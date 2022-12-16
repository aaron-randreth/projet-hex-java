package hex;

import java.util.ArrayList;
import java.util.List;

import ihm.IPlateau;

public class Plateau implements IPlateau {
	private final static int TAILLE_MAX = 26;
	private final static int NB_JOUEURS = 2;
	private final static int PREMIERE_COLONNE = 'A';
	private final static int PREMIERE_LIGNE = 1;
	
	// le premier joueur relie la premiere et la derniere ligne
	// le second joueur relie la premiere et la derniere colonne
	
	private Pion[][] t;
	private int joueur = 0; // prochain à jouer
	
	private void suivant() {
		joueur = (joueur +1) % NB_JOUEURS;
	}
	
	public boolean estPlacable(String coord) {
		return (estValide(coord) && Pion.Vide.equals(getCase(coord))); 
	}
	
	@Override
	public void jouer(String coord) {
		assert estValide(coord);
		assert getCase(coord) == Pion.Vide;
		Pion pion = Pion.values()[joueur];
		int col = getColonne (coord);
		int lig = getLigne(coord);
		t[col][lig] = pion;
		suivant();
	}
	
	public static int getTaille(String pos) {
		int taille = (int) Math.sqrt(pos.length());
		assert taille * taille == pos.length();
		return taille;
	}

	@Override
	public boolean estValide(String coord) {
        if ( coord.length() !=2)
                return false;
        
        int col = getColonne (coord);
        int lig = getLigne(coord);
        
        return estValide(new Coordonne(col, lig));
}
	
	@Override
	public Pion getCase(String coord) {
		assert estValide(coord);
		int col = getColonne (coord);
		int lig = getLigne(coord);
		return t[col][lig];
	}

	private int getColonne(String coord) {
		return coord.charAt(0) - PREMIERE_COLONNE; // ex 'B' -'A' == 1
	}
	
	private int getLigne(String coord) {
		
		return Integer.valueOf(coord.substring(1)) - PREMIERE_LIGNE; // ex '2' - '1' == 1
	}

	public Plateau(int taille) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];
		
		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] = Pion.Vide;
	}
	
	public Plateau(int taille, String pos) {
		assert taille > 0 && taille <= TAILLE_MAX;
		t = new Pion [taille][taille];
		
		String[] lignes = decouper(pos);
		
		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[col][lig] = 
				  Pion.get(lignes[lig].charAt(col));
		
		if (getNb(Pion.J1) != getNb(Pion.J2) &&
			getNb(Pion.J1) != (getNb(Pion.J2)+1) &&
					getNb(Pion.J1) != (getNb(Pion.J2)-1))
			throw new IllegalArgumentException(
					"position non valide");
	}
	
	

	@Override
	public int getNb(Pion pion) {
		int nb = 0;
		for (Pion [] ligne : t)
			for (Pion p : ligne)
				if (p == pion)
					++nb;
		return nb;
	}

	@Override
	public int taille() {
		return t.length;
	}
	
	
	private String espaces(int n) {
		String s = "";
		for(int i = 0; i < n; ++i)
			s+= " ";
		return s;
	}
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < taille(); ++i)
			s+=" "+(char)( 'A' + i);
		s+='\n';
		for (int lig = 0; lig < taille(); ++lig) {
			s+= lig+1 + espaces (lig);
			for (int col = 0; col < taille(); ++col)
				s+= " "+ t[col][lig];
			s+='\n';
		}
		return s;
	}

	public static String[] decouper(String pos) {
		int taille = getTaille(pos);
		String[] lignes = new String[taille];
		for (int i = 0; i <taille; ++i)
			lignes[i] = pos.substring(i*taille,
					(i+1)*taille);
		return lignes;
		
	}
	
	

	public boolean estValide(Coordonne coord) {
        if (coord.getColonne() <0 || coord.getColonne() >= taille())
                return false;
        if (coord.getLigne()  < 0 || coord.getLigne() >= taille())
                return false;
        return true;
	}
	
	public Pion getCase(Coordonne c) {
		  assert estValide(c);
		  return t[c.getColonne()][c.getLigne()];
	}
	
	public boolean aGagne(Pion pi) {
		List<Coordonne> phaut = new ArrayList<>();
		List<Coordonne> pbas = new ArrayList<>();
		List<Coordonne> visitee = new ArrayList<>();
		
		for(int i=0;i<taille()-1;i++) {
			if(pi == getCase(new Coordonne(i,0))) 
				phaut.add(new Coordonne(i,0));
			
			if(pi == getCase(new Coordonne(i,taille()-1)))
				pbas.add(new Coordonne(i,0));
		}
		
		if(phaut.size()==0 || pbas.size()==0)
			return false;
		
		/*for(int i=0;i<phaut.size();i++) {
			visitee.add(phaut.get(i));
			Coordonne actuel=phaut.get(i);
			for(int j=0;j<actuel.voisinOccup(this).size()-1;j++){
				if (actuel == 
			}
		}*/
		
	
	}
	


}

class Coordonne{
	private int ligne;
	private int colonne;
	
	public Coordonne(int l,int c) {
		ligne = l;
		colonne=c;
	}
	
	public int getLigne() {
		return ligne;
	} 
	
	public int getColonne() {
		return colonne;
	}
	
	public List<Coordonne> voisinOccup(Plateau p) {
		List<Coordonne> voisins = new ArrayList<>();
		
		voisins.add(coord_decale(-1,0));
		voisins.add(coord_decale(-1,1));
		voisins.add(coord_decale(0,-1));
		voisins.add(coord_decale(0,1));
		voisins.add(coord_decale(1,-1));
		voisins.add(coord_decale(1,0));
		
		for(int i=0;i<voisins.size();i++) {
			if(!p.estValide(voisins.get(i))) {
				voisins.remove(i);
				i--;
			}
		}
		
		return voisins;
	}
	
	private Coordonne coord_decale(int decalage_x, int decalage_y){
		  return new Coordonne(ligne-decalage_x, colonne-decalage_y);
	}

}
