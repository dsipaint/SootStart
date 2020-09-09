import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactListener extends ListenerAdapter
{
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent e)
	{				
		if(!e.getMessageId().equals(Main.RULES_MSG_ID)) //wilbur's server rules message
		 return;
		
		if(e.getReactionEmote().getName().equals(Main.REACTION_NAME)) //correct reaction
		{
			e.getChannel().retrieveMessageById(Main.RULES_MSG_ID).queue((msg) ->
			{
				msg.removeReaction(Main.REACTION_NAME, e.getUser()).queue(); //remove said reaction
			});
			
			e.getGuild().removeRoleFromMember(e.getMember(), e.getGuild().getRoleById(Main.NEW_ROLE)).queue(); //remove new role
			e.getGuild().getTextChannelById(Main.WELCOME_CHANNEL_ID)
				.deleteMessageById(Main.join_ids.remove(e.getMember().getId())).queue(); //delete welcome message and remove from cache
		}
	}
}
