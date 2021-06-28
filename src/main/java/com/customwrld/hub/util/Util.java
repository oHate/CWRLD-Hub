package com.customwrld.hub.util;

import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.customlib.util.MC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;

public class Util {

    public static Set<UUID> toggledSpeed = new HashSet<>();
    public static Set<UUID> toggledVisibility = new HashSet<>();

    public static void setItems(Player player) {
        org.bukkit.inventory.Inventory inventory = player.getInventory();
        inventory.setItem(0, Util.SERVER_SELECTOR);
        inventory.setItem(1, Util.COSMETIC_SELECTOR);
        player.updateInventory();
    }

    public static ItemStack SERVER_SELECTOR = new ItemFactory(Material.CLOCK)
            .setName(Component.text("Server Selector")
                    .color(MC.CC.BLUE.getTextColor())
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(Component.text("Right-Click to select a server!")
                    .color(MC.CC.GRAY.getTextColor())
                    .decoration(TextDecoration.ITALIC, false))
            .build();

    public static ItemStack COSMETIC_SELECTOR = new ItemFactory(Material.BEACON)
            .setName(Component.text("Cosmetic Selector")
                    .color(MC.CC.AQUA.getTextColor())
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(Component.text("Right-Click to select a cosmetic!")
                    .color(MC.CC.GRAY.getTextColor())
                    .decoration(TextDecoration.ITALIC, false))
            .build();

    public static final ItemStack PARKOUR_ITEM = new ItemFactory(Material.GOLDEN_BOOTS)
            .setName(Component.text("PARKOUR")
                    .color(MC.CC.GOLD.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(
                    Component.text("Fun, Casual, Competitive")
                            .color(MC.CC.DARK_GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Parkour is where you maneuver")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("through various obstacles without")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("falling or failing")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Unique Features:")
                            .color(MC.CC.GOLD.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Timed Leaderboards")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Completion/Record Log")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Course Checkpoint Saving")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Unique Maps")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect")
                            .color(MC.CC.GOLD.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("0 ")
                                    .color(MC.CC.GOLD.getTextColor()))
                            .append(Component.text("Players are Online!")
                                    .color(MC.CC.GRAY.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build()
            )
            .build();

    public static final ItemStack SURVIVAL_ITEM = new ItemFactory(Material.GRASS_BLOCK)
            .setName(Component.text("SURVIVAL")
                    .color(MC.CC.GREEN.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(
                    Component.text("Survival, Peaceful, Build")
                            .color(MC.CC.DARK_GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("An expansive survival experience")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("full of features to keep you busy")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Unique Features:")
                            .color(MC.CC.GREEN.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Land Claiming")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("30k x 30k Border")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Global Events")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Achievements")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect")
                            .color(MC.CC.GREEN.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("0 ")
                                    .color(MC.CC.GREEN.getTextColor()))
                            .append(Component.text("Players are Online!")
                                    .color(MC.CC.GRAY.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build()
            )
            .build();

    public static final ItemStack PRACTICE_ITEM = new ItemFactory(Material.IRON_SWORD)
            .setName(Component.text("PRACTICE")
                    .color(MC.CC.RED.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(
                    Component.text("PvP, Competitive, Fun")
                            .color(MC.CC.DARK_GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Practice your pvp skills and")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("become the best of the best")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Unique Features:")
                            .color(MC.CC.RED.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Player Hosted Events")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Duels")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Leaderboards")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Custom Kits")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Tournaments")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect")
                            .color(MC.CC.RED.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("0 ")
                                    .color(MC.CC.RED.getTextColor()))
                            .append(Component.text("Players are Online!")
                                    .color(MC.CC.GRAY.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build()
            )
            .build();

    public static final ItemStack SKYBLOCK_ITEM = new ItemFactory(Material.OAK_SAPLING)
            .setName(Component.text("SKYBLOCK")
                    .color(MC.CC.AQUA.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
                    .decoration(TextDecoration.ITALIC, false))
            .setLore(
                    Component.text("Survival, Challenge, Island")
                            .color(MC.CC.DARK_GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Spawn on a floating island with")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("very limited resources, expand")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text("and grow your island ")
                            .color(MC.CC.GRAY.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.empty(),
                    Component.text("Unique Features:")
                            .color(MC.CC.AQUA.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Solo & Co-Op")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Warzone")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Player Shops/Warps")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Island Upgrades")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.text()
                            .append(Component.text("» ")
                                    .color(MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Minions")
                                    .color(MC.CC.WHITE.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect")
                            .color(MC.CC.AQUA.getTextColor())
                            .decoration(TextDecoration.ITALIC, false),
                    Component.text()
                            .append(Component.text("0 ")
                                    .color(MC.CC.AQUA.getTextColor()))
                            .append(Component.text("Players are Online!")
                                    .color(MC.CC.GRAY.getTextColor()))
                            .decoration(TextDecoration.ITALIC, false)
                            .build()
            )
            .build();

//    public static ItemStack TOGGLE_SPEED(UUID uuid) {
//        if (Util.toggledSpeed.contains(uuid)) {
//            return new ItemBuilder(Material.GUNPOWDER).title("&6&lSuper Speed: &cDisabled").lores("&7Left-Click to enable super speed!").amount(1).build();
//        }
//        return new ItemBuilder(Material.SUGAR).title("&6&lSuper Speed: &aEnabled").lores("&7Left-Click to disable super speed!").amount(1).build();
//    }
//
//    public static ItemStack TOGGLE_VISIBILITY(UUID uuid) {
//        if (Util.toggledVisibility.contains(uuid)) {
//            return new ItemBuilder(Material.GRAY_DYE).title("&6&lPlayer Visibility: &cDisabled").lores("&7Left-Click to enable player visibility!").amount(1).build();
//        }
//        return new ItemFactory(Material.LIME_DYE).title("&6&lPlayer Visibility: &aEnabled").lores("&7Left-Click to disable player visibility!").amount(1).build();
//    }

    public static Object getValue(Object instance, String field) throws Exception {
        Field f = instance.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f.get(instance);
    }
}
