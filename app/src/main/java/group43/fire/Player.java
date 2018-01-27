package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Player {

  //<<<<<<< HEAD
  private Position posit;
  private Bitmap playerImage;

  public Player(Bitmap playerImage){
    this.posit       = new Position();
    this.playerImage = playerImage;
  }

  public Player (int start){
    this.posit       = new Position(start, 0);
    this.playerImage = playerImage;
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

  public void draw(Canvas canvas) {
    canvas.drawBitmap(playerImage, getPlayerX(), getPlayerY(), null);
  }

}