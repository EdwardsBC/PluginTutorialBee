package me.ewahv1.plugin;

import me.ewahv1.plugin.Commands.Bee.SetBeeCommand;
import me.ewahv1.plugin.Commands.Bee.StatusBeeCommand;
import me.ewahv1.plugin.Listeners.Bee.BeeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ConfigManager config;

    @Override
    public void onEnable() {
        config = new ConfigManager("ewahv1.plugin");

        // Pasar la instancia de ConfigManager a los comandos
        SetBeeCommand setBeeCommand = new SetBeeCommand(config);
        StatusBeeCommand statusBeeCommand = new StatusBeeCommand(config);

        // Registrar el comando
        this.getCommand("set").setExecutor(setBeeCommand);
        this.getCommand("set").setTabCompleter(setBeeCommand);

        this.getCommand("status").setExecutor(statusBeeCommand);
        this.getCommand("status").setTabCompleter(statusBeeCommand);

        // Pasar la instancia de ConfigManager al listener y registrar el listener
        BeeListener beeListener = new BeeListener(config);
        getServer().getPluginManager().registerEvents(beeListener, this);
    }

    @Override
    public void onDisable() {
        // No es necesario cerrar nada ya que no estamos usando una base de datos
    }
}
