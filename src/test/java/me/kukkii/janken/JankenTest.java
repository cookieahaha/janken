// $Id$
package me.kukkii.janken;

import me.kukkii.janken.bot.*;

// import junit.framework.Assert;
import junit.framework.TestCase;

public class JankenTest extends TestCase {

  public void testResult() {
    Judge judge = new Judge();

    Result result = judge.judge(Hand.ROCK, Hand.ROCK);
    // Assert.assertEquals(result, Result.DRAW);
    assertEquals(result, Result.DRAW);

    result = judge.judge(Hand.ROCK, Hand.PAPER);
    assertEquals(result, Result.LOSE);

    result = judge.judge(Hand.ROCK, Hand.SCISSOR);
    assertEquals(result, Result.WIN);
  }

  public void testBot() {
    Player bot = new Rock100Bot();
    Hand hand = bot.hand2();
    assertEquals(hand, Hand.ROCK);

    bot = new Paper100Bot();
    hand = bot.hand2();
    assertEquals(hand, Hand.PAPER);

    bot = new Scissor100Bot();
    hand = bot.hand2();
    assertEquals(hand, Hand.SCISSOR);
  }
}
