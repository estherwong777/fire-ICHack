package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

/**
 * Created by majesticyak on 27/1/2018.
 */

public class FireArmy {

  private List<Fire> fireArmy;

  public FireArmy() {
    this.fireArmy = null;
  }

  public void addFire(int playerX, int playerY, Bitmap bmp) {
    fireArmy.add(new Fire(playerX, playerY, bmp));
  }

  public void removeFire(int index) {
    fireArmy.remove(index);

  }

  public void update() {
    //moves all of the fires up the screen
    //does not check for collisions, that will probably be done in the mainActivity file
    if (fireArmy != null) {
      for (Fire f : fireArmy) {
        f.update();
      }
    }
  }

  public void draw(Canvas canvas) {
    for(Fire f : fireArmy) {
      f.draw(canvas);
    }
  }

  public boolean didhitDummy(DummyArmy dummyArmy) {
    List<Dummy> army = dummyArmy.getDummyArmy();

    for(Dummy d : army) {
      for(Fire f : fireArmy) {
        if (f.getX() == d.getX() && f.getY() == d.getY()) {
          return true;
        }
      }
    }
    return false;
  }

  public int indexOfHitDummy(DummyArmy dummyArmy) {
    List<Dummy> army = dummyArmy.getDummyArmy();

    for (int i = 0; i < army.size(); i++) {
      for (int j = 0; j < fireArmy.size(); j++) {
        if (army.get(i).getX() == fireArmy.get(j).getX() && army.get(i).getY() == fireArmy.get(j).getY()) {
          return i;
        }
      }
    }
    return -1;

  }

  public int indexOfHitFire(DummyArmy dummyArmy) {
    List<Dummy> army = dummyArmy.getDummyArmy();

    for (int i = 0; i < army.size(); i++) {
      for (int j = 0; j < fireArmy.size(); j++) {
        if (army.get(i).getX() == fireArmy.get(j).getX() && army.get(i).getY() == fireArmy.get(j).getY()) {
          return j;
        }
      }
    }
    return -1;

  }

}
