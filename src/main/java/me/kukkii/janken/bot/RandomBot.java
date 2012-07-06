// $Id$

package me.kukkii.janken.bot;

public class RandomBot extends AbstractBot {

  public RandomBot(){
    this(0L, null);
  }

  public RandomBot(long id, String name){
    this.id = id;
    this.name = name;
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

}
