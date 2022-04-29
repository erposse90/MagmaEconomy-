package Economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import vaultbridge.VEconomy;

public class Eco extends VEconomy {
  private Player p;
  
  private double money;
  
  public Eco(Player p, double money) {
    super(Main.getIstance());
    this.p = p;
    this.money = money;
  }
  
  public double getBalance() {
    if (Main.useVault())
      return getBalance(this.p.getName()); 
    return Main.getData().getValue(this.p);
  }
  
  public void setBalance() {
    Main.getData();
    Data.saveData(this.p, this.money);
  }
  
  public void addBalance() {
    if (Main.useVault()) {
      depositPlayer(this.p.getName(), this.money);
    } else {
      Main.getData();
      Data.saveData(this.p, this.money + getBalance((OfflinePlayer)this.p));
    } 
  }
  
  public void takeBalance() {
    if (Main.useVault()) {
      withdrawPlayer(this.p.getName(), this.money);
    } else if (getBalance((OfflinePlayer)this.p) - this.money >= 0.0D) {
      Main.getData();
      Data.saveData(this.p, getBalance((OfflinePlayer)this.p) - this.money);
    } else {
      Main.getData();
      Data.saveData(this.p, 0.0D);
    } 
  }
  
  public boolean payable() {
    return (getBalance() - this.money >= 0.0D);
  }
}
