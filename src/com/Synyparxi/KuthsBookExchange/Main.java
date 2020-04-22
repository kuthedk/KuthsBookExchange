package com.Synyparxi.KuthsBookExchange;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args ) {
		//kbx
		if (label.equalsIgnoreCase("kbx")) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to run this command!");
				return true;
			}
			
			Player player = (Player) sender;
			for(int i = 0; i<player.getInventory().getSize()-1; i++){
				ItemStack item = player.getInventory().getItem(i);
				if(item != null) {
					if(item.getType().equals(Material.ENCHANTED_BOOK)){
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta)item.getItemMeta();
						
						
						if(meta.getStoredEnchants().isEmpty()) {
							sender.sendMessage("Found Runic Book without enchantment! Fixing now!");
							String metaString = meta.toString();
							String[] msArray = metaString.split("=");
							String enchantStr = msArray[msArray.length-2].replaceAll("[\\[\\](){}]","");
							String enchantLvlStr = msArray[msArray.length-1].replaceAll("[\\[\\](){}]","");
							meta.addStoredEnchant(EnchantmentWrapper.getByName(enchantStr), Integer.parseInt(enchantLvlStr), true);
						}
							
						
						item.setItemMeta(meta);
						player.updateInventory();
						
					}
				}
			}
			return true;
		}
		
		return false;
	}
	
}