// $Id$

package me.kukkii.janken.log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import me.kukkii.janken.Player;
import me.kukkii.janken.Result;

public class UserScore implements Serializable {

  private Map<Player, int[]> scoreMap;

  public UserScore() {
    scoreMap = new HashMap<Player, int[]>();
  }

  public int[] getScore(Player player) {
    return scoreMap.get(player);
  }

  public void setScore(Player player, int[] scores) {
    scoreMap.put(player, scores);
  }

  public void addScore(Player player, Result result) {
    int[] scores = getScore(player);
    if (scores == null) {
      scores = new int[3];
      setScore(player, scores);
    }
    addScore(scores, result);

    scores = getScore(null);
    if (scores == null) {
      scores = new int[3];
      setScore(null, scores);
    }
    addScore(scores, result);
  }

  private void addScore(int[] scores, Result result) {
    switch (result) {
    case WIN:
      scores[0] += 1;
      break;
    case LOSE:
      scores[1] += 1;
      break;
    case DRAW:
    default:
      scores[2] += 1;
      break;
    }
  }

}
