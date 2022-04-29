package Economy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.EconomyResponse;

public class Data {
	private static  File file =new File(Main.getIstance().getDataFolder()+"/data.yml");
	private static FileConfiguration data;
	
 public Data() {
	 if(!file.exists()) {
		 try {
			 file.createNewFile();
			 data = YamlConfiguration.loadConfiguration(file);
			 data.createSection("Data");
			 data.save(file);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return;
	 }
	 data = YamlConfiguration.loadConfiguration(file);
 }
public static void saveData(Player p, double money) {
	data.set("Data."+p.getName(), money);
	try {
		data.save(file);
	} catch (IOException e) {
		e.printStackTrace();
	}
 }

 public boolean hasBalance(Player p) {
	 return this.data.getString("Data."+p.getName())!=null;
 }
 
 public double getValue(Player p) {
	return  this.data.getDouble("Data."+p.getName());
	 
 }
 
 public String getName(Player p) {
	 return this.data.getString("Data."+p);
	 
 }
public static List<String> getDataList() {
	return (List<String>) data.getDefaultSection();
}


 
}
