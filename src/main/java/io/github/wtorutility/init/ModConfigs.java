package io.github.wtorutility.init;

import io.github.wtorutility.ModInfo;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ModInfo.MODID)
@Config.LangKey("config.general." + ModInfo.MODID)
public class ModConfigs {
  @Config.Comment("0: Disable | 1:Only ShortBow Enchantment | 2:Always\nDefault is 1.")
  @Config.Name("No arrow damage cooldown type")
  @Config.LangKey("config.general." + ModInfo.MODID + ".noArrowDamageCoolDownType")
  @Config.RangeInt(min = 0, max = 2)
  public static int noArrowDamageCoolDownType = 1;
  
  @Mod.EventBusSubscriber
  public static class ConfigSyncHandler {
    @SubscribeEvent
    public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
      if (event.getModID().equals(ModInfo.MODID)) {
        ConfigManager.sync(ModInfo.MODID, Config.Type.INSTANCE);
      }
    }
  }
}
