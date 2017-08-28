package at.xander.battleaxes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends ServerProxy {
	private Map<Item, String> textures = new HashMap<>();

	@Override
	public void registerTexture(Item item, String texture) {
		textures.put(item, texture);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		textures.forEach((k, v) -> Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(k, 0,
				new ModelResourceLocation(v, "inventory")));
		textures = null;
	}
}
