package io.github.wtorutility.capablility;

import io.github.wtorutility.api.INoDamageCoolDownCapability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class NoArrowDamageCoolDownCapability {
  public static class Storage implements Capability.IStorage<INoDamageCoolDownCapability> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<INoDamageCoolDownCapability> capability, INoDamageCoolDownCapability instance, EnumFacing side) {
      NBTTagCompound compound = new NBTTagCompound();
      compound.setBoolean("EnableNoArrowDamageCoolDown", instance.getEnableNoDamageCoolDown());
      return compound;
    }
    
    @Override
    public void readNBT(Capability<INoDamageCoolDownCapability> capability, INoDamageCoolDownCapability instance, EnumFacing side, NBTBase nbt) {
      NBTTagCompound compound = (NBTTagCompound) nbt;
      boolean enabled = true;
      if (compound.hasKey("EnableNoArrowDamageCoolDown")) {
        enabled = compound.getBoolean("EnableNoArrowDamageCoolDown");
      }
      instance.setEnableNoDamageCoolDown(enabled);
    }
  }
  
  public static class Implementation implements INoDamageCoolDownCapability {
    private boolean EnableNoArrowDamageCoolDown = true;
    
    @Override
    public void setEnableNoDamageCoolDown(boolean enableNoDamageCooldown) {
      EnableNoArrowDamageCoolDown = enableNoDamageCooldown;
    }
    
    @Override
    public boolean getEnableNoDamageCoolDown() {
      return EnableNoArrowDamageCoolDown;
    }
  }
}
