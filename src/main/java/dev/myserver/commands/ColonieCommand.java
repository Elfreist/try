package dev.myserver.commands;

import dev.myserver.selection.Selection;
import dev.myserver.selection.SelectionManager;
import org.hytale.api.command.Command;
import org.hytale.api.command.CommandSender;
import org.hytale.api.entity.Player;

/**
 * Commande /colonie avec sous-commandes:
 * - /colonie create
 * - /colonie info
 * - /colonie cancel
 */
public class ColonieCommand implements Command {

    private final SelectionManager selectionManager;

    public ColonieCommand(SelectionManager selectionManager) {
        this.selectionManager = selectionManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sendUsage(player);
            return;
        }

        String subCommand = args[0].toLowerCase();

        if ("create".equals(subCommand)) {
            handleCreate(player);
            return;
        }

        if ("info".equals(subCommand)) {
            handleInfo(player);
            return;
        }

        if ("cancel".equals(subCommand)) {
            handleCancel(player);
            return;
        }

        sendUsage(player);
    }

    private void handleCreate(Player player) {
        Selection selection = selectionManager.get(player);
        if (selection == null || !selection.isComplete()) {
            player.sendMessage("§cTu dois définir posA et posB avant /colonie create.");
            return;
        }

        player.sendMessage(
                "§aCréation de colonie: [" +
                        selection.getMinChunkX() + "," + selection.getMinChunkZ() + "] -> [" +
                        selection.getMaxChunkX() + "," + selection.getMaxChunkZ() + "] " +
                        "(" + selection.getChunkWidth() + "x" + selection.getChunkDepth() + " chunks)"
        );

        // TODO: brancher ici ton vrai service de création de colonie.
        selectionManager.remove(player);
    }

    private void handleInfo(Player player) {
        Selection selection = selectionManager.get(player);
        if (selection == null) {
            player.sendMessage("§eAucune sélection active. Clic gauche = posA, clic droit = posB.");
            return;
        }

        if (!selection.isComplete()) {
            player.sendMessage("§eSélection en cours: il manque posA ou posB.");
            return;
        }

        player.sendMessage(
                "§bZone sélectionnée: [" +
                        selection.getMinChunkX() + "," + selection.getMinChunkZ() + "] -> [" +
                        selection.getMaxChunkX() + "," + selection.getMaxChunkZ() + "] " +
                        "(" + selection.getChunkWidth() + "x" + selection.getChunkDepth() + " chunks)"
        );
    }

    private void handleCancel(Player player) {
        Selection selection = selectionManager.get(player);
        if (selection == null) {
            player.sendMessage("§eTu n'as pas de sélection active à annuler.");
            return;
        }

        selectionManager.remove(player);
        player.sendMessage("§aSélection annulée.");
    }

    private void sendUsage(Player player) {
        player.sendMessage("§6Usage: /colonie <create|info|cancel>");
    }
}
