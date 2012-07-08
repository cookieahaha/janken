// $Id$

package me.kukkii.janken.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import me.kukkii.janken.Hand;
import me.kukkii.janken.Result;
import me.kukkii.janken.log.UserScore;
import me.kukkii.janken.net.JankenClient;

public class JankenGamePanel extends JPanel implements ActionListener {

  private JLabel yourNameLabel;
  private JLabel botNameLabel;
  private JankenPanel yourJankenPanel;
  private JankenPanel botJankenPanel;

  private JankenClient client;
  private int mode;
  private String yourName;
  private String botName;
  private UserScore score;

  public JankenGamePanel() {
    Color defaultColor = ColorManager.getManager().getDefaultColor();
    setBackground(defaultColor);
    setOpaque(true);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    JPanel panel = new JPanel();
    panel.setBackground(defaultColor);
    panel.setOpaque(true);
    add(panel);
    yourNameLabel = new JLabel();
    Font font = yourNameLabel.getFont();
    font = new Font(font.getFamily(), Font.BOLD, 20);
    yourNameLabel.setFont(font);
    // yourNameLabel.setBackground(defaultColor);
    yourNameLabel.setBackground(new Color(222, 222, 222));
    yourNameLabel.setOpaque(true);
    panel.add(yourNameLabel);

    yourJankenPanel = new JankenPanel(this);
    yourJankenPanel.clearColors();
    add(yourJankenPanel);

    panel = new JPanel();
    panel.setBackground(defaultColor);
    panel.setOpaque(true);
    add(panel);
    botNameLabel = new JLabel();
    botNameLabel.setFont(font);
    // botNameLabel.setBackground(defaultColor);
    botNameLabel.setBackground(new Color(222, 222, 222));
    botNameLabel.setOpaque(true);
    panel.add(botNameLabel);

    botJankenPanel = new JankenPanel(null);
    botJankenPanel.clearColors();
    add(botJankenPanel);

    try {
      client = new JankenClient();
      yourName = client.getUser().getName();
      yourNameLabel.setText(yourName);

    } catch (IOException e) { }

    score = new UserScore();
    updateScore();
    prepareGame();
  }

  public void prepareGame() {
    try {
      botName = client.receiveBotName();
      // botNameLabel.setText(botName);
      updateBotScore();
    } catch (IOException e) { }

    yourJankenPanel.clearColors();
    botJankenPanel.clearColors();

    mode = 0;
  }

  private void updateScore() {
    updateScoreLabel(yourNameLabel, null);
  }

  private void updateBotScore() {
    updateScoreLabel(botNameLabel, botName);
  }

  private void updateScoreLabel(JLabel label, String name) {
    int[] scores = score.getScore(name);
    if (name == null) {
      name = yourName;
    }
    float rate = (float)(scores[0]);
    if (rate > 0.0f) {
      rate /= (scores[0] + scores[1]);
    }
    String r = String.format("%.3f", rate);
    label.setText(name + "   " + scores[0] + " - " + scores[1] + " - " + scores[2] + "  " + r);
    label.setHorizontalAlignment(SwingConstants.TRAILING);
  }

  public void actionPerformed(ActionEvent ae) {
    if (mode == 1) {
      prepareGame();
      return;
    }

    JButton source = (JButton) ae.getSource();
    Hand yourHand = yourJankenPanel.getHand(source);

    Hand botHand = null;
    Result result = null;
    try {
      client.sendHand(yourHand);
      botHand = client.receiveBotHand();
      result = client.receiveResult();
    } catch (IOException e) { }

    System.err.println("you=" + yourHand + " bot=" + botHand + " result=" + result);

    score.addScore(botName, result);
    updateScore();
    updateBotScore();

    yourJankenPanel.setResultColor(source, result);
    botJankenPanel.setResultColor(botHand, getBotResult(result));

    mode = 1;
  }

  private Result getBotResult(Result result) {
    switch (result) {
    case WIN:
      return Result.LOSE;
    case LOSE:
      return Result.WIN;
    case DRAW:
      return Result.DRAW;
    default:
      return Result.WIN;
    }
  }

  public static void main(String[] args) throws Exception {
    try {
      // UIManager.setLookAndFeel(new MetalLookAndFeel());
      UIManager.setLookAndFeel(new SynthLookAndFeel());
    } catch (UnsupportedLookAndFeelException e) { }
    JFrame frame = new JFrame("janken");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JankenGamePanel());
    frame.pack();
    frame.setVisible(true);
  }
}
