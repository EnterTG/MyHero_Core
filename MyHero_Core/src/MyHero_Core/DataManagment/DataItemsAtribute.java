package MyHero_Core.DataManagment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

import MyHero_Core.Core.Data;
import MyHero_Core.Core.DataTemplate;
import MyHero_ItemAtribute.AtributeType.Atribute;
import MyHero_ItemAtribute.AtributeType.AtributeGetterInterface;
import MyHero_ItemsAtribute.Core.MyHeroPlayerItemAtributes;
import MyHero_ItemsAtribute.Managers.ItemsAtributeManager;
import cn.nukkit.Player;

public class DataItemsAtribute extends DataTemplate{

	
	
	//public final String[] AtributeName = {"Damage","Health","Crit","Armor","LifeSteal","Dodge","ArmorPenetration"};
	private LinkedHashMap<String ,String> ItemsAtribute = new LinkedHashMap<>();
	
	private HashMap<UUID,MyHeroPlayerItemAtributes> PlayersData = new HashMap<>();
	
	private LinkedHashMap<String,AtributeGetterInterface> AllAtributesCreators= new LinkedHashMap<>(); 
	
	
	
	public DataItemsAtribute(Data maindata) {
		super(maindata);

	}
	public HashMap<String,AtributeGetterInterface> getAtributeCreaotrs()
	{
		return AllAtributesCreators;
	}
	public Atribute getAtributeClass(String name,HashMap<String,Double> val)
	{
		return AllAtributesCreators.get(name).get(val);
	}

	@Override
	public void Start() {
		data.MyHeroItemAtributes = true;
		ItemsAtributeManager.LoadData();
		
	}

	@Override
	public void Restart() {
		// TODO Auto-generated method stub
		
	}

	public void setAtributeName(String aname,String avalue)
	{
		ItemsAtribute.put(aname, avalue);
	}
	
	public void addPlayerData(Player p,MyHeroPlayerItemAtributes pia)
	{
		PlayersData.put(p.getUniqueId(), pia);
	}
	public MyHeroPlayerItemAtributes getPlayerData(Player p)
	{
		return PlayersData.get(p.getUniqueId());
	}
	
}
