package hex;

import ihm.IPlateau;
import ihm.Pion;

public interface IRegle_victoire {

	boolean aGagne(Pion pi, IPlateau plateau);

}
