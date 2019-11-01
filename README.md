# fitnessCoach
Java console application to calculate bodybuilding statistics

- [Description](#description_001)
- [Enoncé du brief](#enonce)
- [Exécution](#execution_001)

<a id="description_001"></a>
# Description
L'idée est de développer un petit programme Java en mode console pour enregistrer des séances de musculation dans un fichier csv et faire des statistiques sur les exercices.

<a id="description_001"></a>
# Énoncé du brief
Il s'agit de réaliser un programme en mode console qui permet de :

- Ajouter un set de musculation en spécifiant : le type d'exercice, le nombre de répétition, le poids utilisé pour le set
Sauver tous les sets dans un même fichier csv.
- Demander des statistiques sur un type exercice particulier : par rapport au poids, par rapport aux répétitions.

Le rendu de l'application doit être conforme à la trame ci-dessous :
![menuProgramme](/brief/MenuProgramme.png)

<a id="execution_001"></a>
# Exécution
L'application peut être appelée avec ou sans paramètre.

Par défaut, le fichier csv lu est *"/set/bernard.csv"*.

Si un emplacement d'un autre fichier est spécifié en paramètre, ce dernier sera utilisé par l'application.