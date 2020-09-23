import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactListener extends ListenerAdapter
{
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e)
	{				
		if(!e.getMessageId().equals(Main.RULES_MSG_ID)) //wilbur's server rules message
		 return;
		
		System.out.println("Reaction spotted " + e.getMember().getEffectiveName() + "\n" + e.getReactionEmote().getAsCodepoints()
				+ "\n" + Main.REACTION_NAME); //DEBUG
		
		if(e.getReactionEmote().getAsCodepoints().equals(Main.REACTION_NAME)) //correct reaction
		{
			System.out.println("Right reaction used"); //DEBUG
			e.getChannel().retrieveMessageById(Main.RULES_MSG_ID).queue((msg) ->
			{
				msg.removeReaction(Main.REACTION_NAME, e.getUser()).queue(); //remove said reaction
				System.out.println("Removed reaction " + e.getMember().getEffectiveName()); //DEBUG
			});
			
			e.getGuild().removeRoleFromMember(e.getMember(), e.getGuild().getRoleById(Main.NEW_ROLE)).queue(); //remove new role
			System.out.println("Removed role " + e.getMember().getEffectiveName()); //DEBUG
		}
	}
}
