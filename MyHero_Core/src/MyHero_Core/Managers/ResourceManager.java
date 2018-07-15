package MyHero_Core.Managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Preconditions;

import MyHero_Core.Core.MyHeroMain;
import cn.nukkit.Server;
import cn.nukkit.utils.Utils;

public class ResourceManager {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getConfig(String configsec)
	{
		File file = new File(ResourceManager.getPath() + "\\config.yml");
		if(!file.isFile()) return null;
		
		Yaml FileYML = new Yaml();
		FileYML.load(file.getPath());
		Map<String,Map<String, Object>> Config;
		try 
		{
			Config = FileYML.loadAs(Files.newInputStream(Paths.get(file.getPath())), Map.class);
			
			for(Entry<String, Map<String, Object>> conf : Config.entrySet())
			{
				
				if(conf.getKey().equals(configsec)) return conf.getValue();
			}
			return null;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getPath()
	{
		return MyHeroMain.getMain().getDataFolder().toPath().toString().replace(MyHeroMain.getMain().getName(), "MyHero");
	}
	public static boolean saveResource(String filename, String outputName, boolean replace,String foldername) {
		Preconditions.checkArgument(filename != null && outputName != null, "Filename can not be null!");
		Preconditions.checkArgument(filename.trim().length() != 0 && outputName.trim().length() != 0, "Filename can not be empty!");

		File out = new File(MyHeroMain.getMain().getDataFolder().toString()+"/"+ foldername+"/", outputName);
		if (!out.exists() || replace) {
			try (InputStream resource = MyHeroMain.getMain().getResource("Resource/"+filename)) {
				if (resource != null) {
					File outFolder = out.getParentFile();
					if (!outFolder.exists()) {
						outFolder.mkdirs();
					}
					Utils.writeFile(out, resource);

					return true;
				}
			} catch (IOException e) {
				Server.getInstance().getLogger().logException(e);
			}
		}
		return false;
	}
	public static boolean saveResource(String filename, String outputName, boolean replace) {
		Preconditions.checkArgument(filename != null && outputName != null, "Filename can not be null!");
		Preconditions.checkArgument(filename.trim().length() != 0 && outputName.trim().length() != 0, "Filename can not be empty!");

		File out = new File(MyHeroMain.getMain().getDataFolder().toString(), outputName);
		if (!out.exists() || replace) {
			try (InputStream resource = MyHeroMain.getMain().getResource("Resource/"+filename)) {
				if (resource != null) {
					File outFolder = out.getParentFile();
					if (!outFolder.exists()) {
						outFolder.mkdirs();
					}
					Utils.writeFile(out, resource);

					return true;
				}
			} catch (IOException e) {
				Server.getInstance().getLogger().logException(e);
			}
		}
		return false;
	}
	public static String getPathTo(String foldername)
	{
		return MyHeroMain.getMain().getDataFolder().toPath().toString() + "\\"+foldername;
	}
}
