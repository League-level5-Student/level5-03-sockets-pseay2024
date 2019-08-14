package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class CClient {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	
	ChatApp window;

	public CClient(ChatApp chatApp, String ip, int port) {
		window = chatApp;
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				window.setAddText("them: " + is.readObject().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendText(String s) {
		try {
			if (os != null) {
				os.writeObject(s);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
