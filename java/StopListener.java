import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StopListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		if(!e.getGuild().getId().equals("565623426501443584")) //only wilbur's server
			return;
		
		if(isStaff(e.getMember()))
		{
			String msg = e.getMessage().getContentRaw();
			String[] args = msg.split(" ");
			
			if(args.length > 1 && args[0].equalsIgnoreCase(Main.PREFIX + "disable") && args[1].equalsIgnoreCase("sootstart"))
			{
				e.getChannel().sendMessage("*disabling al's sootstart code...*").queue();
				e.getJDA().shutdown();
				System.exit(0);
			}
			
			if(args[0].equalsIgnoreCase(Main.PREFIX + "shutdown"))
			{
				e.getJDA().shutdownNow();
				System.exit(0);
			}
		}
	}
	
	public static boolean isStaff(Member m)
	{
		if(m.isOwner())
			return true;
		
		if(m.getPermissions().contains(Permission.ADMINISTRATOR))
			return true;
		
		if(m.getRoles().contains(m.getGuild().getRoleById("565626094917648386"))) //wilbur mod role
			return true;
		
		return false;
	}
}
