import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main
{
	static JDA jda;
	static final String PREFIX = "^";
	
	static String current_remind_message = null;
	
	//default values
	static String WELCOME_CHANNEL_ID = "764602233563119637";
	static String WELCOME_MSG = "Welcome {USER_PING} to the Wilbur Soot official discord server! Please be sure to read the rules and follow the instructions!";
	static String RULES_MSG_ID = "734862688974405792";
	static String COMMAND_NAME = "verify";
	static String NEW_ROLE_ID = "732290854118621344";
	static String FILE_LOC = "sootstart.yml";	
	static final String GUILD_ID = "565623426501443584";
	
	static int HOURS_TO_MSG = 24;
	static String REMIND_MESSAGE = "There are {NUM_NEW_MEMBERS} unverified {NEW_MEMBER_PING}s! "
			+ "Please read the messages above! Make sure you read the rules and have a good time chatting! **If you can't talk in this channel it means you're already verified**";
	static final ScheduledThreadPoolExecutor messagetask = new ScheduledThreadPoolExecutor(3);
	
	public static void main(String[] args)
	{
		try
		{
			jda = JDABuilder.createDefault("")
					.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES)
					.setMemberCachePolicy(MemberCachePolicy.ALL)
					.build();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			jda.awaitReady();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		DataHandler.updateValues();
		
		jda.getTextChannelById(WELCOME_CHANNEL_ID).sendMessage("Welcome to the official Wilbur Soot discord server! Please type **"
				+ Main.PREFIX + "verify** in this channel to confirm that you have read the server rules and to gain access to the rest of the server!").queue();
		
		jda.addEventListener(new JoinListener());
		jda.addEventListener(new CommandListener());
		jda.addEventListener(new StopListener());
		
		messagetask.scheduleAtFixedRate(() ->
		{
			Guild g = jda.getGuildById(GUILD_ID);
			Role new_role = g.getRoleById(NEW_ROLE_ID);
			
			if(current_remind_message != null)
			{
				//delete old message first
				jda.getTextChannelById(WELCOME_CHANNEL_ID).retrieveMessageById(current_remind_message).queue(remindmsg ->
				{
					remindmsg.delete().queue();
				});
			}
			
			g.findMembers((member) ->
			{
				return member.getRoles().contains(new_role);
			})
			.onSuccess((members) ->
			{
				current_remind_message = jda.getTextChannelById(WELCOME_CHANNEL_ID).sendMessage(REMIND_MESSAGE.replace("{NUM_NEW_MEMBERS}", Integer.toString(members.size()))
                        .replace("{NEW_MEMBER_PING}", new_role.getAsMention())).complete().getId();
			});
		},
			0, HOURS_TO_MSG, TimeUnit.HOURS);
	}
}
