package application;

import modele.*;

public class Main2 {

	public static void main(String[] args) {
		Serveur serveur = new Serveur("Serv",8080);
		
		Client client1 = new Client("Yohann");
		client1.seConnecter("192.168.1.72", 8080);
		Client client2 = new Client("Amine");
		client2.seConnecter("192.168.1.72", 8080);
		
		serveur.accepterConnexion();
		serveur.accepterConnexion();
			
		while(true) {
			client1.envoiMessageServeur();
			serveur.transfererMessage(0);
			client2.lireMessage();
			client2.envoiMessageServeur();
			serveur.transfererMessage(1);
			client1.lireMessage();
		}
			
	}
}