package hex;

import java.util.ArrayList;
import java.util.List;

import ihm.IPlateau;
import ihm.Pion;

class Regle_victoire_classique implements IRegle_victoire {
	
	private List<Coordonne> voisinOccupe(Coordonne c, IPlateau p, Pion pion) {
		List<Coordonne> voisins = new ArrayList<>();
		List<Coordonne> voisins_ocupe = new ArrayList<>();
		
		voisins.add(c.coord_decale(-1, 0));
		voisins.add(c.coord_decale(-1, 1));
		voisins.add(c.coord_decale( 0,-1));
		voisins.add(c.coord_decale( 0, 1));
		voisins.add(c.coord_decale( 1,-1));
		voisins.add(c.coord_decale( 1, 0));
		
		for(Coordonne v : voisins) {
			if(p.estValide(v.toString()) && pion == p.getCase(v.toString())) {
				voisins_ocupe.add(v);
			}
		}
		
		return voisins_ocupe;
	}
	
	public boolean visit(Coordonne c, List<Coordonne> visitees, List<Coordonne> pions_fin, Pion pion, IPlateau plateau) {
		if (pions_fin.contains(c))
			return true;
		
		boolean has_won = false;
		
		for (Coordonne voisin : voisinOccupe(c, plateau, pion)) {
			if (visitees.contains(voisin))
				continue;
			
			visitees.add(voisin);
			has_won = has_won || visit(voisin, visitees, pions_fin, pion, plateau);
		}
		return has_won;
	}
	
	@Override
	public boolean aGagne(Pion pi, IPlateau plateau) {
		List<Coordonne> pions_depart_ocupe = new ArrayList<>();
		List<Coordonne> pions_arrive_ocupe = new ArrayList<>();
		List<Coordonne> visitees = new ArrayList<>();
		
		for(int i=0 ; i < plateau.taille(); i++) {
			Coordonne depart = (pi == Pion.J1)? new Coordonne(i,0): new Coordonne(0, i);
			if(pi == plateau.getCase(depart.toString())) 
				pions_depart_ocupe.add(depart);
			
			Coordonne arrive = (pi == Pion.J1)? new Coordonne(i,plateau.taille()-1):new Coordonne(plateau.taille()-1,i);
			if(pi == plateau.getCase(arrive.toString()))
				pions_arrive_ocupe.add(arrive);
			
		}
		
		if(pions_depart_ocupe.size() == 0 || pions_arrive_ocupe.size() == 0)
			return false;
		
		for (Coordonne c : pions_depart_ocupe)
			if (visit(c, visitees, pions_arrive_ocupe, pi, plateau))
				return true;

		return false;
	}
}