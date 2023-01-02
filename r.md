-   [Projet hex du Groupe
    203](#projet-hex-du-groupe-203){#toc-projet-hex-du-groupe-203}
    -   [Résumé de notre
        implémentation ](#résumé-de-notre-implémentation-){#toc-résumé-de-notre-implémentation-}
    -   [Notre structure de
        projet](#notre-structure-de-projet){#toc-notre-structure-de-projet}
        -   [Quelques points à
            noter](#quelques-points-à-noter){#toc-quelques-points-à-noter}
        -   [le diagramme
            UML ](#le-diagramme-uml-){#toc-le-diagramme-uml-}
    -   [Ce que l\'on pourrais
        rajouter ](#ce-que-lon-pourrais-rajouter-){#toc-ce-que-lon-pourrais-rajouter-}
    -   [Synthèse de nos tests
        unitaires ](#synthèse-de-nos-tests-unitaires-){#toc-synthèse-de-nos-tests-unitaires-}
    -   [Bilan du projet ](#bilan-du-projet-){#toc-bilan-du-projet-}
        -   [Nos difficultés](#nos-difficultés){#toc-nos-difficultés}
        -   [Le résultat
            final ](#le-résultat-final-){#toc-le-résultat-final-}

# Projet hex du Groupe 203

**Membres** : Yousrah SOULE, Aaron RANDRETH, Ilo RABIARIVELO, Adrien DEU
**Groupe** : 203

## Résumé de notre implémentation  {#résumé-de-notre-implémentation-}

Dans ce projet nous avons implémenté le jeu Hex, avec un interface sur
la ligne de commande, et deux modes de jeux: \"joueur\" où deux joueurs
s\'affrontent, et \"seul\" où un seul joueur fait face à un ordinateur.
Cette ordinateur n\'a ici pas d\'algorithme complexe, mais comme nous le
montrerons dans ce rapport, il est très simple grace à notre
architecture de créer un robot plus complexe.

## Notre structure de projet

### Quelques points à noter

Pour le jeu de hex, nous avons repéré deux axes de changements
principaux: les joueurs, et le plateau. Pour les joueurs, notre jeu
simple contient deux types de joueurs, mais on peut très bien imaginer
des expensions qui créeraient des robots plus intéligents, ou des
joueurs humains qui jouent un coup aléatoire tout les n coups. Pour le
plateau nous avons utilisé un tableau à deux dimensions mais il est tout
à fait possible que dans le future qu\'une implémentation avec une
structure de donnée différente soit requise.

Nous avons donc créé des interfaces et des fabriques pour ces deux types
de classes. Afin de limiter le plus possible la dépendance de l\'ihm,
qui évolue plutôt lentement, à ceux-ci.

Nous avons utilisé le pattern de délégation sur notre classe Plateau,
afin de nous permettre de facilement changer les règles du jeu, qui
pouraient évoluer à un rythme différent de l\'implémentation de plateau.

### le diagramme UML  {#le-diagramme-uml-}

![Notre diagramme
UML](https://cdn.discordapp.com/attachments/1041750500003029165/1059522049816666223/VLBDQlCm4BplKonyX-yKquOUImdzAKaXDBGF43RErb4YIwvibPGqxrvanHLQGdmmQ6TMCxkQITkHwtojuJ--Drfrg4ndyGORghY5yCVnVWpjJfc67DKzC2TNAOFNLwB-jE_O6T8FLbk7bQDlGRYtRiqkmYjDvGu03DNSDbGogasD_nxx3m-0uIirjItEflDutB8lPWjD7PF7sw8Go8lrxDq0O48MofgS7t0x6jt5ncTQEVNfELvmYN4I1pX2vdqRfPFzmRkuUYZ7uwjRAr6Q-fgK0J7HJr7aBHALtOdSporTPFHpZ2E8-TYduS9csVWQmru1GsROVGIQsAJ0n0upy6mncKbEDhmVd1m0C_eRLvgNNwhih78CV_d9wyk-68maHb8NpvQ0WPD2SPfUwiRtkA2PnpZICvsKRsb3vlcoIYAUsqrOwuJDsjVw5m00.png)

## Ce que l\'on pourrais rajouter  {#ce-que-lon-pourrais-rajouter-}

-   Grace à la l\'interface IRegle_victoire, sans modifier le reste du
    code, nous pourions modifier les règles du jeux en créant une
    nouvelle classe qui l\'implémente. Cette classe pourrait par exemple
    faire gagner un joueur si il lie deux côté adjacent ou transformer
    les cases hexagonales en carré.
-   En créant de nouvelles classes joueurs puis en modifiant la
    fabrique, nous pourions augmenter la difficulté du mode \"seul\" en
    faisant un robot intelligent.
-   En créant de nouvelles classes plateau puis en modifiant la
    fabrique, nous pourions rendre le jeu plus rapide mais plus gourmant
    en espace, ou vice-verca
-   En modifiant uniquement le switch case de la classe IHM nous pouvons
    créer d\'autres modes de jeu, comme un mode robot contre robot.
-   Comme l\'IHM est isolé de toutes les autres classes, pour rajouter
    d\'autres types d\'IHM comme un GUI il suffit de créer une classe
    sans modifier le reste du programme.

## Synthèse de nos tests unitaires  {#synthèse-de-nos-tests-unitaires-}

Pour les joueurs nous avons des tests de cohérence assez simple pour
s\'assurer que:

-   Les noms sont ceux attendus; le nom donné pour l\'humain, et le -
    nom séquenciel (R0, R1..) pour le robot.

-   Les joueurs jouent bien au bon endroit

Pour le plateau nous avons vérifié que:     

-   Le plateau s\'affiche comme voulu.

-   La fonctionnalité de recréation d\'un plateau à partir d\'un état de
    jeu déjà commencé fonctionne.

-   La validité ou la possibilité de placer un pion sur le plateau.

-   Agagne fonctionne pour tout les joueurs, dans le plus de scénarios
    divers possibles pour essayer d\'éviter les cas spéciaux.

## Bilan du projet  {#bilan-du-projet-}

### Nos difficultés

Un des points sur lequel nous avons passé le plus de temps sur le projet
a été la gestion des dépendances. Dès le début avec les différents
packages nous avons créé des interfaces, pour inverser les dépendances
et éviter à l\'IHM d\'être dépendant des classes concretes. Cependant, à
cause de la différence de comportement entre le joueur humain qui a
besoin d\'un dialogue de l\'IHM et le joueur robot qui n\'en a pas
besoin, nous avons finit par avoir des dépendances textuelles néfastes:
comme illustré ci-dessous:

``` java

if (j1.getClass().getName().equals("joueur.JoueurHumain")) {

  System.out.println(j1.getNom() + " : Saisir les coordonnées du pion à poser
  sur le plateau :");

  String coord = sc.next();

  j1.jouer(coord, plateau); } 
```

Pour régler cela nous avons rajouté une méthode dans l\'interface
IJoueur:

``` java

public boolean needs_input();
```

qui nous permet de généraliser le code et d\'éviter de rendre l\'IHM
dépendant à une implémentation spécifique:

    if (j1.needs_input())
      // saisie des coordonées par le joeur

    j1.jouer()

### Le résultat final  {#le-résultat-final-}

Les fonctionnalités attendues du jeu fonctionnent sans problème Ce
qu\'on pourrait améliorer dans le projet serait l\'intelligence de
l\'ordinateur. On pourrait créer des algorithmes plus complexes pour que
les coups de l\'ordinateur soit plus intelligents. 
