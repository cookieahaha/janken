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
import java.sql.Timestamp;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Judge;
import me.kukkii.janken.Player;
import me.kukkii.janken.Result;
import me.kukkii.janken.User;
import me.kukkii.janken.bot.BotManager;
import me.kukkii.janken.bot.RandomBot;
import me.kukkii.janken.log.LogManager;
import me.kukkii.janken.log.LogItem;

public class UserHandler implements Runnable{

  private static final boolean dbLog = false;
  
  private Socket sock;
  private Judge judge;
  private User user;
  LogManager lm = null;

  public UserHandler(Socket sock){
    this.sock = sock;
    judge = new Judge();
    if (dbLog) {
      try {
        lm = new LogManager();      
      }
      catch (Exception e) {
        lm = null;
      }
    }
  }

  public void run(){
   try{
      DataInputStream in = new DataInputStream(sock.getInputStream());
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());

      long id = in.readLong();
      String name = NetUtils.receiveString(in);
      user = new User(id, name);

      while(true){ 
        Player bot = BotManager.getManager().next();
        NetUtils.sendString(out, bot.getName());

        Hand userHand = Hand.get( in.readInt() );
        Hand botHand = bot.hand2(userHand);
        out.writeInt(botHand.value());
        Result result = judge.judge(userHand, botHand);
        out.writeInt(result.value());

        System.out.print("user=" + name + "(" + id + ") bot=" + bot.getName());
        System.out.println(" result: " + result + " user: " + userHand + " bot: " + botHand);
        if (lm != null) {
          LogItem li = new LogItem(new Timestamp(System.currentTimeMillis()), user, bot, userHand, botHand, result);
          lm.write(li);
        }
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
