// $Id$

package me.kukkii.janken.log;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;
import me.kukkii.janken.Result;

public class LogItem implements Serializable {

  private Timestamp timestamp;
  private Player user;
  private Player bot;
  private Hand userHand;
  private Hand botHand;
  private Result result;

  public LogItem(Timestamp timestamp, Player user, Player bot, Hand userHand, Hand botHand, Result result) {
    this.timestamp = timestamp;
    this.user = user;
    this.bot = bot;
    this.userHand = userHand;
    this.botHand = botHand;
    this.result = result;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Player getUser() {
    return user;
  }

  public Player getBot() {
    return bot;
  }

  public Hand getUserHand() {
    return userHand;
  }

  public Hand getBotHand() {
    return botHand;
  }

  public Result getResult() {
    return result;
  }

}
