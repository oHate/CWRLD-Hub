package com.customwrld.hub.listeners.events;

import com.customwrld.customlib.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NPCInteractEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final Player player;

    private final NPC npc;

    public NPCInteractEvent(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public Player getPlayer() {
        return player;
    }

    public NPC getNPC() {
        return npc;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
