package me.kukkii.janken;

import java.util.Scanner;
import java.util.InputMismatchException;

public class User implements Player{

  public User(){
  }

  public int hand(){
    try{
      Scanner scan = new Scanner(System.in);
      int user = scan.nextInt() % 3;
      return user;
    }
    catch(InputMismatchException e){
      System.out.println("enter 0=guu 1=choki 2=paa");
      hand();
      return hand();
    } 
  }

  public Hand hand2() {
    return Hand.get( hand() );
  }
}  
