package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VliegCommand implements CommandExecutor {

    UltimateMain plugin;

    public VliegCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.fly_list.contains(player) && player.hasPermission("UltimatePlugin.fly")) {
                for(Player people : Bukkit.getOnlinePlayers()){
                    player.setAllowFlight(false);
                }
                plugin.fly_list.remove(player);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Vliegen voor " + ChatColor.BLUE + player.getName() + ChatColor.GRAY + " is uitgeschakeld!");
            }else if(!plugin.fly_list.contains(player) && player.hasPermission("UltimatePlugin.fly")){
                for(Player people : Bukkit.getOnlinePlayers()){
                    player.setAllowFlight(true);
                }
                plugin.fly_list.add(player);
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Vliegen voor " + ChatColor.BLUE + player.getName() + ChatColor.GRAY + " is ingeschakeld");

            } else {
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "UltimatePlugin" + ChatColor.GRAY + "]: " + ChatColor.RED + "Je hebt geen rechten om dit commando uit te voeren!");
            }

        }
        return true;
    }


}
