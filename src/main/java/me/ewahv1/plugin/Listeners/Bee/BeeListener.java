package me.ewahv1.plugin.Listeners.Bee;

import me.ewahv1.plugin.ConfigManager;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BeeListener implements Listener {

    private ConfigManager config;

    public BeeListener(ConfigManager config) {
        this.config = config;
    }

    @EventHandler
    public void onBeeSpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Bee) {
            Bee bee = (Bee) event.getEntity();

            try {
                // Leer el archivo de configuración
                config.reload();

                boolean angry = Boolean.parseBoolean((String) config.getAttribute("Angry"));
                int strength = Integer.parseInt((String) config.getAttribute("Strength"));

                if (angry) {
                    Player nearestPlayer = null;
                    double nearestDistance = Double.MAX_VALUE;

                    for (Entity entity : bee.getWorld().getEntities()) {
                        if (entity instanceof Player) {
                            double distance = entity.getLocation().distance(bee.getLocation());

                            if (distance < nearestDistance) {
                                nearestPlayer = (Player) entity;
                                nearestDistance = distance;
                            }
                        }
                    }

                    if (nearestPlayer != null) {
                        bee.setTarget(nearestPlayer);
                        bee.setAnger(Integer.MAX_VALUE);
                    }
                }

                if (strength > 0) {
                    bee.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, strength - 1, false, false));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
