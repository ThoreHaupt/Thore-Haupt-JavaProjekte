package PracticeProjects.bbtanclone;

public class bbtanBall {
    int angle;
    static int radius = 1;
    int[] vectorStep;
    int[] position;

    static {
        bbtanField.radius = radius;
    }

    static double speed = .3;

    public bbtanBall(int angle, int[] position) {
        this.angle = angle;
        vectorStep = calculteVectorStep();
        this.position = position;
    }

    public int calcuateReflection(int secandAngle) {
        return (180 - angle - 2 * secandAngle) % 360;
    }

    public int[] calculteVectorStep() {
        int[] step = new int[2];
        step[0] = (int) Math.cos(angle);
        step[1] = (int) Math.sin(angle);
        return step;
    }

    public void move() {
        vectorAdd(position, vectorStep);
        checkBox();
    }

    public static int[] vectorAdd(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
        return a;
    }

    public boolean checkBox() {
        // bbtanField.get(0);
        return true;
    }

    @Override
    public String toString() {
        return ("balls");
    }
}
