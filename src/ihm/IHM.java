package ihm;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import hex.FabriquePlateau;
import joueur.FabriqueJoueur;

public class IHM {
	
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
			} while (!reponse.equals("joueur") && !reponse.equals("seul") && !sc.hasNext());
			 
			System.out.print("Saisissez votre pseudo  : ");
			String pseudo = sc.next();
			j1 = FabriqueJoueur.creer(pseudo);
			
			switch (reponse) {
			case "joueur":
				System.out.print("2eme joueur saisissez votre pseudo : ");
				pseudo = sc.next();
				j2 = FabriqueJoueur.creer(pseudo);
				break;
			case "seul":
				j2 = FabriqueJoueur.creer();
				System.out.println("Vous jouer contre " + j2.getNom());
				break;
			default:
				j2 = FabriqueJoueur.creer();
				System.out.println("Vous jouer contre " + j2.getNom());
				break;
			}
			
			int taille;
			do{
				System.out.print("Saisissez une taille pour le plateau entre 3 et 26 inclus : ");
				taille = sc.nextInt();
			}while((taille < 3 || 26 < taille) && !sc.hasNextInt());
			
			plateau = FabriquePlateau.creer(taille);
			
			System.out.println("Vos pions sont les croix et les ronds pour l'autre joueur.");
			System.out.println(plateau);
			
			do{
				String coord = IHM.saisirCoord(j1.getNom(), sc, taille);
				IHM.jouer(coord, j1, plateau, sc, taille);
				
				System.out.println("Vous avez place votre pion a la position " + coord);
				System.out.println(plateau);
				
				if(plateau.aGagne(Pion.J1)) break;
				
				coord = j2.needs_input()? IHM.saisirCoord(j2.getNom(), sc, taille) : null;
				IHM.jouer(coord, j2, plateau, sc, taille);
				
				System.out.println(plateau);
				
				if(!plateau.peutJouer()) break;
				
			}while(!plateau.aGagne(Pion.J1) || !plateau.aGagne(Pion.J2));
			
			String nom_gagant = (plateau.aGagne(Pion.J1))? j1.getNom(): (plateau.aGagne(Pion.J2)) ? j2.getNom() : "";
			
			if(nom_gagant != "")
				System.out.println("BRAVO " + nom_gagant + ", vous avez gagne !");
			else
				System.out.println("Partie nul, aucun joueur a gagner");
			
			do{
				System.out.print("Vous voulez rejouer ? O(oui)/N(non) : ");
				reponse = sc.next();
			}while (!reponse.equals("O") && !reponse.equals("N"));
			
			if (reponse.equals("N")) rejouer = false;
		}
		
		sc.close();		
	}
	
	private interface validator<T> {
	    public boolean is_valid(T input, Scanner sc);
	}
	
	private interface inputGetter<T> {
		public T getNext(Scanner sc);
	}
	
	private static <Input_type> Input_type get_input(String message, Scanner sc, inputGetter<Input_type> g, validator<Input_type> v) {
		Input_type in;
		do {
			System.out.println(message);
			in = g.getNext(sc);
		} while (!v.is_valid(in, sc));
		
		return in;
		
		/*		
		String col_msg = "Saissisez la colonne entre A et " + (char)(taille -1 + 'A') + " : ";
		validator<String> col_validator =  (col, scanner) -> {return l.contains(col) && !scanner.hasNext();};
		String col = get_input(col_msg, sc, (scanner) -> {return scanner.next();}, col_validator);
		*/
	}

	private static String saisirCoord(String player_name, Scanner sc, int taille) {
		System.out.println(player_name + " , Saissisez votre mouvement sur le plateau");
		
		String msg = "Saissisez la colonne entre A et " + (char)(taille -1 + 'A') + " puis la ligne sous le format 'A1' : ";
		String coord;
		
		char col = '@';
		int li = -1;
		
		do{
			System.out.print(msg);
			coord = sc.next();
			
			col = coord.charAt(0);
			li =  Integer.parseInt(coord.substring(1));
		} while (!(2 <= coord.length() && coord.length() <= 26) &&
				!('A' < col && col <= 'A' + taille) &&
				!(1 <= li && li <= taille ));

		
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
