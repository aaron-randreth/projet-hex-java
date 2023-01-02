# Projet hex du Groupe 203

**Membres** : Yousrah SOULE, Aaron RANDRETH, Ilo RABIARIVELO, Adrien DEU
**Groupe** : 203

## Résumé de notre implémentation 

Dans ce projet nous avons implémenté le jeu Hex, avec un interface sur la ligne
de commande, et deux modes de jeux: "joueur" où deux joueurs s'affrontent, et
"seul" où un seul joueur fait face à un ordinateur. Cette ordinateur n'a ici
pas d'algorithme complexe, mais comme nous le montrerons dans ce rapport, il
est très simple grace à notre architecture de créer un robot plus complexe.

## Notre structure de projet, le diagramme UML 

Pour le jeu de hex, nous avons repéré deux axes de changements principaux:
les joueurs, et le plateau. Pour les joueurs, notre jeu simple contient deux
types de joueurs, mais on peut très bien imaginer des expensions qui créeraient
des robots plus intéligents, ou des joueurs humains qui jouent un coup
aléatoire tout les n coups. Pour le plateau nous avons utilisé un tableau à
deux dimensions mais il est tout à fait possible que dans le future qu'une
implémentation avec une structure de donnée différente soit requise.

Nous avons donc créé des interfaces et des fabriques pour ces deux types de classes. 
Afin de limiter le plus possible la dépendance de l'ihm, qui évolue plutôt lentement, à ceux-ci.

![Notre diagramme UML](https://cdn.discordapp.com/attachments/1041750500003029165/1059522049816666223/VLBDQlCm4BplKonyX-yKquOUImdzAKaXDBGF43RErb4YIwvibPGqxrvanHLQGdmmQ6TMCxkQITkHwtojuJ--Drfrg4ndyGORghY5yCVnVWpjJfc67DKzC2TNAOFNLwB-jE_O6T8FLbk7bQDlGRYtRiqkmYjDvGu03DNSDbGogasD_nxx3m-0uIirjItEflDutB8lPWjD7PF7sw8Go8lrxDq0O48MofgS7t0x6jt5ncTQEVNfELvmYN4I1pX2vdqRfPFzmRkuUYZ7uwjRAr6Q-fgK0J7HJr7aBHALtOdSporTPFHpZ2E8-TYduS9csVWQmru1GsROVGIQsAJ0n0upy6mncKbEDhmVd1m0C_eRLvgNNwhih78CV_d9wyk-68maHb8NpvQ0WPD2SPfUwiRtkA2PnpZICvsKRsb3vlcoIYAUsqrOwuJDsjVw5m00.png)

## Ce que l'on pourrais rajouter 

Grace à la structuration du projet, peut de choses son à chan

## Synthèse de nos tests unitaires 

Pour les joueurs nous avons des tests de cohérence assez simple pour s'assurer
que:

- Les noms sont ceux attendus; le nom donné pour l'humain, et le  - nom
  séquenciel (R0, R1..) pour le robot.

- Les joueurs jouent bien au bon endroit

Pour le plateau nous avons vérifié que:     

- Le plateau s'affiche comme voulu.

- La fonctionnalité de recréation d'un plateau à partir d'un état de jeu déjà
  commencé fonctionne.

- La validité ou la possibilité de placer un pion sur le plateau.


Nous avons aussi sur le plateau vérifier la possibilité de gagner pour les deux
joueurs :

- Sur le premier test on a vérifié si le Joueur 1 pouvait gagner dans une
  partie normale.
- Sur le deuxième test on a vérifié ce qui se passait lorsque la partie n'est
  pas encore finie et que les deux joueurs n'ont pas encore gagné.
- Sur le troisième test on a vérifié si le Joueur 2 pouvait gagner dans une
  partie normale.
- Sur le quatrième test on a vérifié si le Joueur 2 gagnait si il avait une
  ligne droite de pion.
- Sur le cinquième test on a vérifié si le Joueur 1 gagnait si il avait une
  ligne droite de pion


## Bilan du projet 

### Des difficutlés au niveau des dépendances, et de l'implementation d'une
delegation

Un des points sur lequel nous avons passé le plus de temps sur le projet a été
la gestion des dépendances. Dès le début avec les différents packages nous
avons créé des interfaces, pour inverser les dépendances et éviter à l'IHM
d'être dépendant des clas

``` Java if (j1.getClass().getName().equals("joueur.JoueurHumain")) {

    System.out.println(j1.getNom() + " : Saisir les coordonnées du pion à poser
    sur le plateau :");

    String coord = sc.next();

    j1.jouer(coord, plateau); } ```

### Ce que le projet nous a apporté 

### Le résultat final 

Les fonctionnalités attendues du jeu fonctionnent sans problème Ce qu'on
pourrait améliorer dans le projet serait l'intelligence de l'ordinateur. On
pourrait créer des algorithmes plus complexes pour que les coups de
l'ordinateur soit plus intelligents. 



