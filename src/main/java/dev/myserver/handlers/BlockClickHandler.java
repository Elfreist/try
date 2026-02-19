package dev.myserver.handlers;

import dev.myserver.selection.Selection;
import dev.myserver.selection.SelectionManager;
import org.hytale.api.entity.Player;
import org.hytale.api.events.player.InteractionType;
import org.hytale.api.world.Block;

/**
 * Gère les clics sur blocs pour définir les positions A/B en chunks.
 */
public class BlockClickHandler implements InteractionHandler {

    private final SelectionManager selectionManager;

    public BlockClickHandler(SelectionManager selectionManager) {
        this.selectionManager = selectionManager;
    }

    @Override
    public void handle(Player player, Object target, InteractionType interactionType) {
        if (!(target instanceof Block)) {
            return;
        }

        Block block = (Block) target;
        int chunkX = block.getX() >> 4;
        int chunkZ = block.getZ() >> 4;

        Selection selection = selectionManager.getOrCreate(player);

        if (interactionType == InteractionType.LEFT_CLICK_BLOCK) {
            selection.setPosA(chunkX, chunkZ);
        } else if (interactionType == InteractionType.RIGHT_CLICK_BLOCK) {
            selection.setPosB(chunkX, chunkZ);
        }

        if (selection.isComplete()) {
            player.sendMessage("§6Zone prête ! Utilisez /colonie create pour lancer la création.");
        }
    }
}
