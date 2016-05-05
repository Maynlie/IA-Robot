## IA-Robot

# Introduction

Ce projet a été mené durant mon année de Master à trois. Le but était de coder un robot Mindstorm à l'aide de la librairie LeJOS
afin de le faire évoluer à l'intérieur d'un labyrinthe. Dans un premier temps, il devait retrouver la sortie, quel que soit
son point de départ. Puis, à l'aide de l'algorithme A*, revenir à son point de départ le plus vite possible.

# Le Robot et le labyrinthe

Le robot utilisé pour ce projet est muni d'un capteur de distance sonique et se déplace à l'aide de chenilles.
Le labyrinthe est rectangulaire et composé de cases, supposés de même taille. Des murs sont placés aux intersections de ces cases
pour former le corps du labyrinthe. Avant de lancer notre robot dans le labyrinthe, nous avons mis en place une procédure de calibrage.
Tout d'abord, le robot calcule le nombre de rotations de roue qui lui est nécessaire afin de prcourir la distance d'une case.
Il faut donc poser le robot à une distance d'une case d'uun mur et lancer la première phase du programme.
Puis nous testons sa vitesse de rotation pour être sur qu'il tourne de 90° lorsque le script le lui demandera.

# L'algorithme de la main gauche

La stratégie que nous avons choisi de mettre en place pour permettre à notre petit robot de trouver la sortie aura été celle
de la main gauche. C'est à dire que le robot devra toujours garder un mur à sa gauche avant de se déplacer vers une nouvelle case.
Ainsi, si il existe une sortie dans le labyrinthe, il finira toujours par la trouver. De plus, cela lui permettra de cartographier
ce dernier avec le plus de précision possible. Ce qui sera très utile pour la dernière partie du projet.

# Rentrer à la maison

Grace à l'étape précédente, le robot a une idée assez précise de l'ensemble des cases du labyrinthe pouvant le mener jusqu'à son
point de départ. Grâce à ces données, il va calculer le chemin optimal à suivre en utilisant la distance euclidienne
comme heuristique. Une fois le chemin optimal déterminé, il n'aura plus qu'à le suivre.