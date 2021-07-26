package com.customwrld.hub.listeners;

import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.Hub;
import com.customwrld.hub.listeners.events.NPCInteractEvent;
import com.customwrld.hub.menus.CosmeticMenu;
import com.customwrld.hub.menus.ServerSelectorMenu;
import com.customwrld.hub.util.CyclicIterator;
import com.customwrld.hub.util.ItemUtil;
import com.customwrld.hub.util.Visibility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
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
//        Player player = event.getPlayer();
//        NPC npc = event.getNPC();
//
//        System.out.println("Interact Event: " + npc.metadata.get("server") + " by " + player.getName());
//
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
        Hub hub = Hub.get();
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();

        if (!(player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.interact"))) {
            event.setCancelled(true);
        }

        if (event.getItem() != null && event.getHand() == EquipmentSlot.HAND && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            switch (event.getItem().getType()) {
                case CLOCK -> {
                    event.setCancelled(true);
                    new ServerSelectorMenu(player);
                }
                case EMERALD, PLAYER_HEAD -> {
                    player.sendMessage(Component.text("This item is currently under development.", MC.CC.RED.getTextColor()));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1F, 0.5F);
                    event.setCancelled(true);
                }
                case BEACON -> {
                    event.setCancelled(true);
                    new CosmeticMenu(player);
                }
                case FIREWORK_STAR -> {
                    UUID uuid = player.getUniqueId();

                    Map<UUID, Integer> visibilityMap = hub.getVisibilityMap();

                    Integer index = visibilityMap.get(uuid);

                    Visibility visibility = new CyclicIterator<>(Visibility.values(), index).next();

                    visibilityMap.put(uuid, visibility.ordinal());

                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 2F);
                    inventory.setItem(8, visibility.getItemStack());

                    // TODO: Visibility Code
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
        ItemUtil.setItems(player);
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

        // TODO: LOAD FROM PROFILE GAMEMODE STORAGE

        Integer playerVisibility = Visibility.ALL.ordinal();
        Hub.get().getVisibilityMap().put(player.getUniqueId(), playerVisibility);

//        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            // TODO THIS
//        }

//        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
//            if(onlinePlayer != player && ItemUtil.toggledVisibility.contains(onlinePlayer.getUniqueId())) {
//                ServerUtil.hidePlayer(player, onlinePlayer);
//            }
//        });

        player.sendMessage(MC.Style.SEPARATOR);
        player.sendMessage(Component.empty());
        player.sendMessage(Component.text()
                .append(Component.text("Welcome to the ", MC.CC.BRAND_LIGHT.getTextColor()))
                .append(Component.text("CUSTOMWRLD Network", MC.CC.BRAND.getTextColor(), TextDecoration.BOLD)));
        player.sendMessage(Component.empty());
        player.sendMessage(Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("Website: ", MC.CC.BRAND_LIGHT.getTextColor()))
                .append(Component.text("https://customwrld.com", MC.CC.WHITE.getTextColor())));
        player.sendMessage(Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("Store: ", MC.CC.BRAND_LIGHT.getTextColor()))
                .append(Component.text("https://store.customwrld.com", MC.CC.WHITE.getTextColor())));
        player.sendMessage(Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("Discord: ", MC.CC.BRAND_LIGHT.getTextColor()))
                .append(Component.text("https://discord.customwrld.com", MC.CC.WHITE.getTextColor())));
        player.sendMessage(Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("Twitter: ", MC.CC.BRAND_LIGHT.getTextColor()))
                .append(Component.text("https://twitter.com/customwrldcom", MC.CC.WHITE.getTextColor())));
        player.sendMessage(Component.empty());
        player.sendMessage(MC.Style.SEPARATOR);

        player.setGameMode(GameMode.ADVENTURE);

        player.getInventory().clear();
        player.getActivePotionEffects().clear();

        player.setFoodLevel(20);
        player.setHealth(20);
        player.setExp(0);
        player.setLevel(0);

        player.teleport(Hub.get().getSpawn());

        ItemUtil.setItems(player);

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 2F);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Hub.get().getVisibilityMap().remove(uuid);
    }

}
