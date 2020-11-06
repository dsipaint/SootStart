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
			Main.WELCOME_MSG = (String) values.get("welcome_message");
			Main.RULES_MSG_ID = (String) values.get("rules_message_id");
			Main.COMMAND_NAME = (String) values.get("command_name");
			Main.NEW_ROLE_ID = (String) values.get("role_id");
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}
}
