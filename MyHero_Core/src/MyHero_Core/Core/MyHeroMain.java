package MyHero_Core.Core;

import java.util.Arrays;

import MyHero_Core.Managers.LangManager;
import MyHero_Levels.API.MyHeroLevel;
import MyHero_Levels.API.MyHeroLevelsAPI;
import MyHero_Levels.Core.MyHeroLevelsMain;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class MyHeroMain extends PluginBase implements Listener{

	private static Data MyHeroData = new Data();;
	private static MyHeroMain MainClass;
	
	@Override
	public void onEnable()
	{
		MainClass = this;
		MyHeroData.InitData();
		this.getServer().getPluginManager().registerEvents(this,this);
		
	}
	@Override
	public void onDisable()
	{
	
	
	}
	public static Data getMyHeroData()
	{
		return MyHeroData;
	}
	
	public static MyHeroMain getMain()
	{
		return MainClass;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length > 0)
			switch (args[0].toLowerCase()) {
				case "items":
					if(args.length > 1 && MyHeroMain.getMyHeroData().MyHeroItems)
					{
						switch(args[1].toLowerCase())
						{
							case "list":
								sender.sendMessage(LangManager.Prefix +MyHeroMain.getMyHeroData().getDataItems().getAllItemNames().toString());
								break;
							default:
								String ItemName = args[1];
								if(MyHeroData.getDataItems().itemExist(ItemName))
								{
									if(args.length > 2)
									{
										Player p = this.getServer().getPlayerExact(args[2]);
										if(p != null)
											p.getInventory().addItem(MyHeroData.getDataItems().getItem(ItemName).spawnItem());
										else
											if(sender instanceof Player )
												sender.sendMessage(LangManager.Prefix + "Error while executing the command");
											else
												LangManager.Log("Error while executing the command");
										
									}
									else if(sender instanceof Player )
									{
										((Player)sender).getInventory().addItem(MyHeroData.getDataItems().getItem(ItemName).spawnItem());
										/*else
											sender.sendMessage(LangManager.Prefix + "Error while executing the command");*/
									}
									else
									{
										LangManager.Log("Error while executing the command");
									}
								}
								else
								{
									if(sender instanceof Player)
									{
										sender.sendMessage(LangManager.Prefix + "Error while executing the command");
									}
									else
									{
										LangManager.Log("Error while executing the command");
									}
								}
								break;
						}
					}
					else
					{
						LangManager.Log("Error while executing the command");
					}
					break;
				case "mobs":
					if(args.length > 1 && MyHeroMain.getMyHeroData().MyHeroMobs)
					{
						switch(args[1].toLowerCase())
						{
							case "list":
								if(sender instanceof Player)
									sender.sendMessage(LangManager.Prefix + MyHeroMain.getMyHeroData().getDataMobs().getAllMobsNames().toString());
								else
									LangManager.Log(MyHeroMain.getMyHeroData().getDataMobs().getAllMobsNames().toString());
								break;
							default:
								if(sender instanceof Player)
								{
									if(MyHeroMain.getMyHeroData().getDataMobs().MobExist(args[1]))
										MyHeroMain.getMyHeroData().getDataMobs().getMob(args[1]).getRoot().SpawnEntity(((Player) sender).getLocation());
								}
								else
									LangManager.Log("Error while executing the command");
								
								break;
						}
					}
					else
					{
						LangManager.Log("Error while executing the command");
					}
					break;
				case "spawners":
					if(args.length > 1 && MyHeroMain.getMyHeroData().MyHeroMobs)
					{
						switch(args[1].toLowerCase())
						{
							case "list":
								if(sender instanceof Player)
									sender.sendMessage(LangManager.Prefix + MyHeroMain.getMyHeroData().getDataMobs().getAllSpawnersName());
								else
									LangManager.Log(MyHeroMain.getMyHeroData().getDataMobs().getAllSpawnersName());
								break;	
						}
					}
				case "reload":
					MyHeroData.RestartMyHero();
					break;
				case "exp":
					if(args.length > 2 && MyHeroMain.getMyHeroData().MyHeroLevels)
					{
						Player p = null;
						
						if(args.length > 3)
							p = MyHeroMain.getMain().getServer().getPlayer(args[3]);
						else
							if(sender instanceof Player)
								p = (Player) sender;
						MyHeroLevel mhl = MyHeroLevelsMain.getAPI().getMyHeroLevel(p);
						if(mhl == null)
						{
							if(sender instanceof Player)
								sender.sendMessage("Error while executing the command");
							else
								LangManager.Log("Error while executing the command");
							break;
						}
						
						try
						{
							switch(args[1].toLowerCase())
							{
								case "give":
									mhl.addExp(Long.parseLong(args[2]));
									mhl.updatePlayerView();
									break;
								case "set":
									
									mhl.setPlayerExp(Long.parseLong(args[2]));
									mhl.updatePlayerView();
									break;
								case "substract":
									mhl.subtractExp(Long.parseLong(args[2]));
									mhl.updatePlayerView();
									break;
								case "list":
									MyHeroLevelsAPI api = MyHeroLevelsMain.getAPI();
									p.sendMessage(Arrays.toString(api.getLevelsTable()));
									break;
								default:
									if(sender instanceof Player)
										sender.sendMessage("Error while executing the command");
									else
										LangManager.Log("Error while executing the command");
							}
						}
						catch(NumberFormatException e)
						{
							if(sender instanceof Player)
								sender.sendMessage("Error while executing the command");
							else
								LangManager.Log("Error while executing the command");
						}
					}
					else
					{
						if(sender instanceof Player)
							sender.sendMessage("Error while executing the command");
						else
							LangManager.Log("Error while executing the command");
					}
					break;
				case "level":
					if(args.length > 2 && MyHeroMain.getMyHeroData().MyHeroLevels)
					{
						Player p = null;
						
						if(args.length > 3)
							p = MyHeroMain.getMain().getServer().getPlayer(args[3]);
						else
							if(sender instanceof Player)
								p = (Player) sender;
						MyHeroLevel mhl = MyHeroLevelsMain.getAPI().getMyHeroLevel(p);
						if(mhl == null)
						{
							if(sender instanceof Player)
								sender.sendMessage("Error while executing the command");
							else
								LangManager.Log("Error while executing the command");
							break;
						}
						
						try
						{
							switch(args[1].toLowerCase())
							{
								case "give":
									mhl.addLevel(Integer.parseInt(args[2]));
									
									break;
								case "set":
									
									mhl.setPlayerLevel(Integer.parseInt(args[2]));
									
									break;
								case "substract":
									mhl.subtractLevel(Integer.parseInt(args[2]));
									break;
								default:
									if(sender instanceof Player)
										sender.sendMessage("Error while executing the command");
									else
										LangManager.Log("Error while executing the command");
							}
						}
						catch(NumberFormatException e)
						{
							if(sender instanceof Player)
								sender.sendMessage("Error while executing the command");
							else
								LangManager.Log("Error while executing the command");
						}
					}
					else
					{
						if(sender instanceof Player)
							sender.sendMessage("Error while executing the command");
						else
							LangManager.Log("Error while executing the command");
					}
					break;
						
					
					
					
			}
		return true;
	}
}
