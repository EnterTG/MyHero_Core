package MyHero_Core.DataManagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import MyHero_Core.Core.Data;
import MyHero_Core.Core.DataTemplate;
import MyHero_Items.ItemManager.AbstractItemStackOption;
import MyHero_Items.ItemManager.ItemStackManager;
import MyHero_Items.ItemManager.ItemStackOptionInterface;
import MyHero_Items.ItemManager.ItemStackOptionManager;

public class DataItems extends DataTemplate{
	

	
	public DataItems(Data maindata) {
		super(maindata);
	}
	
	private HashMap<String,AbstractItemStackOption> ItemList;// = new HashMap<>();
	private HashMap<String,ItemStackOptionInterface> ListOptions;// = new HashMap<>();
	@Override
	public void Start() 
	{
		if(!data.MyHeroItems)
		{
			data.MyHeroItems = true;
			ItemList = new HashMap<>();
			ListOptions = new HashMap<>();
			
			
			ItemStackOptionManager.Load();
			ItemStackManager.LoadItems();
			
			
		}
	}
	@Override
	public void Restart() {
		data.MyHeroItems = false;
		ClearData();
		Start();
		
	}
	
	
	public boolean OptionExist(String name)
	{
		return ListOptions.containsKey(name);
	}
	public ItemStackOptionInterface getOption(String name)
	{
		return ListOptions.get(name);
	}
	public AbstractItemStackOption getItem(String itemname)
	{
		return ItemList.get(itemname);
	}
	public void addItemStackOption(String optionName , ItemStackOptionInterface option)
	{
		ListOptions.put(optionName, option);
	}
	
	public List<String> getAllItemNames()
	{
		return new ArrayList<String>(ItemList.keySet());
	}
	public boolean MyHeroItemsInitialized()
	{
		return data.MyHeroItems;
	}
	
	public boolean itemExist(String name)
	{
		return data.MyHeroItems && ItemList.containsKey(name);
	}
	
	public void addItem(String name, AbstractItemStackOption item)
	{
		if(data.MyHeroItems) ItemList.put(name, item);
	}
	
	public void ClearData()
	{
		ItemList.clear();
		ListOptions.clear();
	}
	

	
}
