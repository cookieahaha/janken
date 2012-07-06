// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;

abstract public class AbstractBot implements Player {

  abstract public int hand() ;

  public Hand hand2() {
    return Hand.get( hand() );
  }

}
