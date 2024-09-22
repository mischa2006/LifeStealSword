package mischa.lifeStealSword;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.tools.Tool;

public class GiveSword implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
		if(commandSender instanceof Player sender){
			if(sender.isOp()) {
				if (args.length == 1) {
					Player targetPlayer = sender.getServer().getPlayer(args[0]);
					if (targetPlayer == null) {
						sender.sendMessage("Player not found!");
						return false;
					}
					targetPlayer.getInventory().addItem(Tools.healthSword());
				}else{
					return false;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "You cant use this Command!");
			}
		}else{
			System.out.println("You can not use this command like this");
		}
		return true;
	}
}
