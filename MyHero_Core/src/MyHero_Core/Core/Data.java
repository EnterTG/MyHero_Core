package MyHero_Core.Core;

import MyHero_Core.DataManagment.DataItems;
import MyHero_Core.DataManagment.DataMobs;




public class Data {

	//Config
	public boolean MyHeroItems = false,MyHeroMobs = false;
	
	private DataItems dataitems;
	private DataMobs datamobs;
	
	public void RestartMyHero()
	{
		if(MyHeroItems) dataitems.Restart();
		if(MyHeroMobs) datamobs.Restart();
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
	
}
