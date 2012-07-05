package me.kukkii.janken;

public class Bot implements Player{

  public Bot(){
  }

  public int hand(){
    int bot = (int)(Math.random()*3);
    return bot;
  }

  public Hand hand2() {
    return Hand.get( hand() );
  }
}
