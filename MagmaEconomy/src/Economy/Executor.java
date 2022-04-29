 package Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import pixelmonbridge.pEconomy;


public class Executor implements CommandExecutor{

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {

		   FileConfiguration config = Main.getIstance().getConfig();
		   Player pl = (Player)sender;
		   Eco eco =new Eco (pl,0);
		   pEconomy peco = new pEconomy(pl);
		  
		if(!pl.hasPermission("magmaeconomy."+cmd.getName())) {
			pl.sendMessage("§eYou  don't have permission for this!");
			return false;
		}
		
	if(is(cmd, "balance")) {
			if(args.length > 2) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <target>");
				return false;
			}
			if(args.length == 1) {
				Player ps = Bukkit.getPlayer(args[0]);
				if(!Main.getIstance().getData().hasBalance(ps)) {
					pl.sendMessage("§ePlayer not found!");
					return false;
				}
				eco= new Eco (ps,0);
				pl.sendMessage("§ePlayer balance is §6"+ eco.getBalance());
				return true;
			}
			eco= new Eco (pl,0);
			pl.sendMessage("§eYour balance is §6"+ eco.getBalance());
			return true;
		}
	if(is(cmd, "setbalance")) {
			if(args.length == 0) {
				pl.sendMessage("§cArguments are missing!");
				return true;
			}
			if(args.length > 2) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <money> <target>");
				return false;
			}
			try{
				  Double.parseDouble(args[0]);
				}catch(NumberFormatException e){
				    pl.sendMessage("§cWrong arguments!");
				    pl.sendMessage("§eThe correct syntax is: </cmd> <money> <target>");
	                return false;
				}
			Double arg = Double.parseDouble(args[0]);
			if(args.length == 1) {
				eco= new Eco (pl,arg);
				eco.setBalance();
				pl.sendMessage("§eYour balance now is §6"+eco.getBalance());
			 }else {
			Player ps = Bukkit.getPlayer(args[1]);
			if(!Main.getIstance().getData().hasBalance(ps)) {
				pl.sendMessage("§ePlayer not found!");
				return false;
			}
			eco= new Eco (ps,arg);
			eco.setBalance();
			ps.sendMessage("§eYour balance now is §6"+eco.getBalance());
			pl.sendMessage(ps.getName()+"§e Balance now is §6"+eco.getBalance());
		           }
		    pl.sendMessage("§cWrong arguments!");
		    pl.sendMessage("§ethe correct syntax is: </cmd> <money> <target>");
			return false;
		   }
		
	if(is(cmd, "adminpay")) {
			if(args.length == 0) {
				pl.sendMessage("§cArguments are missing!");
				return true;
			}			
			try{
				  Double.parseDouble(args[0]);
				}catch(NumberFormatException e){
				    pl.sendMessage("§cWrong arguments!");
				    pl.sendMessage("§eThe correct syntax is: </cmd> <money> <target>");
	                return false;
				}
			Double arg = Double.parseDouble(args[0]);
			if(args.length > 2) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <money> <target>");
				return false;
			}
			if(args.length == 1) {
				eco= new Eco (pl,arg);
				eco.addBalance();
				pl.sendMessage("§eYou admin-paid §6 "+arg+" §cYourself");
				return true;
			}			
			Player ps = Bukkit.getPlayer(args[1]);
			if(!Main.getIstance().getData().hasBalance(ps)) {
				pl.sendMessage("§ePlayer not found!");
				return false;
			}
			eco= new Eco (ps,arg);
			eco.addBalance();
			ps.sendMessage("§eYou received §6 "+arg+" §eby the §cAdmin");
			pl.sendMessage("§eYou admin-paid §6 "+arg+" §eto §6"+ ps.getName());
			return true;
		}
	if(is(cmd, "admintake")) {
			if(args.length == 0) {
				pl.sendMessage("§cArguments are missing!");
				return true;
			}
			try{
				  Double.parseDouble(args[0]);
				}catch(NumberFormatException e){
				    pl.sendMessage("§cWrong arguments!");
				    pl.sendMessage("§eThe correct syntax is: </cmd> <money> <target>");
	                return false;
				}
			if(args.length > 2) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <money> <target>");
				return false;
			}
			Double arg = Double.parseDouble(args[0]);
			if(args.length == 1) {
				eco= new Eco (pl,arg);
				eco.takeBalance();
				pl.sendMessage("§eYou take §6 "+arg+" §eto §6Yourself" );
				return true;
			}
			Player ps = Bukkit.getPlayer(args[1]);
			if(!Main.getIstance().getData().hasBalance(ps)) {
				pl.sendMessage("§ePlayer not found!");
				return false;
			}
			eco=new Eco (ps,arg);
			eco.takeBalance();
			ps.sendMessage("§eYou have been removed §6"+arg+" §eby the §cAdmin");
			pl.sendMessage("§eYou take §6 "+arg+" §eto §6"+ps.getName() );
			return true;
		}
		
	if(is(cmd, "pay")) {
			if(args.length == 0) {
				pl.sendMessage("§cArguments are missing!");
				return true;
			}
			try{
				  Double.parseDouble(args[0]);
				}catch(NumberFormatException e){
				    pl.sendMessage("§cWrong arguments!");
				    pl.sendMessage("§eThe correct syntax is: </cmd> <money> <target>");
	                return false;
				}
			if(args.length > 2) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <money> <target>");
				return false;
			}
			Double arg = Double.parseDouble(args[0]);
			Player ps = Bukkit.getPlayer(args[1]);
			if(!Main.getIstance().getData().hasBalance(ps)) {
				pl.sendMessage("§ePlayer not found!");
				return false;
			}
			if(ps == pl) {
				pl.sendMessage("§cDont Pay Yourself!");
				return false;
			}
			eco=new Eco (pl,arg);
			if((eco.getBalance()-arg)>=0) {
				Eco ecot=new Eco (ps,arg);
				ecot.addBalance();
				eco.takeBalance();
				ps.sendMessage("§eYou received §6 "+arg+" §efrom §6"+ pl.getName());
				pl.sendMessage("§eYou paid §6 "+arg+" §eto §6"+ ps.getName());
				return true;
			}else {
				pl.sendMessage("§eYou don't have enough money!");
				return false;
			}

		}
	if(is(cmd, "deposit")) {
		if(args.length == 0) {
			pl.sendMessage("§cArguments are missing!");
			return true;
		}
		try{
			  Double.parseDouble(args[0]);
			}catch(NumberFormatException e){
			    pl.sendMessage("§cWrong arguments!");
			    pl.sendMessage("§eThe correct syntax is: </cmd> <money>");
                return false;
			}
		if(args.length > 1) {
         pl.sendMessage("§cToo many arguments!");
         pl.sendMessage("§ethe correct syntax is: </cmd> <money>");
			return false;
		}
		Double arg = Double.parseDouble(args[0]);
		eco=new Eco (pl,arg);
		if(peco.canAfford(pl, arg)!=false) {
			peco.withdraw(pl, arg, false);
			eco.addBalance();
			pl.sendMessage("§eYou have deposit §6 "+arg+" §ein Vault");
			return true;
		}else {
			pl.sendMessage("§eYou don't have enough PokeMoney!");
			return false;
		}

	}
	
	if(is(cmd, "withdraw")) {
		if(args.length == 0) {
			pl.sendMessage("§cArguments are missing!");
			return true;
		}
		try{
			  Double.parseDouble(args[0]);
			}catch(NumberFormatException e){
			    pl.sendMessage("§cWrong arguments!");
			    pl.sendMessage("§eThe correct syntax is: </cmd> <money>");
                return false;
			}
		if(args.length > 1) {
         pl.sendMessage("§cToo many arguments!");
         pl.sendMessage("§ethe correct syntax is: </cmd> <money>");
			return false;
		}
		Double arg = Double.parseDouble(args[0]);
		eco=new Eco (pl,arg);
		if((eco.getBalance()-arg) >=0) {
			peco.deposit(pl, arg, false);
			eco.takeBalance();
			pl.sendMessage("§eYou have withdraw §6 "+arg+" §ein PokeMoney");
			return true;
		}else {
			pl.sendMessage("§eYou don't have enough Money!");
			return false;
		}

	}
		
	if(is(cmd, "convert")) {
			if(args.length > 1) {
             pl.sendMessage("§cToo many arguments!");
             pl.sendMessage("§ethe correct syntax is: </cmd> <money/target> <target>");
				return false;
			}
			if(args.length == 1) {
				if(pl.getInventory().firstEmpty()== -1) {
					pl.sendMessage("§eYou can't do this if you have full inventory!");
					return false;
				}
				Double arg = Double.parseDouble(args[0]);
				String obj = config.getString("reconvert.obj");
				Double n= config.getDouble("reconvert.coin");
				eco=new Eco (pl,arg);
				int stack=(int) (arg/n);
				if(( eco.getBalance()- arg) < 0) {
					pl.sendMessage("§eYou don't have enough money!");
					return false;
				}
				if(( eco.getBalance()- n) < 0) {
					pl.sendMessage("§eYou don't have enough money!");
					return false;
				}
				if((arg - n) < 0) {
					pl.sendMessage("§eConversion costs at least §c"+ n +"§e Money!");
					return false;
				}
				if(stack > 64) {
					eco=new Eco(pl,(64*n));
					eco.takeBalance();
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = ("give "+pl.getName()+" "+obj.toString()+" "+ "64" );
					Bukkit.dispatchCommand(console, command);
					pl.sendMessage("§eYou received §664 §eof §6"+ obj+"  §efor §6"+ (64*n) +" §eMoney!");
					pl.sendMessage("§eYou can't convert more than one stack at a time");
					return true;
				}
				if((stack*n) < arg) {
					stack=(stack-1);
					Double rst= (arg-(stack*n));
					eco = new Eco (pl,arg);
					if(( eco.getBalance()- rst) < 0) {
						pl.sendMessage("§eYou don't have enough money!");
						return false;
					}
					eco = new Eco (pl,(arg-rst));
					eco.takeBalance();
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = ("give "+pl.getName()+" "+obj.toString()+" "+stack );
					Bukkit.dispatchCommand(console, command);
					pl.sendMessage("§eYou received §6"+ stack+ " §eof §6"+ obj+"  §efor §6"+(arg-rst)+" §eMoney!");
					return true;
				} else {
				Double rst= (arg-(stack*n));
				eco = new Eco (pl,arg);
				if(( eco.getBalance() - rst) < 0) {
					pl.sendMessage("§eYou don't have enough money!");
					return false;
				}
				eco = new Eco (pl,(arg-rst));
				eco.takeBalance();
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command = ("give "+pl.getName()+" "+obj.toString()+" "+stack );
				Bukkit.dispatchCommand(console, command);
				pl.sendMessage("§eYou received §6"+ stack+ " §eof §6"+ obj+"  §efor §6"+(arg-rst)+" §eMoney!");
				return true;
			   }
			}
			if(config.contains("convert."+(pl.getInventory().getItemInMainHand().getType().toString()))) {
				Double arg = config.getDouble("convert."+(pl.getInventory().getItemInMainHand().getType().toString()))*pl.getInventory().getItemInMainHand().clone().getAmount();
				eco = new Eco (pl,arg);
				eco.addBalance();
				pl.getInventory().getItemInMainHand().setAmount(0);
				pl.sendMessage("§eConversion in Coin was §6Successful! §eYour balance now is: §6"+eco.getBalance());
				return true;
			}else {
				pl.sendMessage("§eYou §cCan't §eConvert this Item!");
				return false;
			}

		}
	return true;
 
	}
	public boolean is(Command cmd, String compare) {
		return cmd.getName().equalsIgnoreCase(compare);	
    }


}
