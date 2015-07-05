package latmod.ftbu.core.net;

import io.netty.buffer.ByteBuf;
import latmod.ftbu.core.world.*;
import latmod.ftbu.mod.claims.ChunkType;
import cpw.mods.fml.common.network.simpleimpl.*;

public class MessageAreaRequest extends MessageLM<MessageAreaRequest>
{
	public int chunkX, chunkZ, dim, playerID;
	public byte size;
	
	public MessageAreaRequest() { }
	
	public MessageAreaRequest(int x, int z, int d, byte s, int p)
	{
		chunkX = x;
		chunkZ = z;
		dim = d;
		size = s;
		playerID = p;
	}
	
	public void fromBytes(ByteBuf bb)
	{
		chunkX = bb.readInt();
		chunkZ = bb.readInt();
		dim = bb.readInt();
		size = bb.readByte();
		playerID = bb.readInt();
	}
	
	public void toBytes(ByteBuf bb)
	{
		bb.writeInt(chunkX);
		bb.writeInt(chunkZ);
		bb.writeInt(dim);
		bb.writeByte(size);
		bb.writeInt(playerID);
	}
	
	public IMessage onMessage(MessageAreaRequest m, MessageContext ctx)
	{
		LMPlayer p = LMWorld.server.getPlayer(m.playerID);
		
		byte[] types = new byte[m.size * m.size];
		
		for(int z = 0; z < m.size; z++) for(int x = 0; x < m.size; x++)
			types[x + z * m.size] = (byte)ChunkType.get(m.dim, m.chunkX + x, m.chunkZ + z, p).ordinal();
		
		return new MessageAreaUpdate(m.chunkX, m.chunkZ, m.dim, m.size, types);
	}
}