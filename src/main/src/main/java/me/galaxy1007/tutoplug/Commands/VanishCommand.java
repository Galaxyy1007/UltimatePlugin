package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    UltimateMain plugin;

    public VanishCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if(plugin.invisible_list.contains(player)){
                for(Player people : Bukkit.getOnlinePlayers()){
                    people.showPlayer(plugin, player);
                }
                plugin.invisible_list.remove(player);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.RED  + "Vanish is uitgeschakeld");
                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " joined the game");
            }else if(!plugin.invisible_list.contains(player)){
                for(Player people : Bukkit.getOnlinePlayers()){
                    people.hidePlayer(plugin, player);
                }
                plugin.invisible_list.add(player);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.YELLOW   + "Vanish is ingeschakeld");
                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " left the game");
                
            }
            else {
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.RED + "Error: " + ChatColor.GRAY + " Neem contact op met de beheerder als deze fout zich blijft voortdoen");
            }
        }
        return true;
    }

}
