package modele;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	private String nom;
	private ServerSocket socketServeur;
	private Socket[] socketClient;
	private ThreadE threadEcriture;
	private ThreadE threadLecture;
	private BufferedReader[] lecture;
	private PrintWriter[] ecriture;
	
	public Serveur(String nom,int portLancement) {
		try {
			this.nom = nom;
			this.socketClient = new Socket[2];
			this.socketServeur = new ServerSocket(portLancement,2);
			this.threadEcriture = new ThreadE();
			this.threadLecture = new ThreadE(); 
			this.ecriture = new PrintWriter[2];
			this.lecture = new BufferedReader[2];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void accepterConnexion() {
		
		try {
			if(this.socketClient[0] == null) {
				this.socketClient[0] = socketServeur.accept();
				System.out.println("Connexion accepté de : "+ this.socketClient[0]);
				this.ecriture[0] = new PrintWriter(socketClient[0].getOutputStream());
			    this.lecture[0] = new BufferedReader (new InputStreamReader (socketClient[0].getInputStream()));
			}
			else {
				this.socketClient[1] = socketServeur.accept();
				System.out.println("Connexion accepté de : "+ this.socketClient[1]);
				this.ecriture[1] = new PrintWriter(socketClient[1].getOutputStream());
			    this.lecture[1] = new BufferedReader (new InputStreamReader (socketClient[1].getInputStream()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void envoiMessageSpecifique(int i,String message) {
		this.threadEcriture.envoiMessage(message, ecriture[i]);
	}

	public void transfererMessage(int i) {
		String message = "";
		message = this.threadLecture.recevoirMessage(lecture[i]);

		if(i == 0)
			this.envoiMessageSpecifique(1, message);
		else if(i == 1)
			this.envoiMessageSpecifique(0, message);
	}
}
