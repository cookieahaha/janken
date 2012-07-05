package me.kukkii.janken;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GameManager{

  int port = 50000; 
  int timeout_msec = 10000; // accept() のタイムアウトは10秒にしてみる。
  private int mountOfGames;
  private Socket sock;

  public static void main(String[] args) throws Exception{
    GameManager gm = new GameManager();
    gm.makeGame();
  }

  public GameManager(){
    
  }

  public void makeGame() throws Exception{
    ServerSocket svsock = new ServerSocket(port);
    svsock.setSoTimeout(timeout_msec);  // ※必要があるときのみ設定する。

    sock = svsock.accept();
    InputStream is = sock.getInputStream();
    System.out.println("jan ken pon!");
    DataInputStream input = new DataInputStream(is);
    int userHand = input.readInt();
    int botHand = (int)(Math.random()*3);
    int result = 100;
    if(userHand==botHand){
      result = 0;
    }
    if((userHand==0 && botHand==1) || (userHand==1 && botHand==2) || (userHand==2 && botHand==0)){
      result = 1;
    }
    if((userHand==0 && botHand==2) || (userHand==1 && botHand==0) || (userHand==2 && botHand==1)){
      result = 2;
    }
    DataOutputStream os = new DataOutputStream(sock.getOutputStream());
    os.writeInt(result);

  }


//  public void killGame(){
//    sock.close();    
//  }

}
