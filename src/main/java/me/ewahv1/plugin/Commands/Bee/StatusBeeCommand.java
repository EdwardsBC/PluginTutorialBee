package me.ewahv1.plugin.Commands.Bee;

import me.ewahv1.plugin.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StatusBeeCommand implements CommandExecutor, TabCompleter {

    private ConfigManager config;

    public StatusBeeCommand(ConfigManager config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("Uso incorrecto del comando. Uso: /status <Entity>");
            return true;
        }

        String entity = args[0].toLowerCase();

        if (!entity.equals("bee")) {
            player.sendMessage("Entidad no reconocida.");
            return true;
        }

        try {
            boolean isAngry = (boolean) config.getAttribute("Angry");
            int strength = (int) config.getAttribute("Strength");

            String statusMessage = "Las abejas están " + (isAngry ? "enojadas" : "tranquilas") + " y tienen fuerza " + (strength > 0 ? strength : "0") + ".";
            player.sendMessage(statusMessage);
        } catch (Exception e) {
            player.sendMessage("Error al leer el archivo de configuración.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("Bee");
        }

        return completions;
    }
}
