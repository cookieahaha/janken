// $Id$

package me.kukkii.janken.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import me.kukkii.janken.AbstractPlayer;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Player;
import me.kukkii.janken.bot.AbstractBot;

public class ImageManager {

  // singleton
  private static ImageManager manager = new ImageManager();

  public static ImageManager getManager() {
    return manager;
  }

  //

  private static final String guPng = "images/M-j_gu02.png";
  private static final String chPng = "images/M-j_ch02.png";
  private static final String paPng = "images/M-j_pa02.png";

  private static final String chromePng           = "images/Girls/chrome-256.png";
  private static final String internetExplorerPng = "images/Girls/internet-explorer-256.png";
  private static final String safariPng           = "images/Girls/safari-256.png";
  private static final String firefoxPng          = "images/Girls/firefox-256.png";
  private static final String operaPng            = "images/Girls/opera-256.png";

  private BufferedImage guImage = null;
  private BufferedImage chImage = null;
  private BufferedImage paImage = null;

  private BufferedImage chromeImage = null;
  private BufferedImage internetExplorerImage = null;
  private BufferedImage safariImage = null;
  private BufferedImage firefoxImage = null;
  private BufferedImage operaImage = null;

  public ImageManager() {
    initHandImages();
    initGirlImages();
  }

  private void initHandImages() {
    try {
      guImage = ImageIO.read(new File(guPng));
      chImage = ImageIO.read(new File(chPng));
      paImage = ImageIO.read(new File(paPng));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void initGirlImages() {
    try {
      chromeImage           = ImageIO.read(new File(chromePng));
      internetExplorerImage = ImageIO.read(new File(internetExplorerPng));
      safariImage           = ImageIO.read(new File(safariPng));
      firefoxImage          = ImageIO.read(new File(firefoxPng));
      operaImage            = ImageIO.read(new File(operaPng));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Image getHandImage(Hand hand) {
    switch (hand) {
    case ROCK :
      return guImage;
    case SCISSOR :
      return chImage;
    case PAPER :
      return paImage;
    default :
      return null;
    }
  }

  public Image getPlayerImage(Player player) {
    if (! (player instanceof AbstractBot)) {
      return null;
    }
    String name = player.getName();
    if (name.equals("God")) {
      return safariImage;
    }
    else if (name.equals("RandomBot")) {
      return chromeImage;
    }
    else if (name.equals("RotationBot")) {
      return firefoxImage;
    }
    else if (name.equals("Rock100Bot")) {
      return internetExplorerImage;
    }
    else if (name.equals("Paper100Bot")) {
      return internetExplorerImage;
    }
    else if (name.equals("Scissor100Bot")) {
      return internetExplorerImage;
    }
    else {
      return null;
    }
  }

}
