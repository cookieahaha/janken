package me.kukkii.janken;

import java.util.Scanner;
import java.util.InputMismatchException;

public class User implements Player{

  private long id;
  private String name;

  public User() {
    this(0L, null);
  }

  public User(long id, String name){
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
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
