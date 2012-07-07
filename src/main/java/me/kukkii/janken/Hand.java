// $Id$

package me.kukkii.janken;

public enum Hand {

  ROCK (0),
  SCISSOR (1),
  PAPER (2),
  UNKNOWN (-1);

  private int value;

  private Hand(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static Hand get(int value) {
    switch (value % 3) {
    case 0 :
      return ROCK;
    case 1 :
      return SCISSOR;
    case 2 :
      return PAPER;
    default :
      return UNKNOWN;
    }
  }
}
