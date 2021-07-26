package com.customwrld.hub.menus;

import com.customwrld.customlib.smartinvs.ClickableItem;
import com.customwrld.customlib.smartinvs.SmartInventory;
import com.customwrld.customlib.smartinvs.content.InventoryContents;
import com.customwrld.customlib.smartinvs.content.InventoryProvider;
import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.hub.util.ItemUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ServerSelectorMenu implements InventoryProvider {

    public ServerSelectorMenu(Player player) {
        SmartInventory inventory = SmartInventory.builder()
                .provider(this)
                .size(3, 9)
                .title("Server Selector").build();
        inventory.open(player);
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(new ItemFactory(Material.GRAY_STAINED_GLASS_PANE).setName(Component.empty()).build()));

        contents.set(1, 1, ClickableItem.of(ItemUtil.PARKOUR_ITEM,
                e -> Bukkit.dispatchCommand(player, "queue parkour")));

        contents.set(1, 3, ClickableItem.of(ItemUtil.SURVIVAL_ITEM,
                e -> Bukkit.dispatchCommand(player, "queue survival")));

        contents.set(1, 5, ClickableItem.of(ItemUtil.SKYBLOCK_ITEM,
                e -> Bukkit.dispatchCommand(player, "queue skyblock")));

        contents.set(1, 7, ClickableItem.of(ItemUtil.PRACTICE_ITEM,
                e -> Bukkit.dispatchCommand(player, "queue practice")));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        this.init(player, contents);
    }
}
