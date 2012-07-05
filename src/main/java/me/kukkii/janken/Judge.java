// $Id$

package me.kukkii.janken;

import java.util.Comparator;

public class Judge implements Comparator<Hand> {

  public int compare(Hand hand0, Hand hand1) {
    int a = (hand0.value() - hand1.value() + 3) % 3;
    switch (a) {
    case 0 :
      // draw
      return 0;
    case 2 :
      // hand0 won against hand1.
      return 1;
    case 1 :
    default :
      // hand1 wins against hand0.
      return -1;
    }
  }
}
