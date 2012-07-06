// $Id$

package me.kukkii.janken;

import me.kukkii.janken.bot.RandomBot;

public class Game{

  private Player user;
  private Player bot; 
  private Judge judge; 

  public static void main(String[] args)throws Exception{
    Game game = new Game();
    while(true){
      System.out.println("jan ken pon!");
      game.compare();
    }
  }

  public Game()throws Exception{
    user = new User();
    bot = new RandomBot();
    judge = new Judge();
  }

  public void compare()throws Exception{
    Hand userHand = user.hand2();
    Hand botHand = bot.hand2();
    switch (judge.judge(userHand, botHand)) {
    case DRAW:
      System.out.println("ai kode syo!");
      compare();
      return;
    case WIN:
      System.out.println("u won!");
      return;
    case LOSE:
      System.out.println("u lost!");
      return;
    }
  }

}



