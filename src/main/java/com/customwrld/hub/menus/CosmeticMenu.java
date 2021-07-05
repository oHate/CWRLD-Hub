package com.customwrld.hub.menus;

import com.customwrld.customlib.smartinvs.ClickableItem;
import com.customwrld.customlib.smartinvs.SmartInventory;
import com.customwrld.customlib.smartinvs.content.InventoryContents;
import com.customwrld.customlib.smartinvs.content.InventoryProvider;
import com.customwrld.customlib.util.ItemFactory;
import com.customwrld.commonlib.util.MC;
import com.customwrld.hub.cosmetics.CosmeticType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;
import java.util.List;

public class CosmeticMenu implements InventoryProvider {

    private int unlocked;

    public CosmeticMenu(Player player) {
        SmartInventory inventory = SmartInventory.builder()
                .provider(this)
                .size(3, 9)
                .title("Cosmetic Selection Menu").build();
        inventory.open(player);
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(new ItemFactory(Material.GRAY_STAINED_GLASS_PANE).setName(Component.empty()).build()));

        this.unlocked = 0;
        CosmeticType.ARMOR.getCosmetics().forEach(cosmetic -> { cosmetic.hasPermission(player); unlocked++; });

        contents.set(1, 1, ClickableItem.of(new ItemFactory(CosmeticType.ARMOR.getIcon())
                        .setName(CosmeticType.ARMOR.getDisplay())
                        .setLore(this.getLore(player, CosmeticType.ARMOR))
                        .addFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .build(),
                e -> new CosmeticListMenu(player, CosmeticType.ARMOR)));

        this.unlocked = 0;
        CosmeticType.PARTICLE.getCosmetics().forEach(cosmetic -> { cosmetic.hasPermission(player); unlocked++; });

        contents.set(1, 3, ClickableItem.of(new ItemFactory(CosmeticType.PARTICLE.getIcon())
                        .setName(CosmeticType.PARTICLE.getDisplay())
                        .setLore(this.getLore(player, CosmeticType.PARTICLE))
                        .build(),
                e -> new CosmeticListMenu(player, CosmeticType.PARTICLE)));

        this.unlocked = 0;
        CosmeticType.GADGET.getCosmetics().forEach(cosmetic -> { cosmetic.hasPermission(player); unlocked++; });

        contents.set(1, 5, ClickableItem.of(new ItemFactory(CosmeticType.GADGET.getIcon())
                        .setName(CosmeticType.GADGET.getDisplay())
                        .setLore(this.getLore(player, CosmeticType.GADGET))
                        .build(),
                e -> new CosmeticListMenu(player, CosmeticType.GADGET)));

        this.unlocked = 0;
        CosmeticType.PET.getCosmetics().forEach(cosmetic -> { cosmetic.hasPermission(player); unlocked++; });

        contents.set(1, 7, ClickableItem.of(new ItemFactory(CosmeticType.PET.getIcon())
                        .setName(CosmeticType.PET.getDisplay())
                        .setLore(this.getLore(player, CosmeticType.PET))
                        .build(),
                e -> new CosmeticListMenu(player, CosmeticType.PET)));
    }

    private List<Component> getLore(Player player, CosmeticType type) {
        this.unlocked = 0;
        type.getCosmetics().forEach(cosmetic -> { cosmetic.hasPermission(player); this.unlocked++; });

        return Arrays.asList(
                Component.text()
                        .append(Component.text("Unlocked: ", MC.CC.GRAY.getTextColor()))
                        .append(Component.text(this.unlocked + "/" + type.getCosmetics().size(), MC.CC.RED.getTextColor()))
                        .append(Component.text(" (" + (int)((type.getCosmetics().size() * 100.0f) / this.unlocked) + "%)", MC.CC.DARK_GRAY.getTextColor()))
                        .build()
                        .decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Click to browse!", MC.CC.YELLOW.getTextColor())
                        .decoration(TextDecoration.ITALIC, false));
    }
}
