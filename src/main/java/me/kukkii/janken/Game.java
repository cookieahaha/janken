package me.kukkii.janken;

import java.util.Scanner;

public class Game{

  private int user;
  private int bot;

  public static void main(String[] args)throws Exception{
    while(true){
      Game game = new Game();
      game.compare();
    }
  }

  public Game()throws Exception{
    Scanner scan = new Scanner(System.in);
    System.out.println("jan ken pon!");
    int user = scan.nextInt();
    int bot = (int)(Math.random()*3);
  }

  public void compare()throws Exception{
    if(user==bot){
      System.out.println("ai kode syo!");
      Scanner scan = new Scanner(System.in);    
      user = scan.nextInt();
      bot = (int)(Math.random()*3);
      compare();
      return;
    }
    if((user==0 && bot==1) || (user==1 && bot==2) || (user==2 && bot==0)){
      System.out.println("u won!");
      return;
    }
    if((user==0 && bot==2) || (user==1 && bot==0) || (user==2 && bot==1)){
      System.out.println("u lost!");
      return;
    }
  }

}



