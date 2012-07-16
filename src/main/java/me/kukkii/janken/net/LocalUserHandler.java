// $Id$

package me.kukkii.janken.net;

import java.sql.SQLException;
import java.sql.Timestamp;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Judge;
import me.kukkii.janken.Player;
import me.kukkii.janken.Result;
import me.kukkii.janken.User;
import me.kukkii.janken.bot.BotManager;
import me.kukkii.janken.bot.RandomBot;
import me.kukkii.janken.log.LogManager;
import me.kukkii.janken.log.LogItem;

public class LocalUserHandler {

  private static final boolean dbLog = false;
  
  private Judge judge;
  private User user;
  private Player bot;
  private Hand userHand;
  private Hand botHand;
  private Result result;

  public LocalUserHandler(){
    judge = new Judge();
  }

  public void init(User user) {
    this.user = user;
  }

  public Player nextBot() {
    bot = BotManager.getManager().next();
    return bot;
  }

  public void hand(Hand userHand) {
    this.userHand = userHand;
  }

  public Hand getBotHand() {
    botHand = bot.hand2(userHand);
    return botHand;
  }

  public Result getResult() {
    result = judge.judge(userHand, botHand);
    writeLog();
    return result;
  }

  public void writeLog() {
    System.out.print("user=" + user.getName() + "(" + user.getId() + ") bot=" + bot.getName() + "(" + bot.getId() + ")");
    System.out.println(" result: " + result + " user: " + userHand + " bot: " + botHand);
    if (dbLog) {
      LogItem logItem = new LogItem(new Timestamp(System.currentTimeMillis()), user, bot, userHand, botHand, result);
      try {
        LogManager.getManager().write(logItem);
      }
      catch (SQLException e) {
        System.err.println(e.toString());
      }
    }
  }

}
