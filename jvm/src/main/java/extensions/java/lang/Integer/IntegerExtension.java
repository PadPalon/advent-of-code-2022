package extensions.java.lang.Integer;

import manifold.ext.rt.api.Extension;

@Extension
public class IntegerExtension {
  @Extension
  public static int parseInt(char character) {
    return Integer.parseInt("$character");
  }
}
