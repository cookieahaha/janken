// $Id$

package me.kukkii.janken.bot;

public class RotationBot extends AbstractBot {

  private int prevHand = -1;

  public RotationBot(){
    super();
  }

  public RotationBot(long id, String name){
    super(id, name);
  }

  public int hand(){
    if (prevHand < 0) {
      prevHand = (int)(Math.random()*3);
    }
    else {
      prevHand = (prevHand + 1) % 3;
    }
    return prevHand;
  }

}
