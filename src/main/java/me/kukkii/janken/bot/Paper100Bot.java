// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;

public class Paper100Bot extends AbstractBot {

  public Paper100Bot(){
    super();
  }

  public Paper100Bot(long id, String name){
    super(id, name);
  }

  public int hand(){
    return Hand.PAPER.value();
  }

}
