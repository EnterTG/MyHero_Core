package MyHero_Core.DataManagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import MyHero_Core.Core.Data;
import MyHero_Core.Core.DataTemplate;
import MyHero_Mobs.DropManager.Drop;
import MyHero_Mobs.DropManager.DropManager;
import MyHero_Mobs.MobManager.EntityInterface;
import MyHero_Mobs.MobManager.MobManager;
import MyHero_Mobs.MobManager.MobOptionManager;
import MyHero_Mobs.MobManager.MobsType;
import MyHero_Mobs.MobManager.MyHeroMobCreator;
import MyHero_Mobs.MobManager.OptionInterface;
import MyHero_Mobs.SpawningManager.Spawner;
import MyHero_Mobs.SpawningManager.SpawningManager;
import cn.nukkit.entity.Entity;

public class DataMobs extends DataTemplate{

	
	public DataMobs(Data maindata) {
		super(maindata);

	}

	private HashMap<String,Drop> AllDrops;// = new HashMap<>();
	private List<Spawner> AllSpawners;// = new ArrayList<Spawner>();
	private HashMap<String,OptionInterface> AllMobOptions;// = new HashMap<String,OptionInterface>();
	private HashMap<String,MyHeroMobCreator> AllMobs;
	private HashMap<MobsType,EntityInterface> MobsList;
	private HashMap<Long,Entity> AllSpawnedMobs;
	private HashMap<Long,MyHeroMobCreator> AllSpawnedMobsCreators;
	@Override
	public void Start() {

		if(!data.MyHeroMobs)
		{
			data.MyHeroMobs = true;
			if(data.MyHeroItems)
			{
				AllDrops = new HashMap<>();
				DropManager.Load();
			}
			AllSpawnedMobsCreators= new HashMap<>();
			AllSpawnedMobs = new HashMap<>();
			AllSpawners = new ArrayList<Spawner>();
			AllMobOptions = new HashMap<>();
			AllMobs = new  HashMap<>();
			MobsList = new HashMap<>();
			
			
			MobOptionManager.Load();
			MobManager.Load();
			SpawningManager.Load();
			AllSpawners.forEach((s) ->{s.Spawn();});
		}
	}
	
	@Override
	public void Restart() {
		data.MyHeroMobs = false;
		ClearData();
		Start();
	}
	
	public void ClearData()
	{
		if(data.MyHeroItems) AllDrops.clear();
		AllSpawnedMobs.forEach( (l,e) -> {if(e.isAlive())e.kill();});
		AllSpawnedMobs.clear();
		AllSpawners.forEach((s) -> {s.Stop();});
		AllSpawners.clear();
		AllMobOptions.clear();
		AllMobs.clear();
		MobsList.clear();
		AllSpawnedMobsCreators.clear();
	}
	
	//Drops
	public void addDrop(String dropname, Drop drop)
	{
		if(data.MyHeroItems) AllDrops.put(dropname, drop);
	}
	
	public boolean DropExist(String dropName)
	{
		return data.MyHeroItems && AllDrops.containsKey(dropName);
	}
	
	public Drop getDrop(String dropName)
	{
		return AllDrops.get(dropName);
	}
	
	//Mobs
	public boolean OptionExist(String optionName)
	{
		return AllMobOptions.containsKey(optionName);
	}
	public void addMobOption(String optionname, OptionInterface option)
	{
		AllMobOptions.put(optionname, option);
	}
	public OptionInterface getOption(String optionName)
	{
		return AllMobOptions.get(optionName);
	}
	
	
	
	public void addMob(String MobName,MyHeroMobCreator mob)
	{
		AllMobs.put(MobName, mob);
	}
	public MyHeroMobCreator getMob(String mobName)
	{
		return AllMobs.get(mobName);
	}
	public boolean MobExist(String mobName)
	{
		return AllMobs.containsKey(mobName);
	}
	
	
	
	public List<String> getAllMobsNames()
	{
		return new ArrayList<String>(AllMobs.keySet());
	}
	
	
	
	public boolean isMyHeroMob(Long mobid)
	{
		return AllSpawnedMobs.containsKey(mobid);
	}
	public MyHeroMobCreator getMyHeroMobInstance(Long mobid)
	{
		return AllSpawnedMobsCreators.get(mobid);
	}

	
	
	public void addMobType(MobsType mobtype,EntityInterface entityinterface)
	{
		MobsList.put(mobtype, entityinterface);
	}
	public EntityInterface getMobType(MobsType mobtype)
	{
		return MobsList.get(mobtype);
	}
	//Spawners
	
	public void addNewSpawnedMyHeroMobCreator(Long mobid,MyHeroMobCreator instance)
	{
		AllSpawnedMobsCreators.put(mobid, instance);
	}
	
	public void addNewSpawnedMyHeroMob(Long mobid,Entity instance)
	{
		AllSpawnedMobs.put(mobid, instance);
	}
	
	public void removeSpawnedMyHeroMob(Long mobid)
	{
		Entity e = AllSpawnedMobs.get(mobid);
		AllSpawners.forEach( (s) -> {s.MobDie(e);});
		AllSpawnedMobs.remove(mobid);
		AllSpawnedMobsCreators.remove(mobid);
	}
	
	
	
	public void addSpawner(Spawner spawner)
	{
		AllSpawners.add(spawner);
	}
	
	public Stream<Spawner> getStreamSpawners()
	{
		return AllSpawners.stream();
	}
	
	public String getAllSpawnersName()
	{
		StringBuilder builder = new StringBuilder();
		AllSpawners.stream().forEach( (s) -> {builder.append(s.toString());} );
		return builder.toString();
	}
}
