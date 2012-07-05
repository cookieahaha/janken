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

  public static void main(String[] args){
    try{
      Socket sock = new Socket();
      sock.connect(new InetSocketAddress(host,port));     
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      InputStream is = sock.getInputStream();
      DataInputStream in = new DataInputStream(is);
      while(true){
        out.writeInt(1);   // choki 4testing
        int result = in.readInt();
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
    catch(Exception e){
    
    }
  }


}
