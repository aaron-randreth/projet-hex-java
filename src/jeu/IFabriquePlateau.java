package jeu;

/**
 * @author RABIARIVELO Ilo Andrianaly
 * @author RANDRETH Aaron 
 * @author Yousrah SOULE
 * @author Adrien DEU
 */

public interface IFabriquePlateau {

	/**
	* @brief Cr�e un plateau
	* @return Un nouveau plateau
	* @param taille du plateau
	 */
	IPlateau creer(int taille);
	
	/**
	 * @brief Cr�ation d'un plateau � partir d'un historique de plateau
	 * @param taille
	 * @param pos
	 * @return Un nouveau plateau
	 */
	IPlateau recreation(int taille, String pos);

}