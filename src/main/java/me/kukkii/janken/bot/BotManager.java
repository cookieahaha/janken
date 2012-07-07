// $Id$

package me.kukkii.janken.bot;

import java.util.ArrayList;
import java.util.List;

import me.kukkii.janken.Player;

public class BotManager {

  // singleton
  private static BotManager manager = new BotManager();

  public static BotManager getManager() {
    return manager;
  }

  //
  private List<Player> bots;

  private BotManager(){
    bots = new ArrayList<Player>();
    bots.add(new RandomBot());
    bots.add(new Rock100Bot());
    bots.add(new Scissor100Bot());
    bots.add(new Paper100Bot());
  }

  public Player next() {
    return bots.get( (int)(Math.random() * bots.size()) );
  }

}
