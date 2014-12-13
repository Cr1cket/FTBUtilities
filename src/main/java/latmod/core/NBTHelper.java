package latmod.core;

import java.io.*;

import net.minecraft.nbt.*;

public class NBTHelper // NBTBase
{
	public static final int END = 0;
	public static final int BYTE = 1;
	public static final int SHORT = 2;
	public static final int INT = 3;
	public static final int LONG = 4;
	public static final int FLOAT = 5;
	public static final int DOUBLE = 6;
	public static final int BYTE_ARRAY = 7;
	public static final int STRING = 8;
	public static final int LIST = 9;
	public static final int MAP = 10;
	public static final int INT_ARRAY = 11;
	
	@SuppressWarnings("unchecked")
	public static FastList<String> getMapKeys(NBTTagCompound tag)
	{
		FastList<String> list = new FastList<String>();
		list.addAll(tag.func_150296_c()); return list;
	}
	
	public static FastMap<String, NBTBase> toFastMap(NBTTagCompound tag)
	{
		FastMap<String, NBTBase> map = new FastMap<String, NBTBase>();
		FastList<String> keys = getMapKeys(tag);
		for(int i = 0; i < keys.size(); i++)
		{ String s = keys.get(i); map.put(s, tag.getTag(s)); }
		return map;
	}
	
	public static void writeMap(OutputStream os, NBTTagCompound tag) throws Exception
	{
		byte[] b = CompressedStreamTools.compress(tag);
		os.write(b); os.flush(); os.close();
	}
	
	public static NBTTagCompound readMap(InputStream is) throws Exception
	{
		byte[] b = new byte[is.available()]; is.read(b); is.close();
		return CompressedStreamTools.func_152457_a(b, new NBTSizeTracker(Long.MAX_VALUE));
	}
}