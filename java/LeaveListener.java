import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LeaveListener extends ListenerAdapter
{
	public void onGuildMemberRemove(GuildMemberRemoveEvent e)
	{
		Main.join_ids.remove(e.getMember().getId()); //remove from cache if needed
	}
}
