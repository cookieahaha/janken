// $Id$

package me.kukkii.janken;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.EOFException;

import me.kukkii.janken.bot.RandomBot;

public class UserHandler implements Runnable{
  
  private Socket sock;
  private Player bot;
  private Judge judge;

  public UserHandler(Socket sock){
    this.sock = sock;
    bot = new RandomBot();
    judge = new Judge();
  }

  public void run(){
   try{
      System.out.println("jan ken pon!");
      DataInputStream in = new DataInputStream(sock.getInputStream());
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      while(true){ 
        Hand userHand = Hand.get( in.readInt() );
        Hand botHand = bot.hand2();
        Result result = judge.judge(userHand, botHand);
        out.writeInt(result.value());
        System.out.println("result: " + result + " user's hand: " + userHand);
      }
    }
    catch(EOFException e){
      System.out.println("user quited");
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

}
