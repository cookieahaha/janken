// $Id$

package me.kukkii.janken.net;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

import me.kukkii.janken.Constants;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Result;
import me.kukkii.janken.User;

public class JankenClient implements Constants {
  
  private DataOutputStream out;
  private DataInputStream in;
  private User user;

  public JankenClient() throws IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream("conf/janken.properties"));
    String host = prop.getProperty("server.host");
    int port = Integer.parseInt( prop.getProperty("server.port") );
    System.err.println("host=" + host + " port=" + port);

    Socket sock = new Socket();
    sock.connect(new InetSocketAddress(host, port));     
    out = new DataOutputStream(sock.getOutputStream());
    in  = new DataInputStream(sock.getInputStream());

    user = new User();
    out.writeLong( user.getId() );
    NetUtils.sendString(out, user.getName());
  }  

  public String receiveBotName() throws IOException {
    return NetUtils.receiveString(in);
  }

  public void sendHand(Hand hand) throws IOException {
    out.writeInt(hand.value());
  }

  public Hand receiveBotHand() throws IOException {
    int hand = in.readInt();
    return Hand.get(hand);
  }

  public Result receiveResult() throws IOException {
    int result = in.readInt();
    return Result.get(result);
  }

  //

  public static void main(String[] args) throws Exception {
    JankenClient jc = new JankenClient();
    while(true){
      jc.receiveBotName();
      jc.sendHand(Hand.ROCK);
      jc.receiveBotHand();
      Result result = jc.receiveResult();
      showResult(result);
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
