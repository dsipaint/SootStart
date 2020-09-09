import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class DataHandler
{
	public static void updateValues()
	{
		Yaml yaml = new Yaml();
		Map<String, Object> values = null;
		
		try
		{
			values = yaml.load(new BufferedReader(new FileReader(new File(Main.FILE_LOC))));
			Main.WELCOME_CHANNEL_ID = (String) values.get("welcome_channel_id");
			Main.WELCOME_MSG = (String) values.get("welcome_msg");
			Main.RULES_MSG_ID = (String) values.get("rules_msg");
			Main.REACTION_NAME = (String) values.get("reaction_name");
			Main.NEW_ROLE = (String) values.get("new_role");
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}
}
