package me.kukkii.janken;

public class Game2{

  private Player player0;
  private Player player1;
  private Judge judge;

  public static void main(String[] args)throws Exception{
    Game2 game = new Game2(new User(), new Bot());
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
    int n = judge.compare(hand0, hand1);
    switch (n) {
    case 1 :
      System.out.println("You Won!");
      break;
    case -1 :
      System.out.println("You Lost!");
      break;
    case 0 :
      System.out.println("Draw!!");
      break;
    }
  }
}
