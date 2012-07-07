// $Id$

package me.kukkii.janken.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import me.kukkii.janken.Constants;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Judge;
import me.kukkii.janken.Player;
import me.kukkii.janken.Result;
import me.kukkii.janken.bot.RandomBot;
import me.kukkii.janken.net.JankenClient;

public class JankenPanel extends JPanel {

  private static final String guPng = "images/M-j_gu02.png";
  private static final String chPng = "images/M-j_ch02.png";
  private static final String paPng = "images/M-j_pa02.png";

  private BufferedImage guImage = null;
  private BufferedImage chImage = null;
  private BufferedImage paImage = null;

  private JButton guButton = null;
  private JButton chButton = null;
  private JButton paButton = null;

  public JankenPanel(ActionListener listener) {
    try {
      guImage = ImageIO.read(new File(guPng));
      chImage = ImageIO.read(new File(chPng));
      paImage = ImageIO.read(new File(paPng));
    } catch (IOException e) { }

    setBackground(ColorManager.getManager().getDefaultColor());
    setLayout(new FlowLayout());
    guButton = new JButton(new ImageIcon(guImage));
    guButton.addActionListener(listener);;
    guButton.setOpaque(true);
    add(guButton);
    chButton = new JButton(new ImageIcon(chImage));
    chButton.addActionListener(listener);;
    chButton.setOpaque(true);
    add(chButton);
    paButton = new JButton(new ImageIcon(paImage));
    paButton.addActionListener(listener);;
    paButton.setOpaque(true);
    add(paButton);
  }

  private PrintWriter getWriter() {
    PrintWriter out = null;
    try {
      out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
    }
    catch (UnsupportedEncodingException e) {
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    return out;
  }

  public Hand getHand(JButton source) {
    Hand yourHand = null;
    if (source == guButton) {
      yourHand = Hand.ROCK;
    }
    else if (source == chButton) {
      yourHand = Hand.SCISSOR;
    }
    else if (source == paButton) {
      yourHand = Hand.PAPER;
    }
    return yourHand;
  }

  public void clearColors() {
    Color defaultColor = ColorManager.getManager().getDefaultColor();
    guButton.setBackground(defaultColor);
    chButton.setBackground(defaultColor);
    paButton.setBackground(defaultColor);
  }

  public void setResultColor(JButton button, Result result) {
    button.setBackground(ColorManager.getManager().getColor(result));
  }

  public void setResultColor(Hand hand, Result result) {
    JButton button = null;
    switch (hand) {
    case ROCK:
      button = guButton;
      break;
    case SCISSOR:
      button = chButton;
      break;
    case PAPER:
      button = paButton;
      break;
    }
    setResultColor(button, result);
  }

}
