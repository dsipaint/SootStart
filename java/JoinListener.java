import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinListener extends ListenerAdapter
{
	public void onGuildMemberJoin(GuildMemberJoinEvent e)
	{
		if(!e.getGuild().getId().equals("565623426501443584")) //only in wilbur's server
			return;
		
		e.getGuild().addRoleToMember(e.getMember(), e.getGuild().getRoleById(Main.NEW_ROLE)).queue();
		e.getUser().openPrivateChannel().queue((channel) ->
		{
			channel.sendMessage(Main.WELCOME_MSG
					.replace("{USER_NAME}", e.getUser().getName()) //replace these tags with relevant data
					.replace("{USER_PING}", e.getMember().getAsMention())).queue();
		});
	}
}
