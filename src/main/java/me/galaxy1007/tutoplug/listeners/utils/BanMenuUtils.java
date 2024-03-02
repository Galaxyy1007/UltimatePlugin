package me.galaxy1007.tutoplug.listeners.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BanMenuUtils {

    public static void openBanMenu(Player p) {
        ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());

        Inventory bangui = Bukkit.createInventory(p, 45, ChatColor.BLUE + "Player List");

        for (Player player : list) {

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();

            meta.setDisplayName(player.getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + player.getHealth());
            lore.add(ChatColor.GOLD + "EXP: " + ChatColor.AQUA + player.getExp());
            meta.setLore(lore);
            playerHead.setItemMeta(meta);

            bangui.addItem(playerHead);
        }
        p.openInventory(bangui);
        }
        public static void openConfirmBanMenu(Player p, Player whoToBan){

            Inventory confirmBanmenu = Bukkit.createInventory(p, 9, ChatColor.RED + "Ban deze Speler");

            ItemStack ban = new ItemStack(Material.WOODEN_AXE, 1);
            ItemMeta ban_meta = ban.getItemMeta();
            ban_meta.setDisplayName(ChatColor.DARK_GREEN + "Klik hier om deze speler te verbannen!");
            ban.setItemMeta(ban_meta);
            confirmBanmenu.setItem(0, ban);

            //Add player
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta player_meta = playerHead.getItemMeta();
            player_meta.setDisplayName(whoToBan.getDisplayName());
            playerHead.setItemMeta(player_meta);
            confirmBanmenu.setItem(4, playerHead);

            //Cancel option
            ItemStack cancel = new ItemStack(Material.BARRIER, 1);
            ItemMeta cancel_meta = cancel.getItemMeta();
            cancel_meta.setDisplayName(ChatColor.RED + "Annuleren");
            cancel.setItemMeta(cancel_meta);
            confirmBanmenu.setItem(8, cancel);

            p.openInventory(confirmBanmenu);
        }
    }

