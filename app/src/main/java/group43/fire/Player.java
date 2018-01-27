package group43.fire;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Player {

//<<<<<<< HEAD
  private Position posit;

  public Player(){
    this.posit = new Position();
  }

  public Player (int start){
    this.posit = new Position(start, 0);
  }

  public void moveRight(){
    this.posit = new Position(posit.getX()+1,0);
  }

  public void moveLeft(){
    this.posit = new Position(posit.getX()-1,0);
  }

  public Position getPlayerPosition(){
    return posit;
  }

  public int getPlayerX(){
    return posit.getX();
  }

  public int getPlayerY(){
    return posit.getY();
  }

}



