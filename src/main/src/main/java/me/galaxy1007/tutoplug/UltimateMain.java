package me.galaxy1007.tutoplug;

import me.galaxy1007.tutoplug.Commands.GUICommand;
import me.galaxy1007.tutoplug.Commands.VanishCommand;
import me.galaxy1007.tutoplug.Commands.VliegCommand;
import me.galaxy1007.tutoplug.Events.OnJoinVanishEvent;
import me.galaxy1007.tutoplug.listeners.GUIListener;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class UltimateMain extends JavaPlugin {

    public ArrayList<Player> invisible_list = new ArrayList<>();
    public ArrayList<Player> fly_list = new ArrayList<>();

    public static Plugin getInstance() {
        return null;
    }

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new VliegCommand(this));
        getCommand("gui").setExecutor(new GUICommand());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new OnJoinVanishEvent(this), this);
        System.out.println(Color.BLUE + "[UltimatePlugin] Deze plugin staat ingeschakeld");
    }


    @Override
    public void onDisable() {
        System.out.println("[UltimatePlugin] Deze plugin staat uit");
        // Plugin shutdown logic
    }
}

