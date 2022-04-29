package pixelmonbridge;

import org.bukkit.entity.Player;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.economy.IPixelmonBankAccount;

public class pEconomy {
	
	private IPixelmonBankAccount account;
	public pEconomy(Player plp) {
	    this.account = (IPixelmonBankAccount)Pixelmon.moneyManager.getBankAccount(plp.getUniqueId()).orElseThrow(() -> new NullPointerException("No bank account for " + plp.getName()));
	  }
	
	  public int getPbalance(Player plp) {
		    return (this.account.getMoney());
		  }

		  
	  public boolean canAfford(Player plp, double pmoney) {
		    return (this.account.getMoney() >= pmoney);
		  }
		  
		  public boolean deposit(Player plp, double pmoney, boolean message) {
		    return (change(MathUtils.clamp((int)pmoney, 0, 999999)) != -1);
		  }
		  
		  public boolean withdraw(Player plp, double pmoney, boolean message) {
		    return (change(-MathUtils.clamp((int)pmoney, 0, 999999)) != -1);
		  }
		  
		  
		  private int change(int pmoney) {
		    pmoney = MathUtils.clamp(pmoney, -999999, 999999);
		    int before = this.account.getMoney();
		    int change = this.account.changeMoney(pmoney);
		    int diff = change - before;
		    if (diff == 0 && pmoney != 0)
		      return -1; 
		    return change;
		  }
		  
}



