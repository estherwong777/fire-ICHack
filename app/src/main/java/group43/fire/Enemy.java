package group43.fire;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Enemy {

  private Position position;

  Enemy() {
    this.position = new Position();

  }

  Enemy(Position startPosition) {
    this.position = startPosition;
  }

  public Position getEnemyPosition() {
    return position;
  }

  public int getEnemyX() {
    return position.getX();
  }

  public int getEnemyY() {
    return position.getY();
  }

  public void moveDown() {
    this.position = new Position(getEnemyX(), getEnemyY() + 1);
  }

}
