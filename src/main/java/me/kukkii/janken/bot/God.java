// $Id$

package me.kukkii.janken.bot;

public class God extends AbstractBot {

  public God(){
    super();
  }

  public God(long id, String name){
    super(id, name);
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

  public int hand(int other) {
    if (other < 0) {
      return hand();
    }
    return (other + 2) % 3;
  }

}
