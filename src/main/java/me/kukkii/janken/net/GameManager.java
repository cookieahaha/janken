// $Id$

package me.kukkii.janken.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import me.kukkii.janken.Constants;

public class GameManager implements Constants {

  private static final int timeout_msec = -1;

  private int amountOfGames;
  private Socket sock;

  public static void main(String[] args) throws Exception{
    GameManager gm = new GameManager();
    gm.makeGame();
  }

  public GameManager(){
  }

  public void makeGame() throws Exception{
    Properties prop = new Properties();
    prop.load(new FileInputStream("conf/janken.properties"));
    int port = Integer.parseInt( prop.getProperty("server.port") );
    System.err.println("port=" + port);

    ServerSocket svsock = new ServerSocket(port);
    if (timeout_msec > 0) {
      svsock.setSoTimeout(timeout_msec);
    }

    while(true){
      sock = svsock.accept(); 
      Thread uh = new Thread(new UserHandler(sock));
      uh.start();
      amountOfGames += 1;
    }
  }

  public void killGame() throws Exception {
    sock.close();    
  }

}
