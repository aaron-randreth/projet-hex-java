package ihm;

import java.util.Scanner;

import hex.Pion;
import hex.Plateau;
import joueur.FabriqueJoueur;

public class main {
	public static void main(String[] args){
		boolean rejouer = false;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("IMPORTANT :\n-Les coordonnées doivent être de la forme : B2 \navec B la colonne et 2 la ligne.\n\n");
			System.out.println("Jouer contre un humain ou un ordinateur ?\nTaper 'humain' ou 'ordinateur'");
			String reponse = sc.nextLine();
			IJoueur j1 = null;
			IJoueur j2 = null;
			while (!reponse.equals("humain") && !reponse.equals("ordinateur")) {
				System.out.println("Saisir : 'humain' ou 'ordinateur'");
				reponse = sc.nextLine();
			}
			if (reponse.equals("humain")) {
				System.out.println("Choisir le pseudo du 1er joueur :");
				String pseudo = sc.nextLine();
				j1 = FabriqueJoueur.creer(reponse, pseudo);
				System.out.println("Choisir le pseudo du 2eme joueur :");
				String pseudo2 = sc.nextLine();
				j2 = FabriqueJoueur.creer(reponse, pseudo2);
			}
			if (reponse.equals("ordinateur")){
				System.out.println("Choisir votre pseudo :");
				String pseudo = sc.nextLine();
				j1 = FabriqueJoueur.creer("humain", pseudo);
				j2 = FabriqueJoueur.creer("ordinateur");
			}
			
			System.out.println("Choisir une taille pour le plateau entre 1 et 26 inclus");
			int taille = sc.nextInt();
			while(0>=taille || 26<taille) {
				System.out.println("entre 1 et 26 inclus");
				taille = sc.nextInt();
			}
			IPlateau plateau = new Plateau(taille);
			while(true) {
				System.out.println(plateau);
				
				if (j1.getClass().getName().equals("joueur.JoueurHumain")) {
					System.out.println(j1.getNom() + " : Saisir les coordonnées du pion à poser sur le plateau :");
					String coord = sc.next();
					j1.jouer(coord, plateau);
				}
				else {
					j1.jouer("A1", plateau);
				}
				if(plateau.aGagne(Pion.J1)) {
					System.out.println("BRAVO" + j1.getNom() + ", tu as gagné !");
					break;
				}
				System.out.println(plateau);
				if (j2.getClass().getName().equals("joueur.JoueurHumain")) {
					System.out.println(j2.getNom() + " : Saisir les coordonnées du pion à poser sur le plateau :");
					String coord2 = sc.next();
					j2.jouer(coord2, plateau);
				}
				else {
					j2.jouer("A1", plateau);
				}
				if(plateau.aGagne(Pion.J1)) {
					System.out.println("BRAVO" + j2.getNom() + ", tu as gagné !");
					break;
				}
				
			}
			System.out.println("Rejouer ? \nTaper : 'oui' ou 'non'");
			String estRejouee = sc.nextLine();
			while (!estRejouee.equals("oui") && !estRejouee.equals("non")) {
				System.out.println("Taper : 'oui' ou 'non'");
				estRejouee = sc.nextLine();
			}
			if (estRejouee=="oui") {
				rejouer = true;
				sc.close();
			}
			else if (sc.nextLine()=="non") {
				rejouer = false;
				sc.close();
			}
		}
		while(rejouer==true);

	}
}
