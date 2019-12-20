import java.rmi.Remote;
import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


class ChatImpl implements Chat
{	
	public static final String UNIC_CHAT_NAME = "server.chat";
	private StringBuffer buffer = new StringBuffer();
	public StringBuffer getMessage() throws RemoteException{
		return buffer;
	}
	 
	public void sendMessage(String str) throws RemoteException{	
		buffer.append("\n"+str);
	}
	
	public static void main(String[] args){
		try{
		final ChatImpl chatServer = new ChatImpl();
		final Registry registry = LocateRegistry.createRegistry(2099);
		Remote stub = UnicastRemoteObject.exportObject(chatServer, 0);
		registry.bind(UNIC_CHAT_NAME, stub);
		Thread.sleep(Integer.MAX_VALUE);
		}catch (Exception e){}
		
	}
}