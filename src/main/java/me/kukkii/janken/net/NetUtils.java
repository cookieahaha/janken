// $Id$

package me.kukkii.janken.net;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class NetUtils {
  
  public static void sendString(DataOutputStream out, String text) throws IOException {
    for (char c : text.toCharArray()) {
      out.writeChar(c);
    }
    out.writeChar('\u0000');
  }  

  public static String receiveString(DataInputStream in) throws IOException {
    char[] buff = new char[128];
    int i = 0;
    while (true) {
      char c = in.readChar();
      if (c == '\u0000') {
        break;
      }
      buff[i++] = c;
    }
    return new String(buff, 0, i);
  }

}
