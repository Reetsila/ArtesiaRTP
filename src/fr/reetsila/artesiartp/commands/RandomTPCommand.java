package fr.reetsila.artesiartp.commands;

import fr.reetsila.artesiartp.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RandomTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                Location randomLocation = TeleportUtils.findSafeLocation(player);

                player.teleport(randomLocation);
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 250, 255));

                player.sendMessage("§6[§eArtesia§6] §bVous avez été téléporter de façon aléatoire.");
                player.sendMessage("§7(§cTP§7) §aEmplacement: §2" + randomLocation.getX() + " §2" + randomLocation.getY() + " §2" + randomLocation.getZ());
            }
        }
        return true;
    }
}
