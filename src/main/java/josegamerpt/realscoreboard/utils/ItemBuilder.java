package josegamerpt.realscoreboard.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    public static ItemStack createItem(Material material, int quantidade, int id, String nome) {
        ItemStack item = new ItemStack(material, quantidade, (short) id);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nome));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemLore(Material material, int quantidade, int id, String nome, List<String> desc) {
        ItemStack item = new ItemStack(material, quantidade, (short) id);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nome));
        ArrayList<String> lore = new ArrayList<String>();
        for (String s : desc) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
