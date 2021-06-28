//package com.customwrld.hub.menus;
//
//import com.customwrld.hub.util.Util;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//
//import java.util.UUID;
//
//public class HubMenu implements InventoryProvider {
//
//    public HubMenu(Player player) {
//        SmartInventory inventory = SmartInventory.builder()
//                .id("menuGUI")
//                .provider(this)
//                .size(3, 9)
//                .title("Hub Menu").build();
//        inventory.open(player);
//    }
//
//    @Override
//    public void init(Player player, InventoryContents contents) {
//
//        UUID uuid = player.getUniqueId();
//
//        contents.fill(ClickableItem.empty(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).title(" ").build()));
//
//        contents.set(1, 1, ClickableItem.of(Util.TOGGLE_SPEED(player.getUniqueId()),
//                e -> {
//                    if (Util.toggledSpeed.contains(uuid)) {
//                        Util.toggledSpeed.remove(uuid);
//                        player.sendMessage(CC.GREEN + "You have toggled super speed on!");
//                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
//                    } else {
//                        Util.toggledSpeed.add(uuid);
//                        player.sendMessage(CC.RED + "You have toggled super speed off!");
//                        player.removePotionEffect(PotionEffectType.SPEED);
//                    }
//
//                }));
//
//        contents.set(1, 3, ClickableItem.of(Util.TOGGLE_VISIBILITY(player.getUniqueId()),
//                e -> {
//                    if (Util.toggledVisibility.contains(uuid)) {
//                        Util.toggledVisibility.remove(uuid);
//                        player.sendMessage(CC.GREEN + "You have toggled player visibility on!");
//                        VisibilityUtil.showServer(player);
//                    } else {
//                        Util.toggledVisibility.add(uuid);
//                        player.sendMessage(CC.RED + "You have toggled player visibility off!");
//                        VisibilityUtil.hideServer(player);
//                    }
//                }));
//
//        contents.set(1, 7, ClickableItem.of(Util.COSMETIC_SELECTOR,
//                e -> new CosmeticMenu(player)));
//    }
//
//    @Override
//    public void update(Player player, InventoryContents contents) {
//        UUID uuid = player.getUniqueId();
//
//        contents.set(1, 1, ClickableItem.of(Util.TOGGLE_SPEED(player.getUniqueId()),
//                e -> {
//                    if (Util.toggledSpeed.contains(uuid)) {
//                        Util.toggledSpeed.remove(uuid);
//                        player.sendMessage(CC.GREEN + "You have toggled super speed on!");
//                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
//                    } else {
//                        Util.toggledSpeed.add(uuid);
//                        player.sendMessage(CC.RED + "You have toggled super speed off!");
//                        player.removePotionEffect(PotionEffectType.SPEED);
//                    }
//
//                }));
//
//        contents.set(1, 3, ClickableItem.of(Util.TOGGLE_VISIBILITY(player.getUniqueId()),
//                e -> {
//                    if (Util.toggledVisibility.contains(uuid)) {
//                        Util.toggledVisibility.remove(uuid);
//                        player.sendMessage(CC.GREEN + "You have toggled player visibility on!");
//                        VisibilityUtil.showServer(player);
//                    } else {
//                        Util.toggledVisibility.add(uuid);
//                        player.sendMessage(CC.RED + "You have toggled player visibility off!");
//                        VisibilityUtil.hideServer(player);
//                    }
//                }));
//
//        contents.set(1, 7, ClickableItem.of(Util.COSMETIC_SELECTOR,
//                e -> new CosmeticMenu(player)));
//    }
//}
