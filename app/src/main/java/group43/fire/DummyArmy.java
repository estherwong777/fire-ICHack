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


  public DummyArmy(Dummy firstDummy, Context context) {
    this.context = context;
    army.add(firstDummy);
  }

  public void spawnDummy() {
    int random = (int) (Math.random() * 1000);
    Dummy newDummy = new Dummy(BitmapFactory.decodeResource(context.getResources(),R.drawable.redcircle));

    if (random < 2) {
      army.add(newDummy);
    }
  }

  public void removeOutOfBoundDummies() {
    for(Dummy d : army) {
      if (d.getX() > 2000 || d.getY() > 2000) {
        army.remove(d);
      }
    }
  }

  public void update() {
    for(Dummy d : army) {
      d.update();
    }

    //if it touches fire then ---
  }

  public void draw(Canvas canvas) {
    for(Dummy d : army) {
      d.draw(canvas);
    }
  }

  /*public boolean isHit() {
    for(Dummy d : army) {
      if (d.getY() == )

    }
    return true;
  }*/

  public boolean isGameOver(Canvas canvas) {
    for(Dummy d : army) {
      if (d.getY() == screenHeight) {
        return true;
      }
    }
    return false;
  }

}
