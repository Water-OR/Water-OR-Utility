package io.github.wtorutility.init;

import io.github.wtorutility.items.ItemTest;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModObjects {
  private static final List<Item> MOD_ITEMS = new ArrayList<>();
  
  public static List<Item> getModItems() {
    return MOD_ITEMS;
  }
  
  public static void addItem(Item item) { MOD_ITEMS.add(item); }
  
  public static final ItemTest ITEM_TEST = new ItemTest();
}
