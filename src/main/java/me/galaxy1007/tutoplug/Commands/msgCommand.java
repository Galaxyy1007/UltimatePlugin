package me.galaxy1007.tutoplug.Commands;

import me.galaxy1007.tutoplug.UltimateMain;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class msgCommand implements CommandExecutor {

    private final UltimateMain plugin;

    public msgCommand(UltimateMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dit commando kan alleen door een speler worden gebruikt.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Gebruik: /message <speler> <bericht>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Speler niet gevonden of offline."));
            return true;
        }

        // Bouw het bericht op
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }

        String formattedMessage = ChatColor.GRAY + "[" + ChatColor.GOLD + "Whisper" + ChatColor.GRAY + "] "
                + ChatColor.AQUA + player.getName() + ChatColor.DARK_GRAY + " ➝ "
                + ChatColor.AQUA + target.getName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message.toString().trim();

        // Stuur het bericht naar beide spelers
        player.sendMessage(formattedMessage);
        target.sendMessage(formattedMessage);

        return true;
    }
}