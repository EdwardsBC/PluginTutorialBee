package me.ewahv1.plugin;

import me.ewahv1.plugin.Commands.Bee.SetBeeCommand;
import me.ewahv1.plugin.Commands.Bee.StatusBeeCommand;
import me.ewahv1.plugin.Database.DatabaseConnection;
import me.ewahv1.plugin.Listeners.Bee.BeeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private DatabaseConnection connection;

    @Override
    public void onEnable() {
        connection = new DatabaseConnection();

        // Registrar el comando
        this.getCommand("set").setExecutor(new SetBeeCommand());
        this.getCommand("set").setTabCompleter(new SetBeeCommand());

        this.getCommand("status").setExecutor(new StatusBeeCommand());
        this.getCommand("status").setTabCompleter(new StatusBeeCommand());

        // Registrar el listener
        getServer().getPluginManager().registerEvents(new BeeListener(), this);
    }

    @Override
    public void onDisable() {
        connection.close();
    }
}
