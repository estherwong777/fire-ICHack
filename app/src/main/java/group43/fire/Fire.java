package group43.fire;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Fire  {
  private int x;
  private int y;
  private Player player;

  public Fire(int playerX, int playerY) {
    this.x = playerX;
    this.y = playerY + 10;
  }

  public int getX() {
    return x;
  }

  public int getY(){
    return y;
  }

  public void update() {
    y--;
  }

}
