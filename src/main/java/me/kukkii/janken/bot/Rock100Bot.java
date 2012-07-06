// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;

public class Rock100Bot extends AbstractBot {

  public Rock100Bot(){
  }

  public int hand(){
    return Hand.ROCK.value();
  }

}
