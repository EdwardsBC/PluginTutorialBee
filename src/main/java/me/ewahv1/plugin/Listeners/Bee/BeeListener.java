package me.ewahv1.plugin.Listeners.Bee;

import me.ewahv1.plugin.Database.DatabaseConnection;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BeeListener implements Listener {

    @EventHandler
    public void onBeeSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Bee) {
            Bee bee = (Bee) event.getEntity();

            try {
                PreparedStatement psHostility = DatabaseConnection.getConnection().prepareStatement("SELECT Angry, Strength FROM dif_bee_settings WHERE ID = 1");
                ResultSet rs = psHostility.executeQuery();

                if (rs.next()) {
                    boolean angry = rs.getInt("Angry") == 1;
                    int strength = rs.getInt("Strength");

                    if (angry) {
                        Player nearestPlayer = bee.getWorld().getPlayers().stream()
                                .min((p1, p2) -> (int) (p1.getLocation().distance(bee.getLocation()) - p2.getLocation().distance(bee.getLocation())))
                                .orElse(null);
                        if (nearestPlayer != null) {
                            bee.setTarget(nearestPlayer);
                        }
                    }

                    if (strength > 0) {
                        bee.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, strength - 1, false, false));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
