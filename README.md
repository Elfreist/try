# Base de mod Hytale: sélection de zone + commande `/colonie`

Tu as partagé la bonne approche: on peut partir **sans template** en ajoutant les dépendances Hytale manuellement.

Ce repo inclut maintenant:

- un squelette de code (`selection`, `handlers`, `listener`, `commands`, `bootstrap`)
- une base **Gradle Kotlin DSL** (`settings.gradle.kts`, `build.gradle.kts`)
- une base **Maven** (`pom.xml`)
- un `manifest.json` à personnaliser
- un point d'entrée minimal `dev.myserver.Main`

## Dépendance Hytale (Gradle)

Le `build.gradle.kts` contient:

- `mavenCentral()`
- repo Hytale `https://maven.hytale.com/release`
- dépendance `implementation("com.hypixel.hytale:Server:+")`

Tu peux remplacer `+` par une version fixe quand tu veux stabiliser ton build.

## Dépendance Hytale (Maven)

Le `pom.xml` contient le repo Hytale et une dépendance `Server` en scope `provided`.

⚠️ Pense à remplacer `LATEST_VERSION_HERE` avant de build.

## Intégration rapide

Dans ton entrypoint réel (celui du template/projet):

```java
private ColonyFeature colonyFeature;

@Override
public void onEnable() {
    colonyFeature = new ColonyFeature();
    getEventManager().registerListener(colonyFeature.getListener());
    getCommandManager().registerCommand(colonyFeature.getRootCommand(), colonyFeature.getCommand());
}
```

## Utilisation en jeu

1. Clic gauche sur bloc -> définit `posA` (chunk).
2. Clic droit sur bloc -> définit `posB` (chunk).
3. `/colonie info` -> affiche la zone.
4. `/colonie create` -> lance la création (TODO métier à brancher).
5. `/colonie cancel` -> annule la sélection.

## Next steps

- Personnaliser `settings.gradle.kts`, `build.gradle.kts` et `manifest.json`
- Brancher la vraie logique de création de colonie dans `ColonieCommand#handleCreate`
- Ajouter tes règles métier (claims, limites, permissions, persistance)
