package group43.fire;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Fire  {
  private Position position;
  private Player player;

  public Fire() {
    this.position = new Position(player.getPlayerX(), player.getPlayerY() - 1);
  }

  public Fire(Position position){
    this.position = position;
  }
  public void move() {
    this.position = new Position(getFireX(), getFireY() - 1);

  }

  public int getFireX(){
    return position.getX();
  }
  public int getFireY(){
    return position.getY();

  }
}
