package me.kukkii.janken;

public class Game{

  private User user;
  private Bot bot; 

  public static void main(String[] args)throws Exception{
    Game game = new Game();
    while(true){
      System.out.println("jan ken pon!");
      game.compare();
    }
  }

  public Game()throws Exception{
    user = new User();
    bot = new Bot();
  }

  public void compare()throws Exception{
    int userHand = user.hand();
    int botHand = bot.hand();
    if(userHand==botHand){
      System.out.println("ai kode syo!");
      compare();
      return;
    }
    if((userHand==0 && botHand==1) || (userHand==1 && botHand==2) || (userHand==2 && botHand==0)){
      System.out.println("u won!");
      return;
    }
    if((userHand==0 && botHand==2) || (userHand==1 && botHand==0) || (userHand==2 && botHand==1)){
      System.out.println("u lost!");
      return;
    }
  }

}



