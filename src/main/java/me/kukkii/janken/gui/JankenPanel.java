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

import me.kukkii.janken.Bot;
import me.kukkii.janken.Hand;
import me.kukkii.janken.Judge;
import me.kukkii.janken.JankenClient;
import me.kukkii.janken.Result;

public class JankenPanel extends JPanel implements ActionListener {

  private static final String guPng = "images/M-j_gu02.png";
  private static final String chPng = "images/M-j_ch02.png";
  private static final String paPng = "images/M-j_pa02.png";

  private BufferedImage guImage = null;
  private BufferedImage chImage = null;
  private BufferedImage paImage = null;

  private JButton guButton = null;
  private JButton chButton = null;
  private JButton paButton = null;

  private Color defaultColor = null;
  private Color winColor = null;
  private Color loseColor = null;
  private Color drawColor = null;
  private Color invalidColor = null;

  private Bot bot;
  private Judge judge;
  private JankenClient client;

  public JankenPanel() {
    try {
      guImage = ImageIO.read(new File(guPng));
      chImage = ImageIO.read(new File(chPng));
      paImage = ImageIO.read(new File(paPng));
    } catch (IOException e) { }

    defaultColor = new Color(255, 255, 255);
    winColor = new Color(128, 128, 255);
    loseColor = new Color(255, 128, 128);
    drawColor = new Color(255, 255, 128);
    invalidColor = new Color(255, 255, 255);

    try {
      // UIManager.setLookAndFeel(new MetalLookAndFeel());
      UIManager.setLookAndFeel(new SynthLookAndFeel());
    } catch (UnsupportedLookAndFeelException e) { }

    setBackground(defaultColor);
    setLayout(new FlowLayout());
    guButton = new JButton(new ImageIcon(guImage));
    guButton.addActionListener(this);;
    guButton.setOpaque(true);
    add(guButton);
    chButton = new JButton(new ImageIcon(chImage));
    chButton.addActionListener(this);;
    chButton.setOpaque(true);
    add(chButton);
    paButton = new JButton(new ImageIcon(paImage));
    paButton.addActionListener(this);;
    paButton.setOpaque(true);
    add(paButton);

 // bot = new Bot();
 // judge = new Judge();
    client = new JankenClient();
  }

  public void actionPerformed(ActionEvent ae) {
    PrintWriter out = null;
    try {
      out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
    }
    catch (UnsupportedEncodingException e) {
      out = new PrintWriter(new OutputStreamWriter(System.out));
    }
    JButton source = (JButton) ae.getSource();
    Hand yourHand = null;
    if (source == guButton) {
      out.println("グゥー :  ✊  : rock");
      yourHand = Hand.ROCK;
    }
    else if (source == chButton) {
      out.println("チョキ :  ✌  : scissor");
      yourHand = Hand.SCISSOR;
    }
    else if (source == paButton) {
      out.println("パァー :  ✋  : paper");
      yourHand = Hand.PAPER;
    }
 // Hand botHand = bot.hand2();
 // int c = judge.compare(yourHand, botHand);
    Result result = client.game(yourHand);

    guButton.setBackground(defaultColor);
    chButton.setBackground(defaultColor);
    paButton.setBackground(defaultColor);

    switch (result) {
    case WIN:
      out.println("勝ち");
      source.setBackground(winColor);
      break;
    case LOSE:
      out.println("負け");
      source.setBackground(loseColor);
      break;
    case DRAW:
      out.println("引き分け");
      source.setBackground(drawColor);
      break;
    case INVALID:
    default:
      out.println("?");
      source.setBackground(invalidColor);
      break;
    }
    out.flush();
  }

  public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame("janken");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JankenPanel());
    frame.pack();
    frame.setVisible(true);
  }
}
