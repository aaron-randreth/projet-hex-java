package ihm;

/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

import java.util.Scanner;
import hex.FabriquePlateau;
import joueur.FabriqueJoueur;

public class IHM {
	private static IFabriquePlateau fplateau = new FabriquePlateau();
	private static IFabriqueJoueur fjoueur = new FabriqueJoueur();
	
	public static void main(String[] args){
		boolean rejouer = true;
		Scanner sc = new Scanner(System.in);
		while(rejouer){
			IPlateau plateau;
			IJoueur j1, j2;
			
			System.out.println("Voulez vous jouer contre un autre joueur ou seul (face a l'ordinateur) ?");
			
			String reponse;
			 do{
				System.out.print("Saisissez 'joueur' ou 'seul' : ");
				reponse = sc.next();
			} while (!reponse.equals("joueur") && !reponse.equals("seul"));
			 
			System.out.print("Saisissez votre pseudo  : ");
			String pseudo = sc.next();
			j1 = fjoueur.creer(pseudo);
			
			switch (reponse) {
			case "joueur":
				System.out.print("2eme joueur saisissez votre pseudo : ");
				pseudo = sc.next();
				j2 = fjoueur.creer(pseudo);
				break;
			case "seul":
				j2 = fjoueur.creer();
				System.out.println("Vous jouer contre " + j2.getNom());
				break;
			default:
				j2 = fjoueur.creer();
				System.out.println("Vous jouer contre " + j2.getNom());
				break;
			}
			
			int taille;
			do{
				System.out.print("Saisissez une taille pour le plateau entre 3 et 26 inclus : ");
				taille = sc.nextInt();
			}while((taille < 3 || 26 < taille));
			
			plateau = fplateau.creer(taille);
			
			System.out.println("Vos pions sont les croix et les ronds pour l'autre joueur.");
			System.out.println(plateau);
			
			String coord;
			do{
				coord = j1.needs_input()? IHM.saisirCoord(j1.getNom(), sc, taille) : null;
				IHM.jouer(coord, j1, plateau, sc, taille);
				
				System.out.println("Vous avez place votre pion a la position " + coord);
				System.out.println(plateau);
				
				if(plateau.aGagne(Pion.J1)) break;
				
				coord = j2.needs_input()? IHM.saisirCoord(j2.getNom(), sc, taille) : null;
				IHM.jouer(coord, j2, plateau, sc, taille);
				
				System.out.println(plateau);
				
				if(!plateau.peutJouer()) break;
				
			}while(!plateau.aGagne(Pion.J1) || !plateau.aGagne(Pion.J2));
			
			if (!plateau.aGagne(Pion.J1) && !plateau.aGagne(Pion.J2))
				System.out.println("Partie nul, aucun joueur n'a gagner");
			else {
				String nom_gagant = (plateau.aGagne(Pion.J1))? j1.getNom():  j2.getNom();
				System.out.println("BRAVO " + nom_gagant + ", vous avez gagne !");
			}
				
			
			do{
				System.out.print("Vous voulez rejouer ? O(oui)/N(non) : ");
				reponse = sc.next();
			}while (!reponse.equals("O") && !reponse.equals("N"));
			
			if (reponse.equals("N")) rejouer = false;
		}
		
		sc.close();		
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	


	private static String saisirCoord(String player_name, Scanner sc, int taille) {
		System.out.println(player_name + " , Saissisez votre mouvement sur le plateau");
		
		String msg = "Saissisez la colonne entre A et " + (char)(taille -1 + 'A') + " puis la ligne sous le format 'A1' : ";
		String coord;
		
		char col = '@';
		int li = -1;
		boolean taille_valide = false, colonne_valide = false, ligne_valide = false;
		
		do{
			System.out.print(msg);
			coord = sc.next();
			
			if (coord.length() < 2 || !isInteger(coord.substring(1))) {
				System.out.println("Veillez saisir une coordonnée valide");
				continue;
			}
			
			col = coord.charAt(0);
			li =  Integer.parseInt(coord.substring(1));
			
			taille_valide = 2 <= coord.length() && coord.length() <= 26;
			colonne_valide = 'A' <= col && col <= 'A' + taille;
			ligne_valide = 1 <= li && li <= taille ;
		} while (!(taille_valide && colonne_valide && ligne_valide));
		
		return coord;
	}
	
	private static void jouer(String coord, IJoueur j, IPlateau p, Scanner sc, int taille) {
		if(coord != null)
			while(!p.estPlacable(coord)) {
				System.out.println("Vous devez donnez une position libre");
				coord = IHM.saisirCoord(j.getNom(), sc, taille);
			}
		j.jouer(coord, p);
	}
	
}
