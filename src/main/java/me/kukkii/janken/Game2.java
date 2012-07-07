// $Id$

package me.kukkii.janken;

import me.kukkii.janken.bot.RandomBot;

public class Game2{

  private Player player0;
  private Player player1;
  private Judge judge;

  public static void main(String[] args)throws Exception{
    Game2 game = new Game2(new User(), new RandomBot());
    while(true){
      System.out.println("Jan Ken Pon!");
      game.play();
    }
  }

  public Game2(Player player0, Player player1) throws Exception{
    this.player0 = player0;
    this.player1 = player1;
    judge = new Judge();
  }

  public void play() throws Exception{
    Hand hand0 = player0.hand2();
    Hand hand1 = player1.hand2();
    System.out.println("You: " + hand0);
    System.out.println("Bot: " + hand1);
    switch (judge.judge(hand0, hand1)) {
    case WIN :
      System.out.println("You Won!");
      break;
    case LOSE :
      System.out.println("You Lost!");
      break;
    case DRAW :
      System.out.println("Draw!!");
      break;
    }
  }
}
