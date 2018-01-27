package group43.fire;

import java.util.List;

/**
 * Created by majesticyak on 27/1/2018.
 */

public class FireArmy {

  private List<Fire> fireArmy;

  public FireArmy() {
    this.fireArmy = null;
  }

  public void addFire(int playerX, int playerY) {
    fireArmy.add(new Fire(playerX, playerY));
  }

  public void removeFire(int index) {
    fireArmy.remove(index);

  }

  public void update() {
    //moves all of the fires up the screen
    //does not check for collisions, that will probably be done in the mainActivity file
    for (Fire f : fireArmy) {
      f.update();
    }
  }

}
