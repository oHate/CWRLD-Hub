package com.customwrld.hub.listeners;

import com.customwrld.customlib.CustomLib;
import com.customwrld.customlib.npc.NPC;
import com.customwrld.customlib.util.InventoryUtil;
import com.customwrld.customlib.util.ServerUtil;
import com.customwrld.hub.Hub;
import com.customwrld.hub.listeners.events.NPCInteractEvent;
import com.customwrld.hub.menus.CosmeticMenu;
import com.customwrld.hub.menus.ServerSelectorMenu;
import com.customwrld.hub.util.Util;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class HubListeners implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory() != null) {
            if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
                if(event.getSlotType() == InventoryType.SlotType.ARMOR) {
                    event.setCancelled(true);
                }
                if (player.getGameMode() != GameMode.CREATIVE) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onNPCInteract(NPCInteractEvent event) {
        Player player = event.getPlayer();
        NPC npc = event.getNPC();

        System.out.println("Interact Event: " + npc.server + " by " + player.getName());

//        ByteArrayOutputStream b = new ByteArrayOutputStream();
//        DataOutputStream out = new DataOutputStream(b);
//
//        try {
//            out.writeUTF("Connect");
//            out.writeUTF(npc.server);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        player.sendPluginMessage(Hub.get(), "BungeeCord", b.toByteArray());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!(player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.interact"))) {
            event.setCancelled(true);
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null && event.getHand() == EquipmentSlot.HAND) {
                if (InventoryUtil.isSimilar(event.getItem(), Util.SERVER_SELECTOR)) {
                    event.setCancelled(true);
                    new ServerSelectorMenu(player);
                }

                if (InventoryUtil.isSimilar(event.getItem(), Util.COSMETIC_SELECTOR)) {
                    event.setCancelled(true);
                    new CosmeticMenu(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.deathMessage(null);
        event.getDrops().clear();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.teleport(Hub.get().getSpawn());
        Util.setItems(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Hub hub = Hub.get();
        if(player.getGameMode() != GameMode.CREATIVE) {
            if (player.getLocation().getY() <= 0) {
                player.teleport(hub.getSpawn());
            }
            if (player.getLocation().getY() >= 256) {
                player.teleport(hub.getSpawn());
            }
        }
        if (player.getGameMode() != GameMode.CREATIVE && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1.0));
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1.0F, 0.8F);
        }
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Hub.get().setupNetty(player);

//        player.sendTitle(CC.RGB(252, 115, 0) + CC.BOLD + "MineTale", CC.YELLOW + "Welcome, " + player.getName() + "!", 20, 80, 20);

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            if(onlinePlayer != player && Util.toggledVisibility.contains(onlinePlayer.getUniqueId())) {
                ServerUtil.hidePlayer(player, onlinePlayer);
            }
        });

//        Arrays.asList(CC.CHAT_BAR,
//                "",
//                "&eWelcome to the &6&lMineTale Network",
//                "",
//                "&8&l\u00BB &6Website: &eminetale.cc",
//                "&8&l\u00BB &6Store: &estore.minetale.cc",
//                "&8&l\u00BB &6Twitter: &ehttps://twitter.com/MineTaleCC",
//                "",
//                "&6You are currently connected to " + mLib.get().getServerName(),
//                CC.CHAT_BAR).forEach(message -> player.sendMessage(CC.translate(message)));

        player.getInventory().clear();
        player.getActivePotionEffects().clear();

        player.teleport(Hub.get().getSpawn());
        Util.setItems(player);
        player.setGameMode(GameMode.ADVENTURE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Util.toggledSpeed.remove(uuid);
        Util.toggledVisibility.remove(uuid);
    }
}
