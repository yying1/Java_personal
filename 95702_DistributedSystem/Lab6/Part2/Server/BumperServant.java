import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.math.BigInteger;

public class BumperServant extends UnicastRemoteObject implements Bumper {
    public static BigInteger held = BigInteger.valueOf(0);

    public BumperServant() throws RemoteException {
    }
    public boolean bump() throws RemoteException {
        System.out.println("Got request to add 1 to " + held.toString());
        held = held.add(new BigInteger("1"));
        return true;
    }

    public BigInteger get() throws RemoteException {
        System.out.println("Got request to return held BigInteger");
        return held;
    }
}