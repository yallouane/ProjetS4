package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private String nom;
	private Socket socketClient;
	private BufferedReader lecture;
	private PrintWriter ecriture;
	private ThreadE threadEcriture;
	private ThreadE threadLecture;
	
	public Client(String nom) {
		this.nom = nom;
		this.threadEcriture = new ThreadE();
		this.threadLecture = new ThreadE();
	}
	
	public void seConnecter (String IPServ,int port) {
		try {
			this.socketClient = new Socket(IPServ,port);
			this.ecriture = new PrintWriter(socketClient.getOutputStream());
	        this.lecture = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void seDeconnecter() {
		if(!this.socketClient.isClosed()) {
			try {
				this.socketClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void envoiMessageServeur() {
		Scanner sc = new Scanner(System.in);
		String message = this.nom + " : " + sc.nextLine();
		this.threadEcriture.envoiMessage(message, ecriture);
	}
	
	public void lireMessage() {
		String message;
		message = this.threadLecture.recevoirMessage(lecture);
		if(!message.isEmpty())
			System.out.println(message);
	}

	public String getNom() {
		return nom;
	}

}
