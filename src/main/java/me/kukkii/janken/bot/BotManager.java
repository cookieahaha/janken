// $Id$

package me.kukkii.janken.bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.kukkii.janken.Player;

public class BotManager {

  // singleton
  private static BotManager manager = new BotManager();

  public static BotManager getManager() {
    return manager;
  }

  //
  private List<Player> bots;
  private Map<String, Player> map;

  private BotManager(){
    bots = new ArrayList<Player>();
    map = new HashMap<String, Player>();

    add(new Rock100Bot());
    add(new Scissor100Bot());
    add(new Paper100Bot());
    add(new RotationBot());
    add(new RandomBot());
    add(new God());
  }

  private void add(Player bot) {
    bots.add(bot);
    map.put(bot.getName(), bot);
  }

  public Player next() {
    return bots.get( (int)(Math.random() * bots.size()) );
  }

  public Player getBot(String name) {
    return map.get(name);
  }
}
