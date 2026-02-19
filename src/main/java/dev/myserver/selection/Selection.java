package dev.myserver.selection;

import org.hytale.api.entity.Player;

/**
 * Représente une sélection de zone en chunks pour un joueur.
 */
public class Selection {

    private final Player player;

    private Integer posAChunkX;
    private Integer posAChunkZ;
    private Integer posBChunkX;
    private Integer posBChunkZ;

    public Selection(Player player) {
        this.player = player;
    }

    public void setPosA(int x, int z) {
        this.posAChunkX = x;
        this.posAChunkZ = z;
        player.sendMessage("§aPosition A définie : " + x + "," + z);
    }

    public void setPosB(int x, int z) {
        this.posBChunkX = x;
        this.posBChunkZ = z;
        player.sendMessage("§aPosition B définie : " + x + "," + z);
    }

    public boolean isComplete() {
        return posAChunkX != null && posAChunkZ != null && posBChunkX != null && posBChunkZ != null;
    }

    public int getPosAChunkX() {
        return posAChunkX;
    }

    public int getPosAChunkZ() {
        return posAChunkZ;
    }

    public int getPosBChunkX() {
        return posBChunkX;
    }

    public int getPosBChunkZ() {
        return posBChunkZ;
    }

    public int getMinChunkX() {
        return Math.min(posAChunkX, posBChunkX);
    }

    public int getMaxChunkX() {
        return Math.max(posAChunkX, posBChunkX);
    }

    public int getMinChunkZ() {
        return Math.min(posAChunkZ, posBChunkZ);
    }

    public int getMaxChunkZ() {
        return Math.max(posAChunkZ, posBChunkZ);
    }

    public int getChunkWidth() {
        return getMaxChunkX() - getMinChunkX() + 1;
    }

    public int getChunkDepth() {
        return getMaxChunkZ() - getMinChunkZ() + 1;
    }

    public Player getPlayer() {
        return player;
    }
}
