package me.kukkii.janken;

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.EOFException;

public class UserHandler implements Runnable{
  
  private Socket sock;

  public UserHandler(Socket sock){
    this.sock = sock;
  }

  public void run(){
   try{
      InputStream is = sock.getInputStream();
      System.out.println("jan ken pon!");
      DataInputStream input = new DataInputStream(is);
      DataOutputStream os = new DataOutputStream(sock.getOutputStream());
      while(true){ 
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
        os.writeInt(result);
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
