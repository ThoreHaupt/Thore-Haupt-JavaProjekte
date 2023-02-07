package CodingClasses.ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;

public class testRadomStuff {
    public static void main(String[] args) throws IOException {
        /* System.out.println(Thread.currentThread().getName());
        ServerSocket s = new ServerSocket(7777);
        Socket sock = s.accept();
        OutputStream o = sock.getOutputStream();
        sock.getInetAddress();
        */

        LinkedList<String> l = new LinkedList<>();
        l.add("A");
        l.add("B");
        Iterator<String> i = l.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }

    }

    synchronized public static <J extends Comparable<J>> J max(Collection<J> xs) {
        Iterator<J> xi = xs.iterator();
        J w = xi.next();
        while (xi.hasNext()) {
            J x = xi.next();
            if (w.compareTo(x) < 0)
                w = x;
        }
        return w;
    }

}

enum Demo {
    A,
    True,
    B,
    False;
    ;

    public static void main(String[] args) {
        {
            System.out.println(A.toString());
            System.out.println(A + "False");

        }
    }

    //void doNothing();
}

abstract class k {
    public void h() {

    }

    abstract void n();

    abstract void b();
}

interface inter {
    void b();
}
