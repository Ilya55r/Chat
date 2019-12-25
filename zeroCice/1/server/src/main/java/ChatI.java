public class ChatI implements Demo.Chat
{	
	String lastMessage;
    //public void printString(String s, com.zeroc.Ice.Current current)
    //{
    //    System.out.println(s);
    //}
	
	public void sendMessage(String s, com.zeroc.Ice.Current current)
	{
		lastMessage = s;
	}
		
	public String getLastMessage(com.zeroc.Ice.Current current)
	{
		return lastMessage;
	}
}