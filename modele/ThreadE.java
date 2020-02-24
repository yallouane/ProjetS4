package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadE extends Thread{

	public ThreadE() {
		
	}
	
	public void envoiMessage(String message,PrintWriter ecriture) {
        ecriture.println(message);
        ecriture.flush();
	}
	
	public String recevoirMessage(BufferedReader lecture) {
		String message;
		try {
			message = lecture.readLine();
			return message;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
