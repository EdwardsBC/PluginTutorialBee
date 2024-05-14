package me.ewahv1.plugin.Commands.Bee;

import me.ewahv1.plugin.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetBeeCommand implements CommandExecutor, TabCompleter {

    private ConfigManager config;

    public SetBeeCommand(ConfigManager config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Recargar el archivo de configuración
        config.reload();

        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 3) {
            player.sendMessage("Uso incorrecto del comando. Uso: /set <Angry|Strength> <Entity> <Value>");
            return true;
        }

        String attribute = args[0].toLowerCase();
        String entity = args[1].toLowerCase();
        String value = args[2];

        if (!entity.equals("bee")) {
            player.sendMessage("Entidad no reconocida.");
            return true;
        }

        switch (attribute) {
            case "angry":
                try {
                    boolean angry = Boolean.parseBoolean(value);
                    config.setAttribute("Angry", angry);
                } catch (Exception e) {
                    player.sendMessage("Error al actualizar el archivo de configuración.");
                }
                break;
            case "strength":
                try {
                    int strength = Integer.parseInt(value);
                    config.setAttribute("Strength", strength);
                } catch (NumberFormatException e) {
                    player.sendMessage("Por favor, introduce un número válido para 'strength'.");
                } catch (Exception e) {
                    player.sendMessage("Error al actualizar el archivo de configuración.");
                }
                break;
            default:
                player.sendMessage("Atributo no reconocido.");
                return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("Angry");
            completions.add("Strength");
        } else if (args.length == 2) {
            completions.add("Bee");
        } else if (args.length == 3 && args[0].equalsIgnoreCase("Angry")) {
            completions.add("true");
            completions.add("false");
        }

        return completions;
    }
}
