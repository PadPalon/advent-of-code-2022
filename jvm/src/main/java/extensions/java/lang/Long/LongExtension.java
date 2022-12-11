package extensions.java.lang.Long;

import manifold.ext.rt.api.Extension;

@Extension
public class LongExtension {
  @Extension
  public static long parseLong(char character) {
    return Long.parseLong("$character");
  }
}
