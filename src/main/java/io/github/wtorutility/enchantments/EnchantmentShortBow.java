package io.github.wtorutility.enchantments;

import io.github.wtorutility.init.ModConfigs;
import io.github.wtorutility.init.ModObjects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentShortBow extends Enchantment {
  public EnchantmentShortBow() {
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
    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModObjects.ENCHANTMENT_SHORT_BOW, item);
    
    if (enchantmentLevel == 0) {
      return;
    }
    
    final int maxItemUseDuration = bow.getMaxItemUseDuration(item);
    int cooldown = (int) (ModObjects.ENCHANTMENT_SHORT_BOW.getMinEnchantability(enchantmentLevel) * 2.5);
    
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
    ItemStack item = event.getItem();
    
    if (!(item.getItem() instanceof ItemBow)) { return; }
    
    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModObjects.ENCHANTMENT_SHORT_BOW, item);
    
    if (enchantmentLevel == 0) {
      return;
    }
    
    event.setCanceled(true);
  }
  
  @SubscribeEvent(
      priority = EventPriority.LOWEST,
      receiveCanceled = true
  )
  public void onArrowSpawn(EntityJoinWorldEvent event) {
    if (!(event.getEntity() instanceof EntityArrow)) {
      return;
    }
    
    EntityArrow arrow = (EntityArrow) event.getEntity();
    EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
    
    if (shooter == null) {
      return;
    }
    
    ItemStack bow = shooter.getActiveItemStack();
    if (bow == ItemStack.EMPTY) {
      bow = shooter.getHeldItemOffhand();
      if (bow == ItemStack.EMPTY) {
        return;
      }
    }
    
    if (ModConfigs.noArrowDamageCoolDownType == 0) {
      return;
    }
    
    if (ModConfigs.noArrowDamageCoolDownType == 2 || (ModConfigs.noArrowDamageCoolDownType == 1) && (EnchantmentHelper.getEnchantmentLevel(ModObjects.ENCHANTMENT_SHORT_BOW, bow) > 0)) {
      arrow.getEntityData().setBoolean("NoDamageCoolDown", true);
    }
  }
  
  @SubscribeEvent(
          priority = EventPriority.LOWEST,
          receiveCanceled = true
  )
  public void onArrowHit(LivingHurtEvent event) {
    if (!event.getSource().getDamageType().equals("arrow")) {
      return;
    }
    
    if (!(event.getSource().getImmediateSource() instanceof EntityArrow)) {
      return;
    }
    
    if (ModConfigs.noArrowDamageCoolDownType == 2) {
      event.getEntityLiving().hurtResistantTime = 0;
      return;
    }
    
    if (ModConfigs.noArrowDamageCoolDownType == 1) {
      if (!(event.getSource().getTrueSource() instanceof EntityPlayer)) {
        return;
      }
      
      EntityArrow arrow = (EntityArrow) event.getSource().getImmediateSource();
      
      if (!arrow.getEntityData().hasKey("NoDamageCoolDown")) {
        return;
      }
      
      if (!arrow.getEntityData().getBoolean("NoDamageCoolDown")) {
        return;
      }
      
      event.getEntityLiving().hurtResistantTime = 0;
    }
  }
}
