package io.github.wtorutility.init;

import io.github.wtorutility.enchantments.EnchantmentShortBow;
import io.github.wtorutility.items.ItemTest;
import io.github.wtorutility.util.Log;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModObjects {
  private static final List<Item> MOD_ITEMS = new ArrayList<>();
  
  public static List<Item> getModItems() {
    return MOD_ITEMS;
  }
  
  public static void addItem(Item item) { MOD_ITEMS.add(item); }
  
  public static final ItemTest ITEM_TEST = new ItemTest();
  
  private static final List<Enchantment> MOD_ENCHANTMENT = new ArrayList<>();
  
  public static List<Enchantment> getModEnchantment() {
    return MOD_ENCHANTMENT;
  }
  
  public static void addEnchantment(Enchantment enchantment) { MOD_ENCHANTMENT.add(enchantment); }
  
  public static void EnchantmentSubscribe() {
    for (Enchantment enchantment : MOD_ENCHANTMENT) {
      MinecraftForge.EVENT_BUS.register(enchantment);
      Log.info("Register enchantment event of " + Objects.requireNonNull(enchantment.getRegistryName()));
    }
  }
  
  public static final EnchantmentShortBow ENCHANTMENT_SHORT_BOW = new EnchantmentShortBow();
}
