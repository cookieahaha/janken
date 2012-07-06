// $Id$

package me.kukkii.janken.bot;

public class RandomBot extends AbstractBot {

  public RandomBot(){
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

}
