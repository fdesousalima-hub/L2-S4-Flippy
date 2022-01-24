# Projet Flippy


## Qu'est-ce qu'un flipper ?

Par défininition, un flipper est un Billard électronique, mettant en oeuvre plusieurs dispotitifs de rebond, d'accélération, de
ralentissement, de changement de trajectoire d'une balle. A l'aide de flips, des barres pouvant être bougées par le joueur, le joueur
ne doit pas cesser de renvoyer la balle afin que cette dernière ne tombe jamais en dessous des flips, et dans le même temps marqué
le plus de points grâce aux différents objets du flipper.

## Quel est le but du Projet flipper ?

Le but du projet Flipper est de réaliser un jeu de type Flipper (Pinball, Casse brique..), ou plus globalement, tout jeu permettant de mettre
en oeuvre un système de déplacement d'une balle (collision, rebond, déplacement, vitesse, accélération).
Le choix du jeu est donc libre. Dans notre cas nous avons opté pour le flipper classique, avec quelques foncionnalités propre.

## Qu'est ce que notre flipper a de plus ?

En plus de toutes les fonctionnalités de base d'un flipper, nous avons ajoutés un éditeur de niveau permettant de créer son propre flipper et
de l'enregistrer. En plus de ça, l'ajout de quelques objets supplémentaire comme des planètes modifiant la gravité, ou une superballe marquant
le double des points.

## Comment faire fonctionner le projet ?

Le projet Maven contient un fichier Makefile, permettant de compiler. Il faut donc, dans le terminal, et dans le répertoire ou se trouve le fichier,
taper la commande ```make```.
Une fois le projet compilé, il faut entrer la commande ```java -jar Flippy.jar```, qui s'occupera de lancer le jeu.
Vous pouvez aussi si vous le désirez, visionner le gitstats du projet grâce à la commande ```make Stats```

## Format de fichier

Les données du projet sont stockées dans des fichier java (en ce qui concerne le code du jeu), et dans des fichiers xml, des fichiers flp pour la création
des flippers (pour plus de précisions sur les fichiers flp et leurs création nous vous invitons à regarder la documentationFLP) et autres pour le projet Maven plus globalement. Le tout est stocké dans un dépot Git.


## Auteurs

Le projet est réalisé par

* De Sousa Fabio
* Chanez Julien
* Amorim Pierre
* Clisson Adam


## Licence
