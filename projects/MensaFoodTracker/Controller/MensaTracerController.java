package Projects.MensaFoodTracker.Controller;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.ChangeListener;

import Projects.MensaFoodTracker.Model.MensaMealWrapper;
import Projects.MensaFoodTracker.Model.MensaTrackerModel;
import Projects.MensaFoodTracker.View.MealTablePanel;
import Projects.MensaFoodTracker.View.MensaTrackerFrame;

public class MensaTracerController {

    MensaTrackerModel model;
    MensaTrackerFrame frame;

    public MensaTracerController(MensaTrackerModel model) {
        this.model = model;
        Thread closeModelHook = new Thread(() -> {
            model.closeModel();
            System.out.println("shutDown VM");
        });
        Runtime.getRuntime().addShutdownHook(closeModelHook);
    }

    public void setMensaTrackerFrame(MensaTrackerFrame frame) {
        this.frame = frame;
    }

    public ActionListener getSetDateButtonListener(JSpinner cal) {
        ActionListener al = e -> {
            DateEditor editor = (DateEditor) cal.getEditor();
            String spinnerInp = editor.getFormat().format(cal.getValue());

            //tauscht alles, was nicht ne zahl ist gegen ein "-" aus
            String eddited = spinnerInp.chars().mapToObj(c -> Character.isDigit(c) ? "" + (char) c : "-")
                    .collect(Collectors.joining(""));

            LocalDate ld = eddited.split("-").length == 3
                    ? LocalDate.parse(MensaTrackerModel.reverseDateFormat(eddited, "-"))
                    : model.getSelectedDate();
            model.setDate(ld.compareTo(LocalDate.now()) > 0 ? ld : LocalDate.now());
            frame.rebuildSelectionPanelList();
        };
        return al;
    }

    public ActionListener getAddHistoryListener(MealTablePanel listPanel) {
        return e -> {
            MensaMealWrapper[] meals = listPanel.getActiveMeals();
            model.addToHistory(meals);
            frame.rebuildHistoryPanelList();
        };
    }

    public ActionListener getDeleteFromHistoryActionListener(MealTablePanel listPanel) {
        return e -> {
            MensaMealWrapper[] meals = listPanel.getActiveMeals();
            model.deleteFromHistoryButton(meals);
            frame.rebuildHistoryPanelList();
        };
    }

    public WindowAdapter getWindowCloseAdapterImp() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                model.closeModel();
            }
        };
    }

    /**
     * depreciated
     */
    public ChangeListener getSetDateChangeListener(JSpinner cal) {
        return e -> {
            DateEditor editor = (DateEditor) cal.getEditor();
            String spinnerInp = editor.getFormat().format(cal.getValue());

            //tauscht alles, was nicht ne zahl ist gegen ein "-" aus
            String eddited = spinnerInp.chars().mapToObj(c -> Character.isDigit(c) ? "" + (char) c : "-")
                    .collect(Collectors.joining(""));

            LocalDate ld = eddited.split("-").length == 3
                    ? LocalDate.parse(MensaTrackerModel.reverseDateFormat(eddited, "-"))
                    : model.getSelectedDate();
            model.setDate(ld.compareTo(LocalDate.now()) > 0 ? ld : LocalDate.now());
            frame.rebuildSelectionPanelList();

        };
    }
}
