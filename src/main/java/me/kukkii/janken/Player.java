// $Id$

package me.kukkii.janken;

public interface Player{

  public long getId();

  public String getName();

  public int hand();

  public Hand hand2();

  public int hand(int other);

  public Hand hand2(Hand other);

}
