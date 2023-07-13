package io.github.wtorutility.util;

import io.github.wtorutility.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
  private static final Logger LOGGER = LogManager.getLogger(ModInfo.MODID);
  
  public static Logger getLOGGER() {
    return LOGGER;
  }
  
  public static void info(String msg) { LOGGER.info(msg); }
  
  public static void warn(String msg) { LOGGER.warn(msg); }
  
  public static void error(String msg) { LOGGER.error(msg); }
  
  public static void debug(String msg) { LOGGER.debug(msg); }
}
