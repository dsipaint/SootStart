import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		//if member has new role and talks in welcome channel
		if(e.getMember().getRoles().contains(e.getGuild().getRoleById(Main.NEW_ROLE_ID))
				&& e.getChannel().getId().equals(Main.WELCOME_CHANNEL_ID))
		{
			String msg = e.getMessage().getContentRaw();
			String[] args = msg.split(" ");
			
			//^verify
			if(args[0].equalsIgnoreCase(Main.PREFIX + Main.COMMAND_NAME))
				e.getGuild().removeRoleFromMember(e.getMember(), e.getGuild().getRoleById(Main.NEW_ROLE_ID)).queue();
			
			e.getMessage().delete().queue();
		}
	}
}
