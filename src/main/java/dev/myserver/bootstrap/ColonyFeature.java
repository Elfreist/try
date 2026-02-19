package dev.myserver.bootstrap;

import dev.myserver.commands.ColonieCommand;
import dev.myserver.handlers.BlockClickHandler;
import dev.myserver.listener.ListenerCentral;
import dev.myserver.selection.SelectionManager;
import org.hytale.api.world.Block;

/**
 * Assemble les composants de la feature "colonie" sans imposer un type de plugin précis.
 *
 * Objectif: être facilement branché dans n'importe quel template (dont plugin-template).
 */
public final class ColonyFeature {

    private final SelectionManager selectionManager;
    private final ListenerCentral listener;
    private final ColonieCommand command;

    public ColonyFeature() {
        this.selectionManager = new SelectionManager();
        this.listener = new ListenerCentral();
        this.listener.registerHandler(Block.class, new BlockClickHandler(selectionManager));
        this.command = new ColonieCommand(selectionManager);
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
