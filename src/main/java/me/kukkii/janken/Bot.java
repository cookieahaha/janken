package me.kukkii.janken;

public class Bot implements Player{

  int bot;

  public Bot(){
  }

  public int hand(){
    bot = (int)(Math.random()*3);
    return bot;
  }
}
