package joueur;

import ihm.IJoueur;
import ihm.IPlateau;

public class JoueurOrdi implements IJoueur {
	
	private static int idRobots= 0;
	private int idRobot;
	
	public JoueurOrdi() {
		idRobot=idRobots++;
		
	}

	@Override
	public String getNom() {
		return "r"+idRobot;
		
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
		p.jouer(""+a1c+a2);

	}


}
