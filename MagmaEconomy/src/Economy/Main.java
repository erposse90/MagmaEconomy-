package Economy;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pixelmonbridge.pEconomy;
import vaultbridge.VEconomy;
import vaultbridge.vHook;

public class Main extends JavaPlugin implements Listener {
  public static Main plugin;
  
  public static Data data;
  
  public static VEconomy veco;
  
  public static vHook hook;
  
  public static pEconomy peco;
  
  public void onEnable() {
    plugin = this;
    Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
    getCommand("balance").setExecutor(new Executor());
    getCommand("setbalance").setExecutor(new Executor());
    getCommand("pay").setExecutor(new Executor());
    getCommand("adminpay").setExecutor(new Executor());
    getCommand("admintake").setExecutor(new Executor());
    getCommand("convert").setExecutor(new Executor());
    getCommand("deposit").setExecutor(new Executor());
    getCommand("withdraw").setExecutor(new Executor());
    data = new Data();
    veco = new VEconomy(plugin);
    hook = new vHook();
    hook.onHook();
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    if (!getData().hasBalance(e.getPlayer())) {
      getData();
      Data.saveData(e.getPlayer(), getIstance().getConfig().getDouble("startBalance"));
    } 
  }
  
  public boolean getVault() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      Bukkit.getLogger().severe("Vault not found!The Plugin Will work as an economy and as a bridge with pixelcoin, but will not be compatible with other plugins that require vault!");
      return false;
    } 
    return true;
  }
  
  public static Main getIstance() {
    return plugin;
  }
  
  public static boolean useVault() {
    return getIstance().getVault();
  }
  
  public static Data getData() {
    return data;
  }
  
  public void onDisable() {
    System.out.println("MagmaEconomy is Disabled!");
    hook.offHook();
  }
}
