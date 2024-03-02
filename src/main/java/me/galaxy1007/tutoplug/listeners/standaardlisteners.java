package me.galaxy1007.tutoplug.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class standaardlisteners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.RED + "Welkom op de server van MeesGames");

    }
    @EventHandler
    public void onBlockBreak (BlockBreakEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.DARK_PURPLE + "Je hebt een blokje naar atomen geslagen");

    }

    @EventHandler
    public void OnBlockPlace (BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "Je hebt een blokje geplaatst!");
    }




}
