import java.util.HashMap;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main
{
	static JDA jda;
	static HashMap<String, String> join_ids = new HashMap<String, String>();
	static final String PREFIX = "^";
		
	static String WELCOME_CHANNEL_ID = "732290737558913107";
	static String WELCOME_MSG = "Welcome {USER_PING} to the Wilbur Soot official discord server! Please be sure to read the rules and follow the instructions!";
	static String RULES_MSG_ID = "734862688974405792";
	static String REACTION_NAME = "U+1f649";
	static String NEW_ROLE = "732290854118621344";
	static String FILE_LOC = "sootstart.yml";
	
	public static void main(String[] args)
	{
		try
		{
			jda = JDABuilder.createDefault("")
					.enableIntents(GatewayIntent.GUILD_MEMBERS)
					.build();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		
		DataHandler.updateValues();
		
		jda.addEventListener(new JoinListener());
		jda.addEventListener(new LeaveListener());
		jda.addEventListener(new ReactListener());
		jda.addEventListener(new StopListener());
	}
}
