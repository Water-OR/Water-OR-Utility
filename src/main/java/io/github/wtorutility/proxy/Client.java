package io.github.wtorutility.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

public class Client extends Common {
  @Override
  public void RegisterModel(Item item, int meta, String itemIn) {
    ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), itemIn));
  }
}
