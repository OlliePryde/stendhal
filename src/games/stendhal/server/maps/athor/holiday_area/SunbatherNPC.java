package games.stendhal.server.maps.athor.holiday_area;

import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.SpeakerNPCFactory;

public class SunbatherNPC extends SpeakerNPCFactory {

	@Override
	protected SpeakerNPC instantiate(String name) {
		SpeakerNPC npc = new SpeakerNPC(name) {
			@Override
			public void say(String text) {
				// He doesn't move around because he's "lying" on his towel.
				say(text, false);
			}
		};
		return npc;
	}
	
	@Override
	protected void createDialog(SpeakerNPC npc) {
		npc.addGreeting("Hey there!");
		npc.addQuest("I don't have a task for you, I'm perfectly happy!");
		npc.addJob("Don't remind me of my job, I'm on holiday!");
		npc.addHelp("In the desert there is dangerous quicksand.");
		npc.addGoodbye("Bye! I'll stock up on some more sunshine.");
	}
}
