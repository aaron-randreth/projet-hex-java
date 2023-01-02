package hex;

import jeu.IPlateau;
import jeu.Pion;

public interface IRegle_victoire {

	boolean aGagne(Pion pi, IPlateau plateau);

}
