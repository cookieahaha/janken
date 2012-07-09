// $Id$

package me.kukkii.janken.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Result;
import me.kukkii.janken.net.JankenClient;

public class JankenPanel extends JPanel {

  private JButton guButton = null;
  private JButton chButton = null;
  private JButton paButton = null;

  public JankenPanel(ActionListener listener) {
    setBackground(ColorManager.getManager().getDefaultColor());
    setOpaque(true);

    setLayout(new FlowLayout());

    ImageManager imageManager = ImageManager.getManager();
    guButton = new JButton( new ImageIcon(imageManager.getHandImage(Hand.ROCK)) );
    if (listener != null) {
      guButton.addActionListener(listener);;
    }
    guButton.setOpaque(true);
    add(guButton);
    chButton = new JButton( new ImageIcon(imageManager.getHandImage(Hand.SCISSOR)) );
    if (listener != null) {
      chButton.addActionListener(listener);;
    }
    chButton.setOpaque(true);
    add(chButton);
    paButton = new JButton( new ImageIcon(imageManager.getHandImage(Hand.PAPER)) );
    if (listener != null) {
      paButton.addActionListener(listener);;
    }
    paButton.setOpaque(true);
    add(paButton);
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
