package me.kukkii.janken;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;

public class JankenClient{
  
  private static String host ="localhost";
  private static int port = 50000;
  private DataOutputStream out;
  private DataInputStream in;

  public JankenClient(){
    try{
      Socket sock = new Socket();
      sock.connect(new InetSocketAddress(host,port));     
      out = new DataOutputStream(sock.getOutputStream());
      InputStream is = sock.getInputStream();
      in = new DataInputStream(is);
    }
    catch (Exception e){
    }
  }  

  public static void main(String[] args){
    JankenClient jc = new JankenClient();
    while(true){
      int result = jc.game(Hand.ROCK);
      jc.showResult(result);
    }
  }

  public int game(Hand hand){
    try{
      out.writeInt(hand.value());   // choki 4testing
      int result = in.readInt();
      return result;
    }
    catch(Exception e){
      return -1;
    }
  }

  public static void showResult(int result){
    if(result == -1){
      System.err.println("server error");
    }
    if(result == 0){
      System.out.println("tie");
    }
    if(result == 1){
      System.out.println("u win");
    }  
    if(result == 2){
      System.out.println("u lose");
    } 
  }
}
