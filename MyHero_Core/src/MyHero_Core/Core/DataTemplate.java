package MyHero_Core.Core;

public abstract class DataTemplate {

	protected Data data;
	public DataTemplate(Data maindata)
	{
		this.data = maindata;
	}
	
	public abstract void Start();
	public abstract void Restart();
}
