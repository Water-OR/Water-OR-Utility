package io.github.wtorutility.items;

import io.github.wtorutility.WaterORUtility;
import io.github.wtorutility.init.ModObjects;
import io.github.wtorutility.interfaces.IHasModel;
import net.minecraft.item.Item;

public class ItemTest extends Item implements IHasModel {
  public ItemTest() {
    this.setRegistryName("test");
    this.setUnlocalizedName("test");
    this.setMaxDamage(0);
    this.setMaxStackSize(1);
    this.setCreativeTab(WaterORUtility.getCreativeTabs());
    ModObjects.addItem(this);
  }
  
  @Override
  public void registerItemRender() {
    WaterORUtility.proxy.RegisterModel(this, 0, "inventory");
  }
}
