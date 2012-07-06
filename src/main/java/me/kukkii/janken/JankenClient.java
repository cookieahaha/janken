package me.kukkii.janken;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;

public class JankenClient implements Constants {
  
  private static String host ="localhost";
  private DataOutputStream out;
  private DataInputStream in;

  public JankenClient(){
    try{
      Socket sock = new Socket();
      sock.connect(new InetSocketAddress(host,PORT));     
      out = new DataOutputStream(sock.getOutputStream());
      InputStream is = sock.getInputStream();
      in = new DataInputStream(is);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }  

  public static void main(String[] args){
    JankenClient jc = new JankenClient();
    while(true){
      Result result = jc.game(Hand.ROCK);
      jc.showResult(result);
    }
  }

  public Result game(Hand hand){
    try{
      out.writeInt(hand.value());   // choki 4testing
      int result = in.readInt();
      return Result.get(result);
    }
    catch(Exception e){
      return Result.INVALID;
    }
  }

  public static void showResult(Result result){
    switch (result) {
    case INVALID:
      System.err.println("server error");
      break;
    case DRAW:
      System.out.println("tie");
      break;
    case WIN:
      System.out.println("u win");
      break;
    case LOSE:
      System.out.println("u lose");
      break;
    } 
  }
}
