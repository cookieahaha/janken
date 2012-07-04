// $Id$

package me.kukkii.janken.gui;

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

  public JankenPanel() {
    try {
      guImage = ImageIO.read(new File(guPng));
      chImage = ImageIO.read(new File(chPng));
      paImage = ImageIO.read(new File(paPng));
    } catch (IOException e) {
    }
    setLayout(new FlowLayout());
    guButton = new JButton(new ImageIcon(guImage));
    guButton.addActionListener(this);;
    add(guButton);
    chButton = new JButton(new ImageIcon(chImage));
    chButton.addActionListener(this);;
    add(chButton);
    paButton = new JButton(new ImageIcon(paImage));
    paButton.addActionListener(this);;
    add(paButton);
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
    if (source == guButton) {
      out.println("グゥー : rock");
    }
    else if (source == chButton) {
      out.println("チョキ : scissor");
    }
    else if (source == paButton) {
      out.println("パァー - paper");
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
