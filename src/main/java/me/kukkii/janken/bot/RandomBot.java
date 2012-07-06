// $Id$

package me.kukkii.janken.bot;

public class RandomBot extends AbstractBot {

  public RandomBot(){
    super();
  }

  public RandomBot(long id, String name){
    super(id, name);
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

}
