package com.customwrld.hub.menus;

import com.customwrld.customlib.misc.GlowEnchantment;
import com.customwrld.customlib.smartinvs.ClickableItem;
import com.customwrld.customlib.smartinvs.SmartInventory;
import com.customwrld.customlib.smartinvs.content.InventoryContents;
import com.customwrld.customlib.smartinvs.content.InventoryProvider;
import com.customwrld.customlib.smartinvs.content.Pagination;
import com.customwrld.customlib.smartinvs.content.SlotIterator;
import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.commonlib.util.MC;
import com.customwrld.flame.util.Util;
import com.customwrld.hub.Hub;
import com.customwrld.hub.cosmetics.Cosmetic;
import com.customwrld.hub.cosmetics.CosmeticType;
import com.customwrld.hub.cosmetics.player.CosmeticPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

public class CosmeticListMenu implements InventoryProvider {

    private final CosmeticType cosmeticType;
    private final SmartInventory inventory;

    CosmeticListMenu(Player player, CosmeticType cosmeticType) {
        this.cosmeticType = cosmeticType;
        this.inventory = SmartInventory.builder()
                .provider(this)
                .size(5, 9)
                .title(cosmeticType.getName()).build();
        this.inventory.open(player, 0);
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(new ItemFactory(Material.GRAY_STAINED_GLASS_PANE).setName(Component.empty()).build()));

        this.logic(player, contents);
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        this.logic(player, contents);
    }

    private void logic(Player player, InventoryContents contents) {
        Pagination pagination = contents.pagination();

        ClickableItem[] items = new ClickableItem[cosmeticType.getCosmetics().size()];

        int i = 0;

        for (Cosmetic cosmetic : this.cosmeticType.getCosmetics()) {
            if (cosmetic.getCosmeticType() == this.cosmeticType) {
                CosmeticPlayer cosmeticPlayer = Hub.get().getCosmeticHandler().getPlayer(player);

                List<Component> lore = new ArrayList<>();
                lore.add(MC.Style.MENU_SEPARATOR);
                lore.add(cosmetic.hasPermission(player) ?
                        (cosmeticPlayer.getSelectedCosmetics().contains(cosmetic) ?
                                (Component.text("Click to deselect this Cosmetic!", MC.CC.RED.getTextColor())
                                        .decoration(TextDecoration.ITALIC, false)) :
                                (Component.text("Click to select this Cosmetic!", MC.CC.GREEN.getTextColor())
                                        .decoration(TextDecoration.ITALIC, false))) :
                        (Component.text("You do not have permission for this cosmetic!", MC.CC.RED.getTextColor())
                                .decoration(TextDecoration.ITALIC, false)));
                lore.add(MC.Style.MENU_SEPARATOR);

                ItemFactory cosmeticItem = new ItemFactory(cosmetic.getIcon())
                        .setName(cosmetic.getDisplayName()
                                .decoration(TextDecoration.ITALIC, false))
                        .setLore(lore);

                if(cosmeticPlayer.isSelected(cosmetic)) {
                    cosmeticItem.addFlags(ItemFlag.HIDE_ENCHANTS);
                    cosmeticItem.addEnchantment(GlowEnchantment.getGlowEnchantment());
                }

                items[i] = ClickableItem.of(cosmeticItem.build(),
                        e -> {
                            if(!cosmetic.hasPermission(player)) {
                                return;
                            }
                            if(cosmeticPlayer.isSelected(cosmetic)) {
                                cosmetic.remove(player);
                            } else {
                                Hub.get().getCosmeticHandler().deselectType(player, cosmeticType);
                                cosmeticPlayer.selectCosmetic(cosmetic);
                            }
                        });
                i++;
            }
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(27);

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 1, 0));

        Util.addNavigation(player, this.inventory, contents);
    }
}
