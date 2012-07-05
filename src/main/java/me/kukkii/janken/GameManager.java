package me.kukkii.janken;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;

public class GameManager{

  int port = 50000; 
//  int timeout_msec = 10000; // accept() のタイムアウトは10秒にしてみる。
  private int amountOfGames;
  private Socket sock;

  public static void main(String[] args) throws Exception{
    GameManager gm = new GameManager();
    gm.makeGame();
  }

  public GameManager(){
    
  }

  public void makeGame() throws Exception{
    ServerSocket svsock = new ServerSocket(port);
//    svsock.setSoTimeout(timeout_msec);  // ※必要があるときのみ設定する。

    while(true){
      sock = svsock.accept(); 
      Thread uh = new Thread(new UserHandler(sock));
      uh.start();
      amountOfGames += 1;
    }

  }


//  public void killGame(){
//    sock.close();    
//  }

}
