package MyHero_Core.Core;

import java.io.File;

import MyHero_Core.DataManagment.DataItems;
import MyHero_Core.DataManagment.DataItemsAtribute;
import MyHero_Core.DataManagment.DataMobs;
import MyHero_Core.Managers.ConnectionManager;
import MyHero_Core.Managers.ResourceManager;





public class Data {

	//Config
	public boolean MyHeroItems = false, MyHeroMobs = false, MyHeroLevels = false,MyHeroItemAtributes = false;
	
	public boolean DbActivated = false;
	private DataItems dataitems;
	private DataMobs datamobs;
	private DataItemsAtribute dataitemsatribute;
	
	
	public Data()
	{

	}
	public void InitData()
	{
		File file = new File(ResourceManager.getPath());
		if(!file.exists())
			file.mkdirs();
		ResourceManager.saveResource("config.yml","config.yml",false);
		
		if(MyHeroMain.getMain().getServer().getPluginManager().getPlugin("DbLib") != null)
		{
			ConnectionManager.InitConnection();
		}
		
	}
	
	public void RestartMyHero()
	{
		if(MyHeroItems) dataitems.Restart();
		if(MyHeroMobs) datamobs.Restart();
		if(MyHeroItemAtributes) dataitemsatribute.Restart();
	}
	//MyHero Items
	public void InitcializeDataItems()
	{
		dataitems = new DataItems(this);
	}
	
	public DataItems getDataItems()
	{
		return dataitems;
	}
	//MyHero Mobs
	public void InicializeDataMobs()
	{
		datamobs = new DataMobs(this);
	}
	public DataMobs getDataMobs()
	{
		return datamobs;
	}
	//MyHero ItemAtribute
	
	public void InicializeDataItemsAtribute()
	{
		dataitemsatribute = new DataItemsAtribute(this);
	}
	public DataItemsAtribute getDataItemsAtribute()
	{
		return dataitemsatribute;
	}
	
}
