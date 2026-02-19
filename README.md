# Base de mod Hytale: sélection de zone + commande `/colonie`

Tu avais raison: pour le template `plugin-template`, il faut une intégration **simple** sans faux `MyModApi`.

Cette version fournit une feature prête à brancher:

- `ColonyFeature` (assemblage de la feature)
- `ListenerCentral` (events d'interaction)
- `BlockClickHandler` (clic gauche/droit -> posA/posB en chunk)
- `ColonieCommand` (sous-commandes `create`, `info`, `cancel`)

## Intégration dans ton plugin-template

Dans ta classe principale du template, instancie la feature puis enregistre listener + commande avec les APIs du template:

```java
private ColonyFeature colonyFeature;

@Override
public void onEnable() {
    colonyFeature = new ColonyFeature();

    // Adapte ces deux lignes à la vraie API de ton template:
    getEventManager().registerListener(colonyFeature.getListener());
    getCommandManager().registerCommand("colonie", colonyFeature.getCommand());
}
```

## Commandes

- `/colonie info` : affiche l'état courant de la sélection.
- `/colonie create` : valide la zone et lance la création (TODO à brancher).
- `/colonie cancel` : annule la sélection active.

## Flux de fonctionnement

1. Clic gauche sur bloc => définit `posA` (chunk).
2. Clic droit sur bloc => définit `posB` (chunk).
3. `/colonie info` pour vérifier la zone.
4. `/colonie create` pour créer la colonie.

## Important

La logique métier finale de création n'est pas encore branchée (`TODO` dans la commande), mais la structure est compatible pour être intégrée proprement à un template réel.
