package ProkSy.RP.RP_008.A2;

/**
 *
 * @author Niklas K�hl
 */
public class EchoServer {
    private EchoObject eo;

    public EchoServer() {
        this.eo = new EchoObject();
        System.out.println("Der Server ist aktiv");
    }

    public EchoObject getEchoObject() {
        EchoObject result;
        result = eo;
        return result;
    }
}
