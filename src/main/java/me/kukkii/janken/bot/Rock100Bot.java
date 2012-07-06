// $Id$

package me.kukkii.janken.bot;

import me.kukkii.janken.Hand;

public class Rock100Bot extends AbstractBot {

  public Rock100Bot(){
    this(0L, null);
  }

  public Rock100Bot(long id, String name){
    this.id = id;
    this.name = name;
  }

  public int hand(){
    return Hand.ROCK.value();
  }

}
