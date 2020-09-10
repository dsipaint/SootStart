import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LeaveListener extends ListenerAdapter
{
	public void onGuildMemberRemove(GuildMemberRemoveEvent e)
	{
		if(!e.getGuild().getId().equals("565623426501443584")) //wilbur's server only
			return;
		
		if(Main.join_ids.containsKey(e.getUser().getId()))
		{
			e.getJDA().getTextChannelById(Main.WELCOME_CHANNEL_ID).retrieveMessageById(Main.join_ids.get(e.getUser().getId())).queue((msg) ->
			{
				msg.delete().queue(); //delete join message if they leave before they react or anything
			});
			
			Main.join_ids.remove(e.getUser().getId());
		}
	}
}
