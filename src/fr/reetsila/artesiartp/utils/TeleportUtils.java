package fr.reetsila.artesiartp.utils;

import fr.reetsila.artesiartp.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    static Main plugin;

    public TeleportUtils(Main plugin){
        this.plugin = plugin;
    }

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static{
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location generateLocation(Player player){
        Random random = new Random();

        int x = random.nextInt(2000);
        int z = random.nextInt(2000);
        // Hauteur
        int y = 160;

        Location randomLocation = new Location(player.getWorld(), x, y, z);
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(Player player){
        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)){
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }
}
