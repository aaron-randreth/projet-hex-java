package hex;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */
import java.util.ArrayList;

import java.util.List;

import ihm.IPlateau;
import ihm.Pion;

public class Plateau implements IPlateau {
	private final static int TAILLE_MAX = 26;
	private final static int NB_JOUEURS = 2;
	final static int PREMIERE_COLONNE = 'A';
	private final static int PREMIERE_LIGNE = 1;
	
	// le premier joueur relie la premiere et la derniere ligne
	// le second joueur relie la premiere et la derniere colonne
	
	private Pion[][] t;
	private int joueur = 0; // prochain � jouer
	
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
					"position non valide ! ");
	}
	
	
	private void suivant() {
		joueur = (joueur +1) % NB_JOUEURS;
	}
	
	@Override
	public boolean estPlacable(String coord) {
		return (estValide(coord) && Pion.Vide == getCase(coord) && peutJouer()); 
	}
	
	@Override
	public void jouer(String coord){
		assert estValide(coord);
		assert(getCase(coord) == Pion.Vide);
		
		if (!estPlacable(coord))
			throw new IllegalArgumentException("position non valide ! ");

		Pion pion = Pion.values()[joueur];
		int col = getColonne (coord);
		int lig = getLigne(coord);
		t[col][lig] = pion;
		suivant();
	}
	
	@Override
	public boolean peutJouer() {
		return getNb(Pion.Vide) != 0;
	}

	public static int getTaille(String pos) {
		int taille = (int) Math.sqrt(pos.length());
		assert taille * taille == pos.length();
		return taille;
	}

	@Override
	public boolean estValide(String coord) {
        if ( coord.length() < 2)
                return false;
        
        int col = getColonne (coord);
        int lig = getLigne(coord);
        
        return estValide(new Coordonne(col, lig));
}
	
	@Override
	public Pion getCase(String coord) {
		assert estValide(coord);
		int col = getColonne(coord);
		int lig = getLigne(coord);
		return t[col][lig];
	}

	private int getColonne(String coord) {
		return coord.charAt(0) - PREMIERE_COLONNE; // ex 'B' -'A' == 1
	}
	
	private int getLigne(String coord) {
		
		return Integer.valueOf(coord.substring(1)) - PREMIERE_LIGNE; // ex '2' - '1' == 1
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
	
	public boolean visit(Coordonne c, List<Coordonne> visitees, List<Coordonne> pions_fin, Pion p) {
		if (pions_fin.contains(c))
			return true;
		
		boolean has_won = false;
		
		for (Coordonne voisin : c.voisinOccupe(this, p)) {
			if (visitees.contains(voisin))
				continue;
			
			visitees.add(voisin);
			has_won = has_won || visit(voisin, visitees, pions_fin, p);
		}
		return has_won;
	}
	
	public boolean aGagne(Pion pi) {
		List<Coordonne> pions_depart_ocupe = new ArrayList<>();
		List<Coordonne> pions_arrive_ocupe = new ArrayList<>();
		List<Coordonne> visitees = new ArrayList<>();
		
		for(int i=0 ; i < taille(); i++) {
			Coordonne depart = (pi == Pion.J1)? new Coordonne(i,0): new Coordonne(0, i);
			if(pi == getCase(depart)) 
				pions_depart_ocupe.add(depart);
			
			Coordonne arrive = (pi == Pion.J1)? new Coordonne(i,taille()-1):new Coordonne(taille()-1,i);
			if(pi == getCase(arrive))
				pions_arrive_ocupe.add(arrive);
			
		}
		
		if(pions_depart_ocupe.size() == 0 || pions_arrive_ocupe.size() == 0)
			return false;
		
		for (Coordonne c : pions_depart_ocupe)
			if (visit(c, visitees, pions_arrive_ocupe, pi))
				return true;

		return false;
	}

}

class Coordonne{
	private int ligne;
	private int colonne;
	
	public Coordonne(int c, int l) {
		colonne=c;
		ligne = l;
	}
	
	public int getLigne() {
		return ligne;
	} 
	
	public int getColonne() {
		return colonne;
	}
	
	public List<Coordonne> voisinOccupe(Plateau p, Pion pion) {
		List<Coordonne> voisins = new ArrayList<>();
		List<Coordonne> voisins_ocupe = new ArrayList<>();
		
		voisins.add(coord_decale(-1, 0));
		voisins.add(coord_decale(-1, 1));
		voisins.add(coord_decale( 0,-1));
		voisins.add(coord_decale( 0, 1));
		voisins.add(coord_decale( 1,-1));
		voisins.add(coord_decale( 1, 0));
		
		for(Coordonne v : voisins) {
			if(p.estValide(v) && pion == p.getCase(v)) {
				voisins_ocupe.add(v);
			}
		}
		
		return voisins_ocupe;
	}
	
	private Coordonne coord_decale(int decalage_col, int decalage_li){
		  return new Coordonne(colonne+decalage_col, ligne+decalage_li);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Coordonne))
			return false;
		
		if (obj == this)
			return true;
		
		Coordonne c = (Coordonne) obj;
		
		return c.colonne == colonne && c.ligne == ligne;
	}
	public String toString() {
		return "(" + (ligne + 1) + "," + (char)(colonne + Plateau.PREMIERE_COLONNE) + ")";
	}
}
