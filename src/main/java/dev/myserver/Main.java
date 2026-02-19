package dev.myserver;

import dev.myserver.bootstrap.ColonyFeature;

/**
 * Point d'entrée minimal à adapter à la signature exacte du plugin-template.
 */
public class Main {

    private ColonyFeature colonyFeature;

    public void onEnable() {
        colonyFeature = new ColonyFeature();

        // Branche ici les API réelles de ton plugin-template:
        // getEventManager().registerListener(colonyFeature.getListener());
        // getCommandManager().registerCommand(colonyFeature.getRootCommand(), colonyFeature.getCommand());
    }

    public ColonyFeature getColonyFeature() {
        return colonyFeature;
    }
}
