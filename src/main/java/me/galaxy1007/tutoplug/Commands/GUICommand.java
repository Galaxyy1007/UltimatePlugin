package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(player, 9 * 3, ChatColor.GOLD + "UltimatePlugin GUI");

        ItemStack getDiamondButton = new ItemStack(Material.DIAMOND);
        ItemMeta diamondmeta = getDiamondButton.getItemMeta();
        diamondmeta.setDisplayName(ChatColor.BLUE + "Get Diamond");
        getDiamondButton.setItemMeta(diamondmeta);

        ItemStack clearInventoryButton = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta clearInventoryMeta = clearInventoryButton.getItemMeta();
        clearInventoryMeta.setDisplayName("Clear Inventory");
        clearInventoryButton.setItemMeta(clearInventoryMeta);

        ItemStack clearWeatherButton = new ItemStack(Material.SUNFLOWER);
        ItemMeta clearWeatherMeta = clearWeatherButton.getItemMeta();
        clearWeatherMeta.setDisplayName("Clear Weather");
        clearWeatherButton.setItemMeta(clearWeatherMeta);

        inventory.setItem(11, getDiamondButton);
        inventory.setItem(13, clearInventoryButton);
        inventory.setItem(15, clearWeatherButton);

        player.openInventory(inventory);
        player.setMetadata("OpenedMenu", new FixedMetadataValue(UltimateMain.getInstance(), inventory));

        return true;
    }
}
