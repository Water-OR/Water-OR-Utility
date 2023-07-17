package io.github.wtorutility.init;

import io.github.wtorutility.interfaces.IHasModel;
import io.github.wtorutility.util.Log;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ModObjectsRegistry {
  @SubscribeEvent
  public static void onItemRegistry(RegistryEvent.Register<Item> event) {
    for (Item item : ModObjects.getModItems()) {
      event.getRegistry().register(item);
    }
  }
  
  @SubscribeEvent
  public static void onModelRegistry(ModelRegistryEvent event) {
    for (Item item : ModObjects.getModItems()) {
      if (item instanceof IHasModel) {
        ((IHasModel) item).registerItemRender();
      }
    }
  }
  
  @SubscribeEvent
  public static void onEnchantmentRegistry(RegistryEvent.Register<Enchantment> event) {
    for (Enchantment enchantment : ModObjects.getModEnchantment()) {
      event.getRegistry().register(enchantment);
    }
  }
}
