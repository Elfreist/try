package dev.myserver.bootstrap;

import dev.myserver.commands.ColonieCommand;
import dev.myserver.handlers.BlockClickHandler;
import dev.myserver.listener.ListenerCentral;
import dev.myserver.selection.SelectionManager;
import org.hytale.api.world.Block;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Feature "colonie" prête à être branchée dans un plugin Hytale.
 *
 * Cette classe n'impose pas de type de plugin spécifique: elle expose
 * soit les objets à enregistrer, soit une méthode d'enregistrement générique.
 */
public final class ColonyFeature {

    private static final String ROOT_COMMAND = "colonie";

    private final SelectionManager selectionManager;
    private final ListenerCentral listener;
    private final ColonieCommand command;

    public ColonyFeature() {
        this.selectionManager = new SelectionManager();
        this.listener = new ListenerCentral();
        this.listener.registerHandler(Block.class, new BlockClickHandler(selectionManager));
        this.command = new ColonieCommand(selectionManager);
    }

    /**
     * Enregistrement direct si ton template expose des callbacks/fonctions.
     *
     * @param listenerRegistrar callback qui enregistre un listener
     * @param commandRegistrar  callback qui enregistre une commande (label, command)
     */
    public void register(Consumer<Object> listenerRegistrar, BiConsumer<String, Object> commandRegistrar) {
        listenerRegistrar.accept(listener);
        commandRegistrar.accept(ROOT_COMMAND, command);
    }

    public String getRootCommand() {
        return ROOT_COMMAND;
    }

    public ListenerCentral getListener() {
        return listener;
    }

    public ColonieCommand getCommand() {
        return command;
    }

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }
}
