package Projects.MensaFoodTracker.Controller;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.ChangeEvent;
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
            LocalDate ld = LocalDate.parse(MensaTrackerModel.parseDateFormatToNormal(spinnerInp));
            model.setDate(ld.compareTo(LocalDate.now()) == -1 ? ld : LocalDate.now());
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

    public ChangeListener getSetDateChangeListener(JSpinner cal) {
        return new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                DateEditor editor = (DateEditor) cal.getEditor();
                /* if (cal.getComponent(0).hasFocus()) {
                    return;
                } */
                String spinnerInp = editor.getFormat().format(cal.getValue());
                if (spinnerInp.equals(""))
                    return;
                String dateF = spinnerInp.substring(6, spinnerInp.length()) + "-" + spinnerInp.substring(3, 5) + "-"
                        + spinnerInp.substring(0, 2);
                LocalDate ld = LocalDate.parse(dateF);
                model.setDate(ld.compareTo(LocalDate.now()) == -1 ? ld : LocalDate.now());
                frame.rebuildSelectionPanelList();
            }
        };
    }
}
