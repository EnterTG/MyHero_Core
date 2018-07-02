package MyHero_Core.Managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Preconditions;

import MyHero_Core.Core.MyHeroMain;
import cn.nukkit.Server;
import cn.nukkit.utils.Utils;

public class ResourceManager {

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
	
	public static String getPathTo(String foldername)
	{
		return MyHeroMain.getMain().getDataFolder().toPath().toString() + "\\"+foldername;
	}
}
