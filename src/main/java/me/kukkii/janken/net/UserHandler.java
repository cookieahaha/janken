// $Id$

package me.kukkii.janken.net;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.EOFException;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Judge;
import me.kukkii.janken.Player;
import me.kukkii.janken.Result;
import me.kukkii.janken.User;
import me.kukkii.janken.bot.RandomBot;

public class UserHandler implements Runnable{
  
  private Socket sock;
  private Player bot;
  private Judge judge;
  private User user;

  public UserHandler(Socket sock){
    this.sock = sock;
    bot = new RandomBot();
    judge = new Judge();
  }

  public void run(){
   try{
      DataInputStream in = new DataInputStream(sock.getInputStream());
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      long id = in.readLong();
      char[] buff = new char[128];
      int i = 0;
      while (true) {
        char c = in.readChar();
        if (c == '\u0000') {
          break;
        }
        buff[i++] = c;
      }
      String name = new String(buff, 0, i);
      user = new User(id, name);

      while(true){ 
        Hand userHand = Hand.get( in.readInt() );
        Hand botHand = bot.hand2();
        Result result = judge.judge(userHand, botHand);
        out.writeInt(result.value());
        System.out.print("user=" + name + "(" + id + ") ");
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
