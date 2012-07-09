// $Id$

package me.kukkii.janken.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LogManager {
  
  private PreparedStatement ps;
  private Connection connection;

  public LogManager() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    connection=DriverManager.getConnection("jdbc:mysql://localhost/janken","root","");  
    ps = connection.prepareStatement("INSERT INTO logtable(timestamp, user,bot,userHand,botHand,result) values (?,?,?,?,?,?)"); 
  }		


  public void write(LogItem item) throws Exception {
    ps.setTimestamp(1,item.getTimestamp());
    ps.setString(2,item.getUser().getName());
    ps.setString(3,item.getBot().getName());
    ps.setInt(4,item.getUserHand().value());
    ps.setInt(5,item.getBotHand().value());
    ps.setInt(6,item.getResult().value());
    ps.execute();
  }


}
