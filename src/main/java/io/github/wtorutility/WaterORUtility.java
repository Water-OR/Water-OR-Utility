package io.github.wtorutility;

import io.github.wtorutility.proxy.Common;
import io.github.wtorutility.util.Log;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class WaterORUtility {
  @Mod.Instance
  public static WaterORUtility instance = new WaterORUtility();
  
  @SidedProxy(serverSide = ModInfo.ProxyCommon, clientSide = ModInfo.ProxyClient)
  public static Common proxy = new Common();
  
  @Mod.EventHandler
  public static void preInit(FMLPreInitializationEvent event) {
    Log.warn(ModInfo.NAME + "is at the pre init");
  }
  
  @Mod.EventHandler
  public static void init(FMLInitializationEvent event) {
    Log.warn(ModInfo.NAME + "is at the init");
  }
  
  @Mod.EventHandler
  public static void postInit(FMLPostInitializationEvent event) {
    Log.warn(ModInfo.NAME + "is at the post init");
  }
}
