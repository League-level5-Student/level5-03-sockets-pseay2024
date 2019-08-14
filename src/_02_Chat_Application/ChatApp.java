package _02_Chat_Application;

import javax.swing.*;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */
public class ChatApp extends JFrame {
	JPanel jp;
	JLabel lbl = new JLabel("<html>");
	JButton button = new JButton();
	JTextField txt = new JTextField(10);
	CServer server;
	CClient client;
	ChatApp()
	{
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a chat?", null, JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			jp = new JPanel();
			jp.add(lbl);
			jp.add(txt);
			jp.add(button);
			server = new CServer(this, 8080);
			setTitle("Host");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				setAddText("you: " + txt.getText());
				server.sendText(txt.getText());
			});
			add(jp);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			jp = new JPanel();
			jp.add(lbl);
			jp.add(txt);
			jp.add(button);
			setTitle("Connector");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new CClient(this, ipStr, port);
			button.addActionListener((e)->{
				setAddText("you: " + txt.getText());
				client.sendText(txt.getText());
			});
			add(jp);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
	
	void setAddText(String s)
	{
		lbl.setText(lbl.getText() + "<br>" + s);
	}
	public static void main(String[] args) {
		new ChatApp();
	}
}
