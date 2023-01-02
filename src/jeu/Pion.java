package jeu;
/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

public enum Pion {
	J1('X'), J2('O'), Vide('.');
	private char symbole;
	
	/**
	 * @brief Constructeur de Pion
	 * @param symbole du joueur 
	 */
	private Pion (char symbole) {
		this.symbole = symbole;
	}
	
	/**
	 * @brief Affichage d'un pion
	 */
	@Override
	public String toString() {
		return ""+symbole;
	}
	
	/**
	 * @brief Retourne un pion à partir de son symbole
	 * @param c le symbole
	 * @return le Pion
	 */
	public static Pion get(char c) {
		for (Pion p : Pion.values())
			if (p.symbole == c)
				return p;
		throw new IllegalArgumentException(
				"symbole inconnu " + c);
	}
}
