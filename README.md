# SAE_2.01 - Mange moi si tu peux !

## 👾 Présentation
Ce projet Java/JavaFX simule un jeu où un mouton tente de fuir un loup à travers un labyrinthe rempli de végétation.
Le joueur contrôle manuellement les deux animaux, tour par tour. L'objectif ? Survivre... ou chasser !

## 🚫 Règles du jeu
Le mouton mange toujours ce qu'il y a sur la case où il s'arrête : 
- 🌱 **Herbe** : vitesse = 2
- 🌵 **Cactus** : vitesse = 1
- 🌼 **Marguerite** : vitesse = 4

Le loup ne peut pas sortir du labyrinthe. Il gagne s'il attrape le mouton.
Le mouton gagne s'il sort du labyrinthe sans se faire attraper par le loup.
Tous les deux ont une vision de 5 cases (distance de Manhattan)

## 🛠️ Fonctionnalités
- Génération personnalisée du labyrinthe
- Positionnement du loup et du mouton
- Contrôle manuel des animaux (glisser-déposer)
- Comportemment conditioné par la vision et les végétaux consommés
- Fin de la partie lorsqu'un des animaux atteint son objectif

## 🚀 Comment jouer ?
1. Cloner le dépôt Git :
```
git clone https://github.com/lsncq/SAE_2.01.git
```

Compiler et exécuter le projet :
```
cd SAE_2.01
java -jar SAE_2.01.jar
```

## ✅ Tests 
Lancer les tests avec Maven ou votre IDE :
```
mvn test
```

## ✅ Tests
Les principales méthodes sont testées avec JUnit. Exécuter les tests avec : mvn test

## 👥 Equipe
STIEVENARD Kilian - Développeur principal
SENOCQ Louis - Designer UX/UI
LEWANDOWSKI--BRY Enzo 
DEPARIS Corentin

## 📃 Licence
Ce projet à été réalisé dans le cadre d'un projet universitaire en BUT Informatique. Tout droit à l'Université Polytechnique des Hauts-de-France, IUT de Maubeuge.
