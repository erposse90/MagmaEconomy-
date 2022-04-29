package vaultbridge;

import Economy.Data;
import Economy.Main;

import java.text.DecimalFormat;
import java.util.List;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class VEconomy implements Economy {
  private Main plugin;
  private final String name = "MagmaEconomy";
  
  public VEconomy(Main plugin) {
    this.plugin = plugin;
  }
  
  public EconomyResponse bankBalance(String arg0) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
  }
  
  public EconomyResponse bankDeposit(String arg0, double arg1) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
  }
  
  public EconomyResponse bankHas(String arg0, double arg1) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
  }
  
  public EconomyResponse bankWithdraw(String arg0, double arg1) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
  }
  
  public EconomyResponse createBank(String arg0, String arg1) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
  }
  
  public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");

  }
  
  public boolean createPlayerAccount(String arg0) {
	        Main.getData();
	        Data.saveData(Bukkit.getPlayer(arg0), Main.getIstance().getConfig().getDouble("startBalance"));
	        return false;
  }
  
  public boolean createPlayerAccount(OfflinePlayer arg0) {
	    return this.createPlayerAccount(arg0);
  }
  
  public boolean createPlayerAccount(String arg0, String arg1) {
    return this.createPlayerAccount(arg0);
  }
  
  public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
	    return this.createPlayerAccount(arg0);
  }
  
  public String currencyNamePlural() {
    return "Money";
  }
  
  public String currencyNameSingular() {
    return "Money";
  }
  
  public EconomyResponse deleteBank(String arg0) {
	    return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "bank not yet supported!");
 }
  
  public EconomyResponse depositPlayer(String p, double money) {
    Main.getData();
    Data.saveData(Bukkit.getPlayer(p), money + getBalance(p));
    return new EconomyResponse(money, getBalance(p), EconomyResponse.ResponseType.SUCCESS, "bank not yet supported!");
  }
  
  public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
    return depositPlayer(arg0.getName(), arg1);
  }
  
  public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
    return depositPlayer(arg0, arg2);
  }
  
  public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
    return depositPlayer(arg0.getName(), arg2);
  }
  
  public String format(double arg0) {
	  DecimalFormat decimal = new DecimalFormat("#,###.##");
	    return decimal.format(arg0);
  }
  
  public int fractionalDigits() {
    return 2;
  }
  
  public double getBalance(String p) {
    if (Main.getData().hasBalance(Bukkit.getPlayer(p)))
      return Main.getData().getValue(Bukkit.getPlayer(p)); 
    return 0.0D;
  }
  
  public double getBalance(OfflinePlayer arg0) {
    return getBalance(arg0.getName());
  }
  
  public double getBalance(String arg0, String arg1) {
    return getBalance(arg0);
  }
  
  public double getBalance(OfflinePlayer arg0, String arg1) {
    return getBalance(arg0.getName());
  }
  
  public List<String> getBanks() {
    return Data.getDataList();
  }
  
  public String getName() {
    return name;
  }
  
  public boolean has(String arg0, double arg1) {
	  return false;
  }
  
  public boolean has(OfflinePlayer arg0, double arg1) {
	  return false;
  }
  
  public boolean has(String arg0, String arg1, double arg2) {
	  return false;
 }
  
  public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
	  return false;
	  }
  
  public boolean hasAccount(String arg0) {
    if (getBalance(arg0) - 1.0D >= 0.0D) {
      return true;
    } else {
    	return false;
    } 
  }
  
  public boolean hasAccount(OfflinePlayer arg0) {
    return hasAccount(arg0);
  }
  
  public boolean hasAccount(String arg0, String arg1) {
    return hasAccount(arg0);
  }
  
  public boolean hasAccount(OfflinePlayer arg0, String arg1) {
    return hasAccount(arg0);
  }
  
  public boolean hasBankSupport() {
    return false;
  }
  
  public EconomyResponse isBankMember(String arg0, String arg1) {
	  return null;
  }
  
  public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
	  return null;
  }
  
  public EconomyResponse isBankOwner(String arg0, String arg1) {
	  return null;
  }
  
  public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
	  return null;
  }
  
  public boolean isEnabled() {
    return Main.useVault();
  }
  
  public EconomyResponse withdrawPlayer(String arg0, double arg1) {
    if (getBalance(arg0) - arg1 >= 0.0D) {
      Main.getData();
      double money= (getBalance(arg0) - arg1);
      Data.saveData(Bukkit.getPlayer(arg0), money);
    } else {
      Main.getData();
      Data.saveData(Bukkit.getPlayer(arg0), 0.0D);
    } 
    return new EconomyResponse(arg1, getBalance(arg0), EconomyResponse.ResponseType.SUCCESS, "bank not yet supported!");
  }
  
  public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
    return withdrawPlayer(arg0.getName(), arg1);
  }
  
  public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
    return withdrawPlayer(arg0, arg2);
  }
  
  public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
    return withdrawPlayer(arg0.getName(), arg2);
  }
}
