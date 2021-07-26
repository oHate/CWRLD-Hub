package com.customwrld.hub.util;

import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.Hub;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;

public class ItemUtil {

    public static void setItems(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, ItemUtil.SERVER_SELECTOR);
        inventory.setItem(1, ItemUtil.SHOP_SELECTOR);
        inventory.setItem(4, ItemUtil.PROFILE_ITEM(player));
        inventory.setItem(7, ItemUtil.COSMETIC_SELECTOR);
        inventory.setItem(8, ItemUtil.VISIBILITY_ITEM(player.getUniqueId()));
        player.updateInventory();
    }

    public static ItemStack SERVER_SELECTOR = new ItemFactory(Material.CLOCK)
            .setName(Component.text()
                    .append(Component.text("Server Selector ", MC.CC.BLUE.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to select a server!", MC.CC.GRAY.getTextColor()))
            .build();

    public static ItemStack COSMETIC_SELECTOR = new ItemFactory(Material.BEACON)
            .setName(Component.text()
                    .append(Component.text("Cosmetic Selector ", MC.CC.AQUA.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to select a cosmetic!", MC.CC.GRAY.getTextColor()))
            .build();

    public static ItemStack SHOP_SELECTOR = new ItemFactory(Material.EMERALD)
            .setName(Component.text()
                    .append(Component.text("Shop ", MC.CC.GREEN.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                    .build())
            .setLore(Component.text("Right Click to open the shop!", MC.CC.GRAY.getTextColor()))
            .build();

    public static ItemStack PROFILE_ITEM(Player player) {
        return new ItemFactory(Material.PLAYER_HEAD)
                .setName(Component.text()
                        .append(Component.text("Profile ", MC.CC.LIGHT_PURPLE.getTextColor(), TextDecoration.BOLD))
                        .append(Component.text("(Right Click)", MC.CC.GRAY.getTextColor()))
                        .build())
                .setLore(Component.text("Right Click to open your profile!", MC.CC.GRAY.getTextColor()))
                .setPlayerProfile(player.getPlayerProfile())
                .build();
    }

    public static ItemStack VISIBILITY_ITEM(UUID uuid) {
        return Visibility.values()[Hub.get().getVisibilityMap().get(uuid)].getItemStack();
    }

    public static final ItemStack PARKOUR_ITEM = new ItemFactory(Material.GOLDEN_BOOTS)
            .setName(Component.text("PARKOUR", MC.CC.GOLD.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
            )
            .setLore(
                    Component.text("Fun, Casual, Competitive", MC.CC.DARK_GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Parkour is where you maneuver", MC.CC.GRAY.getTextColor()),
                    Component.text("through various obstacles without", MC.CC.GRAY.getTextColor()),
                    Component.text("falling or failing", MC.CC.GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Unique Features:", MC.CC.GOLD.getTextColor()),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Timed Leaderboards", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Completion/Record Log", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Course Checkpoint Saving", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Unique Maps", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect", MC.CC.GOLD.getTextColor()),
                    Component.text()
                            .append(Component.text("0 ", MC.CC.GOLD.getTextColor()))
                            .append(Component.text("Players are Online!", MC.CC.GRAY.getTextColor()))
                            .build()
            )
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    public static final ItemStack SURVIVAL_ITEM = new ItemFactory(Material.GRASS_BLOCK)
            .setName(Component.text("SURVIVAL", MC.CC.GREEN.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
            )
            .setLore(
                    Component.text("Survival, Peaceful, Build", MC.CC.DARK_GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("An expansive survival experience", MC.CC.GRAY.getTextColor()),
                    Component.text("full of features to keep you busy", MC.CC.GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Unique Features:", MC.CC.GREEN.getTextColor()),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Land Claiming", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("30k x 30k Border", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Global Events", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Achievements", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect", MC.CC.GREEN.getTextColor()),
                    Component.text()
                            .append(Component.text("0 ", MC.CC.GREEN.getTextColor()))
                            .append(Component.text("Players are Online!", MC.CC.GRAY.getTextColor()))
                            .build()
            )
            .build();

    public static final ItemStack PRACTICE_ITEM = new ItemFactory(Material.IRON_SWORD)
            .setName(Component.text("PRACTICE", MC.CC.RED.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
            )
            .setLore(
                    Component.text("PvP, Competitive, Fun", MC.CC.DARK_GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Practice your pvp skills and", MC.CC.GRAY.getTextColor()),
                    Component.text("become the best of the best", MC.CC.GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Unique Features:", MC.CC.RED.getTextColor()),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Player Hosted Events", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Duels", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Leaderboards", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Custom Kits", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Tournaments", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect", MC.CC.RED.getTextColor()),
                    Component.text()
                            .append(Component.text("0 ", MC.CC.RED.getTextColor()))
                            .append(Component.text("Players are Online!", MC.CC.GRAY.getTextColor()))
                            .build()
            )
            .addFlags(ItemFlag.HIDE_ATTRIBUTES)
            .build();

    public static final ItemStack SKYBLOCK_ITEM = new ItemFactory(Material.OAK_SAPLING)
            .setName(Component.text("SKYBLOCK", MC.CC.AQUA.getTextColor())
                    .decoration(TextDecoration.BOLD, true)
            )
            .setLore(
                    Component.text("Survival, Challenge, Island", MC.CC.DARK_GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Spawn on a floating island with", MC.CC.GRAY.getTextColor()),
                    Component.text("very limited resources, expand", MC.CC.GRAY.getTextColor()),
                    Component.text("and grow your island ", MC.CC.GRAY.getTextColor()),
                    Component.empty(),
                    Component.text("Unique Features:", MC.CC.AQUA.getTextColor()),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Solo & Co-Op", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Warzone", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Player Shops/Warps", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Island Upgrades", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.text()
                            .append(Component.text("» ", MC.CC.GRAY.getTextColor())
                                    .decoration(TextDecoration.BOLD, true))
                            .append(Component.text("Minions", MC.CC.WHITE.getTextColor()))
                            .build(),
                    Component.empty(),
                    Component.text("▶ Click to Connect", MC.CC.AQUA.getTextColor()),
                    Component.text()
                            .append(Component.text("0 ", MC.CC.AQUA.getTextColor()))
                            .append(Component.text("Players are Online!", MC.CC.GRAY.getTextColor()))
                            .build()
            )
            .build();

    public static Object getValue(Object instance, String field) throws Exception {
        Field f = instance.getClass().getDeclaredField(field);
        f.setAccessible(true);
        return f.get(instance);
    }
}
