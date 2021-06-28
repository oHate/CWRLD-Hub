package com.customwrld.hub;

import com.customwrld.customlib.CustomLib;
import com.customwrld.customlib.cache.ClazzCache;
import com.customwrld.hub.listeners.events.NPCInteractEvent;
import com.customwrld.hub.util.Util;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Executor;

public class NettyInject {

    private final Object networkManager;
    private final Channel channel;
    private final UUID uuid;
    private final Field idField;
    private final Hub hub;
    private final Executor executor;
    private final String channel_name = "npc_interact";

    public NettyInject(Hub hub, final Player player) throws Exception {
        this.hub = hub;
        this.uuid = player.getUniqueId();
        Object craftPlayer = ClazzCache.GET_HANDLE_PLAYER_METHOD.getCacheMethod().invoke(player);
        Object playerConnection = ClazzCache.PLAYER_CONNECTION_FIELD.getCacheField().get(craftPlayer);
        networkManager = ClazzCache.NETWORK_MANAGER_FIELD.getCacheField().get(playerConnection);
        channel = (Channel) ClazzCache.CHANNEL_FIELD.getCacheField().get(networkManager);
        idField = ClazzCache.ID_FIELD.getCacheField();
        executor = r -> this.hub.getServer().getScheduler().scheduleSyncDelayedTask(hub, r, 2);
    }

    public void injectNetty(final Player player) {
        synchronized (networkManager) {
            if (channel == null) throw new IllegalStateException("Channel is NULL!");
            channel.pipeline().addAfter("decoder", channel_name, new MessageToMessageDecoder<>() {
                @Override
                protected void decode(ChannelHandlerContext chc, Object packet, List<Object> out) throws Exception {
                    out.add(packet);
                    if (packet.getClass() == ClazzCache.PACKET_PLAY_IN_USE_ENTITY_CLASS.getCacheClass()) {
                        if (!Util.getValue(packet, "action").toString().equalsIgnoreCase("INTERACT") ||
                                !Util.getValue(packet, "d").toString().equalsIgnoreCase("MAIN_HAND")) return;

                        int entityId = (int) idField.get(packet);
                        CustomLib.getCustomLib().getNpcs().stream().filter(npc1 -> npc1.entityId == entityId).findFirst().ifPresent(npc -> executor.execute(() -> Bukkit.getServer().getPluginManager().callEvent(new NPCInteractEvent(player, npc))));
                    }
                }
            });
        }
    }

    public void ejectNetty() {
        if (!channel.pipeline().names().contains(channel_name)) return;
        channel.eventLoop().execute(() -> channel.pipeline().remove(channel_name));
    }

    public UUID getUuid() {
        return uuid;
    }
}

