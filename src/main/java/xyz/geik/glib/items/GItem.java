package xyz.geik.glib.items;

import de.leonhard.storage.Config;
import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GItem {

    public static ItemStack getItem(Config config, String path) {
        String name = config.getText(path + ".name");
        List<String> lore = config.getTextList(path + ".lore");
        ItemStack item;

        String material = config.getString(path + ".material");
        try {
            Material mat = Material.getMaterial(material);
            item = new ItemStack(mat, 1);
        }
        catch (Exception e) {
            if (material.length() > 63)
                item = SkullCreator.itemFromBase64(material);
            else
                item = SkullCreator.itemFromName(material);
        }

        ItemMeta meta = item.getItemMeta();

        if (config.contains(path + ".glow") && config.getBoolean(path + ".glow")) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
