// $Id$

package me.kukkii.janken.log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogManager {

  private static final String JDBCURL    = "jdbc:mysql://localhost/janken";
  private static final String JDBCUSER   = "root";
  private static final String JDBCPASSWD = "";
  
  // singleton
  private static LogManager manager = new LogManager();

  public static LogManager getManager() {
    return manager;
  }

  //

  private Connection connection;
  private PreparedStatement ps;

  private LogManager() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
      ps = null;
    }
    try {
      connection=DriverManager.getConnection(JDBCURL, JDBCUSER, JDBCPASSWD);
      ps = connection.prepareStatement("INSERT INTO logtable(timestamp, user,bot,userHand,botHand,result) VALUES (?,?,?,?,?,?)");
    }
    catch (SQLException e) {
      e.printStackTrace();
      ps = null;
    }
  }		

  public void close() throws SQLException {
    if (ps == null) {
      return;
    }
    ps.close();
    connection.close();
  }

  public void write(LogItem item) throws SQLException {
    if (ps == null) {
      return;
    }
    ps.setTimestamp(1,item.getTimestamp());
    ps.setString(2,item.getUser().getName());
    ps.setString(3,item.getBot().getName());
    ps.setInt(4,item.getUserHand().value());
    ps.setInt(5,item.getBotHand().value());
    ps.setInt(6,item.getResult().value());
    ps.execute();
  }

}
