package at.xander.battleaxes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends Server_Proxy {
	@Override
	public void registerTexture(Item item, String texture) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(texture, "inventory"));
	}
}
