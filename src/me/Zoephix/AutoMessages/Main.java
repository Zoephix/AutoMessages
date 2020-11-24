package me.Zoephix.AutoMessages;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	// config.
	FileConfiguration config = this.getConfig();
	
	// settings.
	int current = 0;
	long msgdelay = config.getLong("delay") * 20;
	
	@Override
	public void onEnable() {
		// startup.
		// reloads.
		// plugin reloads.
		
		saveDefaultConfig();
		
		// the adverts which got configured.
		List<String> adverts = config.getStringList("adverts");
		
		new BukkitRunnable()
		{
		    @Override
		    public void run()
		    {
		        for (Player player : Bukkit.getOnlinePlayers())
		        {
		        	player.sendMessage("" + ChatColor.translateAlternateColorCodes('&', adverts.get(current)));
		            
		            current++;
		            
		            if (current == adverts.size()) {
		            	current = 0;
		            }
		        }
		    }
		}.runTaskTimer(this, 0, msgdelay);
	}
	
	@Override
	public void onDisable() {
		// shutdown.
		// reloads.
		// plugin reloads.
		
		this.reloadConfig();
	}
}