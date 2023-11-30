package xyz.geik.glib.utils;

import com.cryptomorin.xseries.XMaterial;
import dev.dbassett.skullcreator.SkullCreator;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import xyz.geik.glib.chat.ChatUtils;
import org.bukkit.inventory.meta.Damageable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Item Helper class
 *
 * @author geik
 * @since 1.0
 */
public class ItemUtil {

    /**
     * Gets item with placeholder support
     * @param name item name
     * @param lore lore of item
     * @param material item material
     * @param hasGlow boolean of glow status
     * @param player offline player for placeholder values
     * @return
     */
    public static ItemStack getItem(String name, List<String> lore, int modelData, String material, boolean hasGlow, OfflinePlayer player) {
        ItemStack item = getSkull8Mat(material);
        ItemMeta meta = item.getItemMeta();
        if (modelData != 0)
            meta.setCustomModelData(modelData);
        if (hasGlow)
            glowMeta(meta);
        if (player != null && player.getPlayer() != null) {
            meta.setDisplayName(ChatUtils.color(player.getPlayer(), name));
            meta.setLore(ChatUtils.color(lore, player.getPlayer()));
        }
        else {
            meta.setDisplayName(ChatUtils.color(name));
            meta.setLore(ChatUtils.color(lore));
        }
        // Adds nbt tags to item if exists
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getSkull8Mat(String material) {
        ItemStack item;
        try {
            XMaterial mat = XMaterial.matchXMaterial(material).orElseThrow(NullPointerException::new);
            item = mat.parseItem();
        }
        catch (Exception e) {
            if (material.length() > 63)
                item = SkullCreator.itemFromBase64(material);
            else
                item = SkullCreator.itemFromName(material);
        }
        return item;
    }

    private static ItemMeta glowMeta(ItemMeta meta) {
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return meta;
    }

    /**
     * converts xmaterial to string
     * @param item Item
     * @return String of item
     */
    public static String getName(ItemStack item) {
        XMaterial material = XMaterial.matchXMaterial(item);
        String name = (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) ?
                item.getItemMeta().getDisplayName() :
                WordUtils.capitalize(material.name().replace('_', ' ').toLowerCase(Locale.ENGLISH));;
        return name;
    }


    /**
     * convert item to base64
     *
     * @param itemStack itemStack of item
     * @return base64 of item
     */
    public static String toBase64(ItemStack itemStack) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {

            dataOutput.writeObject(itemStack);

            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save itemStack.", e);
        }
    }

    /**
     * creates item stack by base64
     * @param encoded data
     * @return created item
     */
    public static ItemStack fromBase64(String encoded) {
        try (ByteArrayInputStream outputStream = new ByteArrayInputStream(Base64Coder.decodeLines(encoded));
             BukkitObjectInputStream dataOutput = new BukkitObjectInputStream(outputStream)) {

            return (ItemStack) dataOutput.readObject();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to read itemStack.", e);
        }
    }


    /**
     * Enchantment data to serialize
     * @param itemStack item
     * @return String of serialized data
     */
    public static String getEnchantments(ItemStack itemStack) {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Enchantment, Integer>> entries;
        if (itemStack.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta =(EnchantmentStorageMeta)itemStack.getItemMeta();
            entries = meta.getStoredEnchants().entrySet();
        } else {
            entries = itemStack.getEnchantments().entrySet();
        }
        for (Map.Entry<Enchantment, Integer> entry : entries) {
            builder.append((XMaterial.supports(13) ? entry.getKey().getKey().getKey() : entry.getKey().getName()).toUpperCase())
                    .append(':').append(entry.getValue()).append(',');
        }

        String enchantments = builder.length() > 1 ?
                builder.deleteCharAt(builder.lastIndexOf(",")).toString() : null;
        return enchantments;
    }


    /**
     * Gets modelId of item
     * @param itemStack of item
     * @return String of model data
     */
    public static String getModelId(ItemStack itemStack) {
        String modelId = null;
        if (XMaterial.supports(14) && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData())
            modelId = String.valueOf(itemStack.getItemMeta().getCustomModelData());
        return modelId;
    }

    /**
     * Gets durability of item
     * @param item itemstack
     * @param maxDurability max durability of item
     * @return durability as int
     */
    public static int getDurability(ItemStack item, int maxDurability) {
        int durability = 0;
        if (XMaterial.supports(13))
            durability = maxDurability - ((Damageable) item.getItemMeta()).getDamage();
        else
            durability = maxDurability + 1 - item.getDurability();
        return durability;
    }
}
