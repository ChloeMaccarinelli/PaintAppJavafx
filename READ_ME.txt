TP PAINT MACCARINELLI CHLOE L3-INFO

Problemes: la gomme ne fonctionne pas donc j'ai disable le bouton pareil pour le polygone
	   on ne peut pas enregister le document mais uniquement
	   enregister sous.
	   on ne peut pas creer un nouveau document avec un nouveau nom mais
	   on peut supprimer ce que contient la fenetre avec le bouton "poubelle".
	   --> danc ce cas il faudra re-eregistrer sous le document.
	   Le polygone ne fonctionne pas comme il faut!
voir ligne 287 du Controller.java
Quelques repetitions de code pour la sauvegarde je n'ai pas utilise la sérialisation car elle n'est pas au point(fichier model.txt)



Menu File :
==> Ouverture d'un document --> fichier png --> image pas modifiable, elle
disparait a clic dans l'application.
==> Enregistrement d'un document --> fonctionne (visalisation possible dans
n'importe quel editeur de photo) mais enregistre au format png uniquement.
==> Quitter l'application --> fonctionne et demande a l'utilisateur si il veut
vraiment quitter : si oui = quit sinon reste dans l'app.

Toolbar :
==> deux "Color chooser" le premier pour la couleur des traits et contours
le deuxieme pour la couleur de fond.
setStyle("-fx-color-label-visible: false;"); --> enleve le texte du "color chooser"
car sinon code en hexadecimal --> ici on a juste la couleur qui s'affiche.

Pour le remplissage des formes il faut cliquer sur le pot de peinture afin de
preciser que l'on veut une forme pleine.

==> pour l'epaisseur des traits, j'ai prefere un slider car j'ai eu plusieurs probleme avec les 3 boutons
comme demandé; il modifie aussi l'epaisseur
des contours de formes quand elle ne sont pas pleine.

==> Crayon --> trace un trait a main leve : peut changer de couleur, d'epaisseur
etre deplace, supprime.

==> Trait --> trace un trait droit : peut changer de couleur, d'epaisseur
etre deplace, supprime.


==> Ellipse --> trace une ellipse : peut changer de couleur, d'epaisseur
etre deplace, supprime, etre pleine. --> + Ctrl = Cercle.

==> Rectangle --> trace un rectangle :peut changer de couleur, d'epaisseur
etre deplace, supprime, etre plein. --> + Ctrl = Carre.

==> Selection --> cliquer sur la fleche et puis sur une forme pour la deplacer
la supprimer, mettre en avant, en arriere, au premier et dernier plan.

==> boutons mettre en avant, en arriere, au premier et dernier plan --> fonctionnent.
Selectionner la forme au prealable.

==> Pour supprimer une forme : selectionner la forme et appuyer sur suppr ensuite
une boite de dialogue demandera une confirmation.

==> si l'utilisateur quitter l'application par la croix de la fenetre, une boite de dialogue
s'ouvre et demande si il a enregistre son document; si non il lui propose de
l'enregistrer puis ferme l'application.

==> si l'on place la souris sur un bouton un tooltip apparait avec des informations.

==> bouton poubelle --> efface toutes les formes apres confirmation.


