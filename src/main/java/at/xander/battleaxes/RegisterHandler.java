package at.xander.battleaxes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisterHandler {

	public static final RegisterHandler instance = new RegisterHandler();

	@SubscribeEvent
	public void onItemRegistry(Register<Item> event) {
		List<Item> itemList = new ArrayList<>();
		BAxe_Mod.instance.onInitItems(itemList);
		for (Item i : itemList) {
			event.getRegistry().register(i);
		}
	}

}
