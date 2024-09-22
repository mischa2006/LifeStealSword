package mischa.lifeStealSword;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.tools.Tool;

public class Events implements Listener {
	private final FileConfiguration config;

	public Events(FileConfiguration config) {
		this.config = config;
	}

	@EventHandler
	public void playerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (event.getEntity().getKiller() == null)return;
		Player killer = player.getKiller();

		if (killer.getInventory().getItemInMainHand() == null) return;
		if (!killer.getInventory().getItemInMainHand().hasItemMeta()) return;
		if (!killer.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) return;
		if (killer.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1) {

			if (config.get(String.valueOf(player.getUniqueId())) != null) {
                int Health = (int) config.get(String.valueOf(player.getUniqueId()));
				if (Health == 1) {
					player.sendMessage(ChatColor.RED + "You lost a life, you now were already at 1 lives");
					player.getKiller().sendMessage(ChatColor.RED + "You killed but this player has no more lives left to steal");
					return;
				}
                setPlayerHealth(player, Health - 1);
				event.getEntity().sendMessage(ChatColor.RED + "You lost a life, you now have " + (Health - 1) + " remaining");
			} else {
				setPlayerHealth(player, 9);
                player.sendMessage(ChatColor.RED + "You lost a life, you now have " + 9 + " remaining");
			}
			player = killer;
			if (config.get(String.valueOf(player.getUniqueId())) != null) {
				int Health = (int) config.get(String.valueOf(player.getUniqueId()));
				if (Health == 20) {
					player.sendMessage(ChatColor.RED + "You already have the max amount of lives");
					return;
				}
				setPlayerHealth(player, Health + 1);
				player.sendMessage(ChatColor.GREEN + "You Gained a life, you now have " + (Health + 1) );
			}else{
                setPlayerHealth(player, 11);
                player.sendMessage(ChatColor.GREEN + "You Gained a life, you now have " + 11);
            }
		}
	}
    public void setPlayerHealth(Player player, Integer health){
		player.setMaxHealth(health*2);
       config.set(String.valueOf(player.getUniqueId()), health);
	   LifeStealSword.getPlugin().saveConfig();
    }
}
