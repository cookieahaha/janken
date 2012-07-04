package me.kukkii.janken;

import java.util.Scanner;
import java.util.InputMismatchException;

public class User implements Player{

  int user;

  public User(){
  }

  public int hand(){
    try{
      Scanner scan = new Scanner(System.in);
      user = scan.nextInt();
      return user;
    }
    catch(InputMismatchException e){
      System.out.println("enter 0=guu 1=choki 2=paa");
      hand();
      return hand();
    } 
  }
}  
