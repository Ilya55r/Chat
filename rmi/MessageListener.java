import java.awt.*;
import javax.swing.*;

public class MessageListener implements Runnable{
	private Chat chat;
	TextArea area;
	private StringBuffer msgs = new StringBuffer();
	public MessageListener(Chat chat, TextArea area){
		this.chat = chat;
		this.area = area;
	}
	public void run() {
		while(true){
			try{
			StringBuffer str = chat.getMessage();
			//Thread.currentThread().sleep(10);
			if (!msgs.equals(str)){
				msgs = str;
				area.setText(msgs.toString());
			}
				}catch(Exception e){
					
					}
		}
	
	
	}
	
	
}


