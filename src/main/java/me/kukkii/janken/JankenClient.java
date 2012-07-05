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
      out.writeInt(1);
      InputStream is = sock.getInputStream();
      DataInputStream out2 = new DataInputStream(is);
      int result = out2.readInt();
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
    catch(Exception e){
    
    }
  }


}
