package io.github.wtorutility.enchantments;

import io.github.wtorutility.init.ModObjects;
import io.github.wtorutility.util.Log;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentShortbow extends Enchantment {
  public EnchantmentShortbow() {
    super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
    this.setName("shortbow");
    this.setRegistryName("shortbow");
    ModObjects.addEnchantment(this);
  }
  
  @Override
  public int getMinLevel() {
    return 1;
  }
  
  @Override
  public int getMaxLevel() {
    return 5;
  }
  
  @Override
  public int getMinEnchantability(int enchantmentLevel) {
    return 5 - enchantmentLevel;
  }
  
  @Override
  public int getMaxEnchantability(int enchantmentLevel) {
    return getMinEnchantability(enchantmentLevel);
  }
  
  @SubscribeEvent(
      priority = EventPriority.HIGHEST,
      receiveCanceled = true
  )
  public void onItemUse(LivingEntityUseItemEvent.Tick event) {
    EntityLivingBase entity = event.getEntityLiving();
    ItemStack item = event.getItem();
    
    if (!(entity instanceof EntityPlayer)) { return; }
    if (!(item.getItem() instanceof ItemBow)) { return; }
    
    ItemBow bow = (ItemBow) item.getItem();
    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModObjects.ENCHANTMENT_SHORTBOW, item);
    
    if (enchantmentLevel == 0) {
      return;
    }
    
    final int maxItemUseDuration = bow.getMaxItemUseDuration(item);
    int cooldown = (int) (ModObjects.ENCHANTMENT_SHORTBOW.getMinEnchantability(enchantmentLevel) * 2.5);
    
    if (cooldown < 1) { cooldown = 1; }
    if (event.getDuration() + cooldown == maxItemUseDuration) {
      bow.onPlayerStoppedUsing(item, entity.getEntityWorld(), entity, 0);
      event.setDuration(maxItemUseDuration);
    }
  }
  
  @SubscribeEvent(
      priority = EventPriority.HIGHEST,
      receiveCanceled = true
  )
  public void afterItemUse(LivingEntityUseItemEvent.Stop event) {
    EntityLivingBase entity = event.getEntityLiving();
    ItemStack item = event.getItem();
    
    if (!(item.getItem() instanceof ItemBow)) { return; }
    
    ItemBow bow = (ItemBow) item.getItem();
    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModObjects.ENCHANTMENT_SHORTBOW, item);
    
    if (enchantmentLevel == 0) {
      return;
    }
    
    event.setCanceled(true);
  }
  
  @SubscribeEvent(
      priority = EventPriority.HIGHEST,
      receiveCanceled = true
  )
  public void onArrowDamaging(LivingHurtEvent event) {
    if (event.getSource().getDamageType().equals("arrow")) {
      if (event.getSource().getImmediateSource() instanceof EntityArrow) {
        event.getEntityLiving().hurtResistantTime = 0;
      }
    }
  }
}
