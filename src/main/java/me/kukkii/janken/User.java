// $Id$

package me.kukkii.janken;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Properties;

public class User extends AbstractPlayer{

  public User() {
    try {
      Properties prop = new Properties();
      prop.load(new FileInputStream("conf/janken.properties"));
      id = Long.parseLong( prop.getProperty("user.id") );
      name = prop.getProperty("user.name");
      // System.err.println("user id=" + userId + " name=" + userName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  public User(long id, String name){
    super(id, name);
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

  public int hand(int other){
    return hand();
  }

}  
