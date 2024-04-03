package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.Tutoplug;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    Tutoplug plugin;

    public VanishCommand(Tutoplug plugin) {
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
                player.sendMessage(ChatColor.RED   + "Vanish is uitgeschakeld");
            }else if(!plugin.invisible_list.contains(player)){
                for(Player people : Bukkit.getOnlinePlayers()){
                    people.hidePlayer(plugin, player);
                }
                plugin.invisible_list.add(player);
                player.sendMessage(ChatColor.BLUE   + "Vanish is ingeschakeld");
            }
        }


        return true;
    }

}
