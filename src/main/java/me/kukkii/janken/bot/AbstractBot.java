// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;

abstract public class AbstractBot implements Player {

  protected long id;
  protected String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  abstract public int hand() ;

  public Hand hand2() {
    return Hand.get( hand() );
  }

}
