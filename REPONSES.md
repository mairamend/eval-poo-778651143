# Réponses — Évaluation POO : héritage, polymorphisme, transtypage

- Matricule : 778651143
- Nom et prénom : Ndiath Mairame 
- N (deux derniers chiffres du matricule) :

> Rédigez vos justifications **avec vos propres mots**. Elles serviront de base à l'échange oral.

## Exercice 1 — A. Affichage prédit

Écrivez ci-dessous, entre les deux lignes de trois accents graves, **une ligne par ligne affichée**, dans l'ordre.
Ne modifiez pas les lignes de trois accents graves.

```text

```
A vehicule
F null 0
E moto 143
Moto2
vehicule
moto
C3
C2
D-vehicule
A vehicule
F null 0
E moto 143
true false
true
A vehicule
B vehicule
3  
## Exercice 1 — A. Justifications

1. Ligne qui commence par « F » :
    new Moto() exécute d’abord le constructeur de Vehicule, qui appelle demarrer() vue aue Moto redéfinit cette méthode donc on choisit la version qui est dans Moto or que les champs de Moto (nom, cylindree) ne sont initialisés qu’après le retour de super() , ils ont encore null et 0 comme valeurs
2. Ligne « C3 » :
    Dans v.comparer(v), l’argument a le type statique Vehicule. Le compilateur retient donc la surcharge comparer(Vehicule). À l’exécution, l’objet est une Moto, qui redéfinit cette méthode, donc C3
3. Ligne « C2 » :
    Dans v.comparer((Moto) v), l’argument a le type statique Moto. Le compilateur choisit la surcharge la plus spécifique, comparer(Moto). Moto ne la redéfinit pas, donc c’est celle de Vehicule qui s’exécute : C2
4. Ligne qui commence par « D- » :
    D-vehicule » : categorie() est static, donc elle est masquée et non redéfinie. L’appel est résolu à la compilation d’après le type statique de v (Vehicule), pas d’après l’objet réel
5. Pourquoi des lignes « A », « F », « E » apparaissent avant « true false » :
    L’expression o.equals(v) + " " + (o == new Moto()) doit être entièrement évaluée avant que println n’affiche quoi que ce soit. L’évaluation de new Moto() provoque d’abord l’affichage de A vehicule, F null 0 et E moto 143. Ensuite seulement true false est affiché. Le false vient de new Moto(), qui crée un objet distinct de o. Le true vient de o.equals(v), qui compare des références identiques.

## Exercice 1 — B. Transtypage

Colonne « Compile ? » : `oui` ou `non`. Colonne « Exécution » : `OK`, `exception` (en précisant laquelle), ou `-` si le code ne compile pas.

| n° | Compile ? | Exécution | Justification |
|----|-----------|-----------|---------------|
| 1  |Oui        |OK         |Une moto est Vehicule , Le transtipage est implicite
| 2  |Non        pas d'execution|    le compilateur ne connaît que le type statique de a (Vehicule) et refuse de l’affecter à une Moto. Il faudrait écrire (Moto)            |
| 3  | Oui          | OK     |       Moto hérite de Vehicule        |
| 4  | Oui   ClassCastExceptio       Object vers Moto est autorisé à la compilation (Moto est une sous-classe d’Object). Mais l’objet réel de b est un Camion, qui n’est pas une Moto : l’exception est levée.         |               |
| 5  | Non         pas d'execution   le compilateur voit que le cast est impossible (« types incompatibles »)      |               |
| 6  |  Oui    |StringClassCastException  À l’exécution, l’objet réel est un Camion, pas une 	        |               |
| 7  | Non        pas dexecution   Le type statique de b est Object, qui n’a pas de méthode type()          |               |
| 8  |   Oui        |   OK        |   Le cast (Vehicule) b réussit, car un Camion est un Vehicule. type() existe dans Vehicule et Camion ne la redéfinit pas            |
