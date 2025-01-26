package me.galaxy1007.tutoplug.managers;

import me.galaxy1007.tutoplug.UltimateMain;
import me.galaxy1007.tutoplug.utils.ItemSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class BackpackManager {

    private final UltimateMain plugin;
    private final NamespacedKey backpackIdKey;
    private final NamespacedKey backpackContentsKey;
    private final NamespacedKey backpackAutoCollectionKey;
    private final HashMap<UUID, Inventory> backpackInventories;

    public BackpackManager(UltimateMain plugin) {
        this.plugin = plugin;
        this.backpackIdKey = new NamespacedKey(plugin, "backpack_id");
        this.backpackContentsKey = new NamespacedKey(plugin, "backpack_contents");
        this.backpackAutoCollectionKey = new NamespacedKey(plugin, "backpack_autocollect");
        this.backpackInventories = new HashMap<>();
    }

    public ItemStack createBackpack() {
        ItemStack backpack = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = backpack.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Backpack");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "A portable storage container");
            lore.add(ChatColor.GRAY + "Right-click to open");
            meta.setLore(lore);

            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(this.backpackIdKey, PersistentDataType.STRING, UUID.randomUUID().toString());
            container.set(this.backpackAutoCollectionKey, PersistentDataType.BOOLEAN, false);
            backpack.setItemMeta(meta);
        }

        return backpack;
    }

    public boolean registerRecipe() {
        NamespacedKey recipeKey = new NamespacedKey(plugin, "backpack_recipe");
        ShapedRecipe recipe = new ShapedRecipe(recipeKey, createBackpack());
        recipe.shape("LLL", "LCL", "LLL");
        recipe.setIngredient('L', Material.LEATHER);
        recipe.setIngredient('C', Material.CHEST);

        return Bukkit.addRecipe(recipe);
    }

    public Inventory getBackpackInventory(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItemMeta() == null) return null;

        ItemMeta meta = itemStack.getItemMeta();
        if (!meta.getPersistentDataContainer().has(this.backpackIdKey, PersistentDataType.STRING)) return null;

        var uuid = UUID.fromString(meta.getPersistentDataContainer().get(this.backpackIdKey, PersistentDataType.STRING));
        var backpackInventory = backpackInventories.computeIfAbsent(uuid, id -> {
            return Bukkit.createInventory(null, 27, ChatColor.GOLD + "Backpack");
        });

        var itemContents = meta.getPersistentDataContainer().get(this.backpackContentsKey, PersistentDataType.STRING);
        if (itemContents != null) {
            ItemStack[] items = ItemSerializer.itemStackArrayFromBase64(itemContents);
            backpackInventory.setContents(items);
        }
        return backpackInventory;
    }

    public void saveBackpackContents(Inventory inventory, ItemStack backpack) {
        var items = inventory.getContents();
        var itemsString = ItemSerializer.itemStackArrayToBase64(items);
        var itemMeta = backpack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(backpackContentsKey, PersistentDataType.STRING, itemsString);
        backpack.setItemMeta(itemMeta);
    }

    public boolean toggleAutoCollect(ItemStack itemStack) {
        var itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        boolean current = container.get(this.backpackAutoCollectionKey, PersistentDataType.BOOLEAN);
        container.set(this.backpackAutoCollectionKey, PersistentDataType.BOOLEAN, !current);
        itemStack.setItemMeta(itemMeta);
        return !current;
    }

    public UUID getBackpackId(Inventory inventory) {
        return backpackInventories.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(inventory))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public boolean isBackpack(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItemMeta() == null) return false;

        ItemMeta meta = itemStack.getItemMeta();
        return meta.getPersistentDataContainer().has(this.backpackIdKey, PersistentDataType.STRING);
    }

    public ItemStack[] loadContents(ItemStack backpack) {
        if (!isBackpack(backpack) || backpack.getItemMeta() == null) return new ItemStack[0];

        ItemMeta meta = backpack.getItemMeta();
        String serialized = meta.getPersistentDataContainer().get(backpackContentsKey, PersistentDataType.STRING);

        if (serialized == null) return new ItemStack[0];

        return ItemSerializer.itemStackArrayFromBase64(serialized);
    }

    public boolean isAutoCollectEnabled(ItemStack backpack) {
        if (!isBackpack(backpack) || backpack.getItemMeta() == null) return false;

        ItemMeta meta = backpack.getItemMeta();
        return meta.getPersistentDataContainer().getOrDefault(backpackAutoCollectionKey, PersistentDataType.BOOLEAN, false);
    }

    public boolean isBackpackItemById(ItemStack item, UUID id) {
        if (!item.hasItemMeta()) return false;

        var uuidString = item.getItemMeta().getPersistentDataContainer().get(backpackIdKey, PersistentDataType.STRING);
        if (uuidString == null) return false;

        return uuidString.equalsIgnoreCase(id.toString());
    }

}
