import java.rmi.Remote;
import java.rmi.RemoteException;
interface Chat extends Remote
{
public StringBuffer getMessage() throws RemoteException;
 public void sendMessage(String str) throws RemoteException;
}