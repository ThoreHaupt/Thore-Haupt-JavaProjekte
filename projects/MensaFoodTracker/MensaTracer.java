package Projects.MensaFoodTracker;

import Projects.MensaFoodTracker.Controller.MensaTracerController;
import Projects.MensaFoodTracker.Model.MensaTrackerModel;
import Projects.MensaFoodTracker.View.MensaTrackerFrame;

public class MensaTracer {
    public static void main(String[] args) {
        MensaTrackerModel model = new MensaTrackerModel();
        MensaTracerController controller = new MensaTracerController(model);
        MensaTrackerFrame frame = new MensaTrackerFrame(controller, model);
        controller.setMensaTrackerFrame(frame);
    }
}
