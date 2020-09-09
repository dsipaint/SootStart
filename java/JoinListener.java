import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter
{
	public void onGuildMemberJoin(GuildMemberJoinEvent e)
	{
		if(!e.getGuild().getId().equals("565623426501443584")) //only in wilbur's server
			return;
				
		e.getGuild().getTextChannelById(Main.WELCOME_CHANNEL_ID).sendMessage(Main.WELCOME_MSG
				.replace("{USER_NAME}", e.getUser().getName() //replace these tags with relevant data
				.replace("{USER_PING}", e.getMember().getAsMention()))).queue((msg) -> //send message to member join channel
		{
			Main.join_ids.put(e.getMember().getId(), msg.getId()); //store the message for deletion later
		});
	}
}
