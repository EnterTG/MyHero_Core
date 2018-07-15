package MyHero_Core.DataManagment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

import MyHero_Core.Core.Data;
import MyHero_Core.Core.DataTemplate;
import MyHero_ItemAtribute.AtributeType.Atribute;
import MyHero_ItemAtribute.AtributeType.AtributeGetterInterface;
import MyHero_ItemAtribute.AtributeType.AtributeName;
import MyHero_ItemAtribute.Atributes.Atribute_Armor;
import MyHero_ItemAtribute.Atributes.Atribute_ArmorPenetration;
import MyHero_ItemAtribute.Atributes.Atribute_Crit;
import MyHero_ItemAtribute.Atributes.Atribute_Damage;
import MyHero_ItemAtribute.Atributes.Atribute_Health;
import MyHero_ItemAtribute.Atributes.Atribute_LifeSteal;
import MyHero_ItemsAtribute.Core.MyHeroPlayerItemAtributes;
import MyHero_ItemsAtribute.Managers.ItemsAtributeManager;
import cn.nukkit.Player;

public class DataItemsAtribute extends DataTemplate{

	
	
	//public final String[] AtributeName = {"Damage","Health","Crit","Armor","LifeSteal","Dodge","ArmorPenetration"};
	private LinkedHashMap<String ,String> ItemsAtribute = new LinkedHashMap<>();
	
	private HashMap<UUID,MyHeroPlayerItemAtributes> PlayersData = new HashMap<>();
	
	private HashMap<String,AtributeGetterInterface> AllAtributesCreators= new HashMap<>(); 
	
	
	
	public DataItemsAtribute(Data maindata) {
		super(maindata);
		
		AllAtributesCreators.put(AtributeName.Damage.toString(), (s) -> new Atribute_Damage(s));
		AllAtributesCreators.put(AtributeName.Armor.toString(), (s) -> new Atribute_Armor(s));
		AllAtributesCreators.put(AtributeName.Crit.toString(), (s) -> new Atribute_Crit(s));
		//AllAtributesCreators.put(AtributeName.Dodge, (s) -> new Atribute_Dodge(s));
		AllAtributesCreators.put(AtributeName.ArmorPenetration, (s) -> new Atribute_ArmorPenetration(s));
		AllAtributesCreators.put(AtributeName.Health.toString(), (s) -> new Atribute_Health(s));
		AllAtributesCreators.put(AtributeName.LifeSteal.toString(), (s) -> new Atribute_LifeSteal(s));
		
		
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
