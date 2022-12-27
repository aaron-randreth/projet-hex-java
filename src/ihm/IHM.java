package ihm;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import hex.FabriquePlateau;
import hex.Pion;
import joueur.FabriqueJoueur;

public class IHM {
	
	public static void main(String[] args){
		boolean rejouer = true;
		Scanner sc = new Scanner(System.in);
		while(rejouer){
			IPlateau plateau = null;
			IJoueur j1 = null;
			IJoueur j2 = null;
			
			System.out.println("Voulez vous jouer contre un autre joueur ou seul (face a l'ordinateur) ?");
			
			String reponse;
			 do{
				System.out.print("Saisissez 'joueur' ou 'seul' : ");
				reponse = sc.next();
			}while ((!reponse.equals("joueur") || !reponse.equals("seul")) && !sc.hasNext());
			
			switch (reponse) {
			case "joueur":
				System.out.print("Saisissez votre pseudo  : ");
				String pseudo = sc.next();
				j1 = FabriqueJoueur.creer(reponse, pseudo);
				System.out.print("2eme joueur saisissez votre pseudo : ");
				pseudo = sc.next();
				j2 = FabriqueJoueur.creer(reponse, pseudo);
				break;
			case "seul":
				System.out.println("Saisissez votre pseudo :");
				pseudo = sc.next();
				j1 = FabriqueJoueur.creer("humain", pseudo);
				j2 = FabriqueJoueur.creer("ordinateur");
				System.err.println("Vous jouer contre " + j2.getNom());
			}
			
			int taille;
			do{
				System.out.print("Saisissez une taille pour le plateau entre 3 et 26 inclus :");
				taille = sc.nextInt();
			}while((3 > taille || 26 < taille) && !sc.hasNextInt());
			
			plateau = FabriquePlateau.creer(taille,null);
			
			System.out.println("Vos pions sont les croix et les ronds pour l'autre joueur.");
			System.out.println(plateau);
			
			do{
				
				System.out.println(j1.getNom() + " , Saissisez votre mouvement sur le plateau ");
				String coord = IHM.saisirCoord(sc, taille);
				j1.jouer(coord, plateau);
				
				System.out.println("Vous avez place votre pion a la position " + coord);
				System.out.println(plateau);
				
				if(plateau.aGagne(Pion.J1)) break;
				
				if (j2.getClass().getName().equals("joueur.JoueurHumain")) {
					System.out.println(j2.getNom() + " , Saissisez votre mouvement sur le plateau");
					coord = IHM.saisirCoord(sc, taille);;
					j2.jouer(coord, plateau);
				}
				else j2.jouer(null, plateau);
				
				System.out.println(plateau);
				
			}while(!plateau.aGagne(Pion.J1) || !plateau.aGagne(Pion.J2));
			
			
			if(plateau.aGagne(Pion.J1)) System.out.println("BRAVO" + j1.getNom() + ", vous avez gagné !");
			else System.out.println("BRAVO" + j2.getNom() + ", vous avez gagné !");
			
			do{
				System.out.print("Vous voulez rejouer ? O(oui)/N(non) : ");
				reponse = sc.next();
			}while (!reponse.equals("O") && !reponse.equals("N"));
			
			if (reponse.equals("N")) rejouer = false;
		}
		
		sc.close();
		
		
	}

	private static String saisirCoord(Scanner sc, int taille) {
		List<String> l = new ArrayList<>();
		for (int i = 0; i < taille; i++) {
			l.add((char)(i + 'A')+"");
		}
		String col;
		do{
			System.out.print("Saissisez la colonne entre A et " + (char)(taille -1 + 'A') + " : ");
			col = sc.next();
		}while (!l.contains(col) && !sc.hasNext());
		
		int lig; 
		do{
			System.out.print("Saissisez la ligne : ");
			lig = sc.nextInt();
		}while ((1 > lig || lig > taille) && !sc.hasNextInt());
		
		return col +""+ lig;
	}
}
