package MyHero_Core.Managers;

import java.io.File;
import java.sql.Connection;
import java.util.Map;

import ru.nukkit.dblib.DbLib;

public class ConnectionManager {

	
	private static String MySQLIP = "",MySQLLogin = "root",MySQLPass = "";
	private static int MYSQLPort = 0;
	
	private static boolean UseMySql = false;
	
	public static void InitConnection()
	{

		Map<String, Object> Config = ResourceManager.getConfig("Default");
		for(Map.Entry<String, Object> conf : Config.entrySet())
		{
			if(conf.getKey().equalsIgnoreCase("UseMySql"))
				UseMySql = (boolean) conf.getValue();
			else if(conf.getKey().equalsIgnoreCase("MySqlIP"))
				MySQLIP = (String) conf.getValue();
			else if(conf.getKey().equalsIgnoreCase("MySqlLogin"))
				MySQLLogin = (String) conf.getValue();
			else if(conf.getKey().equalsIgnoreCase("MySqlPass"))
				MySQLPass = (String) conf.getValue();
			else if(conf.getKey().equalsIgnoreCase("MySqlPort"))
				MYSQLPort = (int) conf.getValue();
		}
	}
	
	public static Connection getConnection(String DataBase)
	{
		try 
		{
			if(UseMySql)
				return DbLib.getMySqlConnection(MySQLIP, MYSQLPort, DataBase, MySQLLogin, MySQLPass);
			else
				return DbLib.getSQLiteConnection(new File(ResourceManager.getPath(), DataBase+".db"));
			
		} catch (Exception e) {
			//MyHeroLevelsMain.getMainClass().getLogger().info(e);
			e.printStackTrace();
			return null;
		}
	}
	
	
}
