package joueur;
import jeu.IJoueur;
import jeu.IPlateau;

public class JoueurOrdi implements IJoueur {
	
	private static int idRobots= 0;
	private int idRobot;
	
	public JoueurOrdi() {
		idRobot=idRobots++;
	}

	@Override
	public String getNom() {
		return "R"+idRobot;
		
	}

	@Override
	public void jouer(String coord, IPlateau p) {
		int a1;
		int a2;
		char a1c;
		do {
			a1 = (int) (Math.random() * (p.taille()-1))+1;
			a2 = (int) (Math.random() * (p.taille()-1))+1;
			a1c = (char)(a1 +'A') ;
		}while (!p.estPlacable(""+a1c+a2));
		System.out.println(getNom() + " a place son pion a la position " + a1c+a2 );
		p.jouer(""+a1c+a2);

	}
	
	@Override
	public boolean needs_input() {
		return false;
	}


}
