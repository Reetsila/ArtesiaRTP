package fr.reetsila.artesiartp;

import fr.reetsila.artesiartp.commands.RandomTPCommand;
import fr.reetsila.artesiartp.utils.TeleportUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        TeleportUtils tputils = new TeleportUtils(this);
        getCommand("rtp").setExecutor(new RandomTPCommand());
        super.onEnable();
    }
}
