import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;


public class Client extends JFrame {
	public static final String UNIC_CHAT_NAME = "server.chat";
	//area for all letters
	TextArea area;
	//field for message text
	TextField text;
	//field for name
	TextField textName;
	//send btn
	JButton btnSend;
	//structure unit
	JPanel messInfo;

	public Client(String name){
		super(name);
		messInfo = new JPanel();
		messInfo.setLayout(new FlowLayout());
		area = new TextArea("Messages"+"\n",10,45);
		area.setEnabled(false);
		text = new TextField("", 20);
		textName = new TextField("name", 10);
		btnSend = new JButton("Send Message");
		btnSend.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		String str = text.getText();
		text.setText("");
		try{
		chat.sendMessage(name+" : "+str);
		area.setText(chat.getMessage().toString());
		}catch(Exception ex){}
		}
	});
		setLayout(new BorderLayout());
		add(area,BorderLayout.CENTER);
		messInfo.add(textName);
		messInfo.add(text);
		add(messInfo,BorderLayout.SOUTH);
		add(btnSend,BorderLayout.EAST);
		setLocation(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		setVisible(true);
		try{
			registry = LocateRegistry.getRegistry(2099);
			chat = (Chat) registry.lookup(UNIC_CHAT_NAME);
		}catch(Exception e){}
		
	}
	public static void main(String[] args) {
		//chat client
		new Client("Chat");
	}
}