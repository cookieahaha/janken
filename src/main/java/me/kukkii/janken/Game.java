package me.kukkii.janken;

public class Game{

  private User user;
  private Bot bot; 

  public static void main(String[] args)throws Exception{
    while(true){
      Game game = new Game();
      game.compare();
    }
  }

  public Game()throws Exception{
    user = new User();
    bot = new Bot();
    System.out.println("jan ken pon!");
    user.hand();    
    bot.hand();
  }

  public void compare()throws Exception{
    if(user.hand()==bot.hand()){
      System.out.println("ai kode syo!");
      user.hand();
      bot.hand();
      compare();
      return;
    }
    if((user.hand()==0 && bot.hand()==1) || (user.hand()==1 && bot.hand()==2) || (user.hand()==2 && bot.hand()==0)){
      System.out.println("u won!");
      return;
    }
    if((user.hand()==0 && bot.hand()==2) || (user.hand()==1 && bot.hand()==0) || (user.hand()==2 && bot.hand()==1)){
      System.out.println("u lost!");
      return;
    }
  }

}



