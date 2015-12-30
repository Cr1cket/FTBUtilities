package latmod.ftbu.mod.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.*;
import latmod.ftbu.badges.*;
import latmod.ftbu.util.client.LatCoreMCClient;
import latmod.ftbu.world.*;
import net.minecraftforge.client.event.RenderPlayerEvent;

@SideOnly(Side.CLIENT)
public class FTBUBadgeRenderer
{
	public static final FTBUBadgeRenderer instance = new FTBUBadgeRenderer();
	
	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Specials.Post e)
	{
		if(LatCoreMCClient.isPlaying() && !Badge.badges.isEmpty() && FTBUClient.render_badges.get() && !e.entityPlayer.isInvisible())
		{
			LMPlayerClient pc = LMWorldClient.inst.getPlayer(e.entityPlayer);
			
			if(pc != null && pc.renderBadge)
			{
				if(pc.cachedBadge == null)
				{
					pc.cachedBadge = Badge.badges.get(pc.getUUID());
					if(pc.cachedBadge == null) pc.cachedBadge = new BadgeEmpty();
				}
				
				pc.cachedBadge.onPlayerRender(e.entityPlayer);
			}
		}
	}
}