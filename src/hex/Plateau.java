package hex;

import java.util.ArrayList;
import java.util.List;

import ihm.IPlateau;

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
	
	@Override
	public boolean peutJouer() {
		return getNb(Pion.Vide) == 0;
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
			System.out.println(visitees);
			has_won = has_won || visit(voisin, visitees, pions_fin, p);
		}
		return has_won;
	}
	
	public boolean aGagne(Pion pi) {
		if(pi == Pion.J1) {
			List<Coordonne> phaut = new ArrayList<>();
			List<Coordonne> pbas = new ArrayList<>();
			List<Coordonne> visitees = new ArrayList<>();
			
			for(int i=0 ; i < taille(); i++) {
				Coordonne haut = new Coordonne(i,0);
				if(pi == getCase(haut)) 
					phaut.add(haut);
				
				Coordonne bas = new Coordonne(i,taille()-1);
				if(pi == getCase(bas))
					pbas.add(bas);
			}
			
			if(phaut.size() == 0 || pbas.size() == 0)
				return false;
			
			for (Coordonne c : phaut) {
				if (visit(c, visitees, pbas, pi))
					return true;
			}
			
		}else {
			List<Coordonne> pgauche = new ArrayList<>();
			List<Coordonne> pdroit = new ArrayList<>();
			List<Coordonne> visitees = new ArrayList<>();
			
			for(int i=0 ; i < taille(); i++) {
				Coordonne gauche = new Coordonne(0,i);
				if(pi == getCase(gauche)) 
					pgauche.add(gauche);
				
				Coordonne droit = new Coordonne(taille()-1,i);
				if(pi == getCase(droit))
					pdroit.add(droit);
			}
		
			if(pgauche.size() == 0 || pdroit.size() == 0)
				return false;
			
			for (Coordonne c : pgauche) {
				if (visit(c, visitees, pdroit, pi))
					return true;
			}
		}
		return false;
		
		
	
	}
	


}

class Coordonne{
	private int ligne;
	private int colonne;
	
	public Coordonne(int c,int l) {
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
		
		voisins.add(coord_decale(-1, 0));
		voisins.add(coord_decale(-1, 1));
		voisins.add(coord_decale( 0,-1));
		voisins.add(coord_decale( 0, 1));
		voisins.add(coord_decale( 1,-1));
		voisins.add(coord_decale( 1, 0));
		
		for(int i=0; i<voisins.size() ;i++) {
			if( !(p.estValide(voisins.get(i)) && pion.equals(p.getCase(voisins.get(i)))) ) {
				voisins.remove(i);
				i--;
			}
		}
		
		return voisins;
	}
	
	private Coordonne coord_decale(int decalage_x, int decalage_y){
		  return new Coordonne(ligne-decalage_x, colonne-decalage_y);
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
