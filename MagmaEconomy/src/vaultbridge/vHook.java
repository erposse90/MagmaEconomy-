package vaultbridge;

import org.bukkit.plugin.ServicePriority;

import Economy.Main;
import net.milkbowl.vault.economy.Economy;

public class vHook {
	
	private Main plugin= Main.getIstance();
	private Economy veco;
	
	public void onHook(){
		veco = Main.veco;
		plugin.getServer().getServicesManager().register(Economy.class, veco, plugin, ServicePriority.Normal);
		}
	public void offHook(){
		plugin.getServer().getServicesManager().unregister(Economy.class, Main.veco);
		}

}
