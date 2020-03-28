package fr.reetsila.artesiartp.commands;

import fr.reetsila.artesiartp.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class RandomTPCommand implements CommandExecutor {

    private Map<String, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {

                if (cooldowns.containsKey(player.getName())){
                    int seconds = 300;
                    long timeleft = ((cooldowns.get(player.getName()) / 1000) + seconds) - (System.currentTimeMillis() / 1000);
                    if (timeleft > 0){
                        player.sendMessage("§8[§e§l!§8] §cIl te reste " + timeleft + " secondes avant d'utiliser à nouveau cette commande !");
                        return true;
                    }
                }

                cooldowns.put(player.getName(), System.currentTimeMillis());

                Location randomLocation = TeleportUtils.findSafeLocation(player);

                player.teleport(randomLocation);
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 255));

                player.sendMessage("§6[§eArtesia§6] §bVous avez été téléporter de façon aléatoire.");
                player.sendMessage("§7(§cTP§7) §aEmplacement: §2" + randomLocation.getX() + " §2" + randomLocation.getY() + " §2" + randomLocation.getZ());
            }
        }
        return true;
    }
}
