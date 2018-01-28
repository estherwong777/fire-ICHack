package group43.fire;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by majesticyak on 27/1/2018.
 */

public class DummyArmy {

  private List<Dummy> army = new ArrayList<>();
  private Context context;
  private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  private int count = 0;
  public int score = 0;
  private int level = 0;
  private int frequency = 250;
  private int probability = 5;


  public List<Dummy> getDummyArmy() {
    return army;
  }


  public DummyArmy(Dummy firstDummy, Context context) {
    this.context = context;
    army.add(firstDummy);
  }

  public void spawnDummy() {
    int random = (int) (Math.random() * 1500);
    Dummy newDummy = new Dummy(BitmapFactory.decodeResource(context.getResources(),R.drawable.redcircle));

    if (random < probability) {
      army.add(newDummy);
    }

    count ++;
    if ((count % frequency) == 0) {
      army.add(newDummy);
    }
  }

  public void removeOutOfBoundDummies() {
    for(Dummy d : army) {
      if (d.getY() < 0 || d.getY() > 2000) {
        army.remove(d);
      }
    }
  }

  public void update() {
    for(Dummy d : army) {
      d.update();
    }
    spawnDummy();

    //if it touches fire then ---
  }

  public void draw(Canvas canvas) {
    for(Dummy d : army) {
      d.draw(canvas);
    }
  }

  public void resetProbability() {
    probability = 5;
  }

  public void resetFrequency() {
    frequency = 250;
  }


  public boolean isGameOver() {
    for(Dummy d : army) {
      if (d.getY() >= screenHeight - 15) {
        return true;
      }
    }
    return false;
  }

  public int getScore() {
    return score;
  }

  public int getLevel() {
    return level;
  }


  public void resetScore() {
    this.score = 0;
  }

  public void resetLevel() {
    this.level = 0;
  }

  public void incrementScore(int i) {
    score += i;
    if (score % 10 == 0) {
      level++;
      frequency += (20 * level);
      probability += (2 * level);
    }
  }

}
