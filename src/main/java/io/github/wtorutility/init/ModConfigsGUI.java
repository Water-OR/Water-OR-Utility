package io.github.wtorutility.init;

import io.github.wtorutility.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

import java.util.Collections;
import java.util.Set;

public class ModConfigsGUI implements IModGuiFactory {
  public static final String ModConfigsGUIClass = "io.github.wtorutility.init.ModConfigsGUI";
  
  @Override
  public void initialize(Minecraft minecraftInstance) {}
  
  @Override
  public boolean hasConfigGui() {
    return true;
  }
  
  @Override
  public GuiScreen createConfigGui(GuiScreen parentScreen) {
    return new GuiConfig(parentScreen, ConfigElement.from(ModConfigs.class).getChildElements(), ModInfo.MODID, false, false, "Config page of" + ModInfo.NAME, "???");
  }
  
  @Override
  public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
    return Collections.emptySet();
  }
}
