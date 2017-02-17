package at.xander.battleaxes;

import net.minecraft.item.Item;

public class ClientProxy extends ServerProxy {
	@Override
	public void registerTexture(Item item, String texture) {
		item.setTextureName(texture);
	}
}
