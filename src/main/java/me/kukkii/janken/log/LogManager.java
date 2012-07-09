// $Id$

package me.kukkii.janken.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class LogManager {

  private Connection connection;

  public LogManager() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    connection=DriverManager.getConnection("jdbc:mysql://localhost/janken","root","");  // localhost ÇÃïîï™ÇÅ@ÉTÅ[ÉoÅ[ÇÃñºëOÇ©ÅAIPaddress Ç…Ç©Ç¶ÇÈ
  }		


  public void write(LogItem item) throws Exception {
    Statement statement=connection.createStatement();
    statement.execute("INSERT INTO logtable(timestamp, user,bot,userHand,botHand,result) values ('" + item.getTimestamp() + "','" + item.getUser().getName() + "','" + item.getBot().getName() + "'," + item.getUserHand().value() + "," + item.getBotHand().value() + "," + item.getResult().value() + ");");
  }

}
