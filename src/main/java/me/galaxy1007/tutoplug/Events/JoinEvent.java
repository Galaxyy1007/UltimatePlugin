package me.galaxy1007.tutoplug.Events;

import me.galaxy1007.tutoplug.Tutoplug;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener{

    Tutoplug plugin;

    public JoinEvent(Tutoplug plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for(int i = 0; i < plugin.invisible_list.size(); i++) {
            player.hidePlayer(plugin, plugin.invisible_list.get(i));
        }

    }

}
