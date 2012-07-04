// $Id$

package me.kukkii.janken.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    JButton source = (JButton) ae.getSource();
    if (source == guButton) {
      System.err.println("gu - rock");
    }
    else if (source == chButton) {
      System.err.println("choki - scissor");
    }
    else if (source == paButton) {
      System.err.println("pa - paper");
    }
  }

  public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame("janken");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JankenPanel());
    frame.pack();
    frame.setVisible(true);
  }
}
