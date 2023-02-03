package CodingClasses.ProkSy.RP.RP_01.A1;

/**
 * @version 1.0
 * @author Jonas Lehner
 *
 */
public class MetzgerDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Metzger m1 = new Metzger("Meyer", Wursttypen.LYONER);
        Metzger m2 = new Metzger("Mueller", Wursttypen.WEISSWURST);
        Metzger m3 = new Metzger("Mumpitz", Wursttypen.METTWURST);
        Metzger m4 = new Metzger("Michel", Wursttypen.GRUETZWURST);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
    }
}
