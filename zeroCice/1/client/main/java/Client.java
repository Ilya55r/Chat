import java.util.*;


public class Client
{
    public static void main(String[] args)
    {	Scanner scn = new Scanner(System.in);
		ArrayList<String> msgs = new ArrayList<String>();
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimpleChat:default -p 10000");
            Demo.ChatPrx chat = Demo.ChatPrx.checkedCast(base);
            if(chat == null)
            {
                throw new Error("Invalid proxy");
            }
			
			while (true){
				String msg = scn.next();
				chat.sendMessage(msg);
				String last = chat.getMessage();
				if (!msgs.contains(last)){
					msgs.add(last);
				}
			}
        }
    }
}