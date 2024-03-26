package me.ewahv1.plugin.Commands.Bee;

import me.ewahv1.plugin.Database.DatabaseConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class SetBeeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
                    PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("UPDATE dif_bee_settings SET Angry = ? WHERE ID = 1");
                    statement.setBoolean(1, angry);
                    statement.executeUpdate();
                } catch (Exception e) {
                    player.sendMessage("Error al actualizar la base de datos.");
                }
                break;
            case "strength":
                try {
                    int strength = Integer.parseInt(value);
                    PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement("UPDATE dif_bee_settings SET Strength = ? WHERE ID = 1");
                    statement.setInt(1, strength);
                    statement.executeUpdate();
                } catch (NumberFormatException e) {
                    player.sendMessage("Por favor, introduce un número válido para 'strength'.");
                } catch (Exception e) {
                    player.sendMessage("Error al actualizar la base de datos.");
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
