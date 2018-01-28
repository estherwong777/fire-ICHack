package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by majesticyak on 27/1/2018.
 */

public class FireArmy {

  private List<Fire> fireArmy;

  public FireArmy() {
    this.fireArmy = new ArrayList<>();
  }

  public List<Fire> getFireArmy() {
    return fireArmy;
  }

  public void addFire(int playerX, int playerY, Bitmap bmp) {
    fireArmy.add(new Fire(playerX, playerY, bmp));
  }

  public void removeFire(int index) {
    fireArmy.remove(index);

  }

  public void removeOutOfBoundDummies() {
    for(Fire f : fireArmy) {
      if (f.getY() < 0 || f.getY() > 2000) {
        fireArmy.remove(f);
      }
    }
  }

  public void update() {
    //moves all of the fires up the screen
    //does not check for collisions, that will probably be done in the mainActivity file
    if (!fireArmy.isEmpty()) {
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


  public boolean removeHits(DummyArmy dummyArmy) {
    List<Dummy> army = dummyArmy.getDummyArmy();

    for (int i = 0; i < army.size(); i++) {
      for (int j = 0; j < fireArmy.size(); j++) {
        if (isInRange(fireArmy.get(j).getX(), army.get(i).getX() - 100,
          army.get(i).getX() + 100) && isInRange(fireArmy.get(j).getY(),
          army.get(i).getY(), army.get(i).getY() + 50)) {
          fireArmy.remove(j);
          dummyArmy.getDummyArmy().remove(i);
          return true;
        }
        }
      }
      return false;
  }

  private boolean isInRange(int number, int lower, int higher) {
    return (number <= higher && number >= lower);
  }

}
