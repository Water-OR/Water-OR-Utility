package io.github.wtorutility.util;

import io.github.wtorutility.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
  private static final Logger LOG = LogManager.getLogger(ModInfo.MODID);
  
  public static Logger getLOGGER() {
    return LOG;
  }
  
  public static void info(String msg) { LOG.info(msg); }
  
  public static void warn(String msg) { LOG.warn(msg); }
  
  public static void error(String msg) { LOG.error(msg); }
  
  public static void debug(String msg) { LOG.debug(msg); }
}
