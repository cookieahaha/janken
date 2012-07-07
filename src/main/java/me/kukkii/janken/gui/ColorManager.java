// $Id$

package me.kukkii.janken.gui;

import java.awt.Color;

import me.kukkii.janken.Result;

public class ColorManager {

  // singleton
  private static ColorManager manager = new ColorManager();

  public static ColorManager getManager() {
    return manager;
  }

  //

  private Color defaultColor = null;
  private Color winColor = null;
  private Color loseColor = null;
  private Color drawColor = null;
  private Color invalidColor = null;

  public ColorManager() {
    defaultColor = new Color(255, 255, 255);
    winColor     = new Color(128, 128, 255);
    loseColor    = new Color(255, 128, 128);
    drawColor    = new Color(255, 255, 128);
    invalidColor = new Color(255, 255, 255);
  }

  public Color getDefaultColor() {
    return defaultColor;
  }

  public Color getColor(Result result) {
    switch (result) {
    case WIN:
      return winColor;
    case LOSE:
      return loseColor;
    case DRAW:
      return drawColor;
    case INVALID:
    default:
      return invalidColor;
    }
  }

}
