// $Id$

package me.kukkii.janken.bot;

import java.awt.Image;

import me.kukkii.janken.AbstractPlayer;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;
import me.kukkii.janken.gui.ImageManager;

abstract public class AbstractBot extends AbstractPlayer {

  public AbstractBot() {
    super();
    id *= (-1);
  }

  public AbstractBot(long id, String name) {
    super(id, name);
    id *= (-1);
  }

  public Image getImage() {
    return ImageManager.getManager().getPlayerImage(this);
  }

  abstract public int hand() ;

  public int hand(int other) {
    return hand();
  }

}
