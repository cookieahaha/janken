package me.kukkii.janken;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class JankenClient implements Constants {
  
  private DataOutputStream out;
  private DataInputStream in;
  private long userId;
  private String userName;

  public JankenClient(){
    try{
      Properties prop = new Properties();
      prop.load(new FileInputStream("conf/janken.properties"));
      String host = prop.getProperty("server.host");
      int port = Integer.parseInt( prop.getProperty("server.port") );
      System.err.println("host=" + host + " port=" + port);

      userId = Long.parseLong( prop.getProperty("user.id") );
      userName = prop.getProperty("user.name");
      System.err.println("user id=" + userId + " name=" + userName);

      Socket sock = new Socket();
      sock.connect(new InetSocketAddress(host, PORT));     
      out = new DataOutputStream(sock.getOutputStream());
      in  = new DataInputStream(sock.getInputStream());
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
      out.writeInt(hand.value());
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
