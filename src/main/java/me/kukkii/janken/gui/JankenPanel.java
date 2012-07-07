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

  private Player bot;
  private Judge judge;
  private JankenClient client;

  public JankenPanel() {
    try {
      guImage = ImageIO.read(new File(guPng));
      chImage = ImageIO.read(new File(chPng));
      paImage = ImageIO.read(new File(paPng));
    } catch (IOException e) { }

    setBackground(ColorManager.getManager().getDefaultColor());
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

    try {
      client = new JankenClient();

      PrintWriter out = getWriter();
      String botName = client.receiveBotName();
      out.println("bot=" + botName);
      out.flush();
    } catch (IOException e) { }
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

  public void actionPerformed(ActionEvent ae) {
    PrintWriter out = getWriter();
    JButton source = (JButton) ae.getSource();
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

    Hand botHand = null;
    Result result = null;
    try {
      client.sendHand(yourHand);
      botHand = client.receiveBotHand();
      result = client.receiveResult();
    } catch (IOException e) { }

    out.println("you=" + yourHand + " bot=" + botHand + " result=" + result);

    Color defaultColor = ColorManager.getManager().getDefaultColor();
    guButton.setBackground(defaultColor);
    chButton.setBackground(defaultColor);
    paButton.setBackground(defaultColor);

    source.setBackground(ColorManager.getManager().getColor(result));

    try {
      String botName = client.receiveBotName();
      out.println("bot=" + botName);
      out.flush();
    } catch (IOException e) { }
  }

  public static void main(String[] args) throws Exception {
    try {
      // UIManager.setLookAndFeel(new MetalLookAndFeel());
      UIManager.setLookAndFeel(new SynthLookAndFeel());
    } catch (UnsupportedLookAndFeelException e) { }

    JFrame frame = new JFrame("janken");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JankenPanel());
    frame.pack();
    frame.setVisible(true);
  }
}
