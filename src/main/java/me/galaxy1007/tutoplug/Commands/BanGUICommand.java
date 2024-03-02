package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.listeners.utils.BanMenuUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {

        if (sender instanceof Player){

            Player p = (Player) sender;

            BanMenuUtils.openBanMenu(p);
        }
        return false;
    }
}
