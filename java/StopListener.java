import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StopListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		//consider null value due to cache (lazy but works?)
		if(e.getMember() != null && e.getMember().getId().equals("475859944101380106"))
		{
			String msg = e.getMessage().getContentRaw();
			String[] args = msg.split(" ");
			
			if(args.length > 1 && args[0].equalsIgnoreCase(Main.PREFIX + "disable") && args[1].equalsIgnoreCase("sootstart"))
			{
				e.getChannel().sendMessage("*disabling al's sootstart code...*").complete();
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
}
