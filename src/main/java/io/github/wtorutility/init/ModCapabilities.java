package io.github.wtorutility.init;

import io.github.wtorutility.api.INoDamageCoolDownCapability;
import io.github.wtorutility.capablility.NoArrowDamageCoolDownCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
  @CapabilityInject(NoClassDefFoundError.class)
  private static Capability<NoArrowDamageCoolDownCapability> noArrowDamageCoolDownCapabilityCapability;
  
  public static void registerModCapabilities() {
    CapabilityManager.INSTANCE.register(INoDamageCoolDownCapability.class, new NoArrowDamageCoolDownCapability.Storage(), NoArrowDamageCoolDownCapability.Implementation.class);
  }
}
