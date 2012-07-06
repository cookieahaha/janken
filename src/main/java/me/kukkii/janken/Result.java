// $Id$

package me.kukkii.janken;

public enum Result {

  WIN (1),
  DRAW (0),
  LOSE (-1),
  INVALID (-2);

  private int value;

  private Result(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static Result get(int value) {
    switch (value) {
    case 1 :
      return WIN;
    case 0 :
      return DRAW;
    case -1 :
      return LOSE;
    case -2 :
    default :
      return INVALID;
    }
  }
}
