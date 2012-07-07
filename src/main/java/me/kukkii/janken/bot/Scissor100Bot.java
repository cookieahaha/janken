// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;

public class Scissor100Bot extends AbstractBot {

  public Scissor100Bot(){
    super();
  }

  public Scissor100Bot(long id, String name){
    super(id, name);
  }

  public int hand(){
    return Hand.SCISSOR.value();
  }

}
