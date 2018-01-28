package group43.fire;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Player {

  //<<<<<<< HEAD
  private int x;
  private int y;
  private Bitmap playerImage;
  private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  private int xVelocity;


  public Player(Bitmap playerImage){
    this.x = screenWidth / 2;
    this.y = screenHeight - playerImage.getHeight() - 200;
    this.playerImage = playerImage;
    this.xVelocity = 12;
  }

  public void update() {
    if (x < screenWidth) {
      x += xVelocity;
      if (x > screenWidth - playerImage.getWidth() || (x < 0)) {
        xVelocity *= -1;
      }
    }
  }




  public int getPlayerX(){
    return x;
  }

  public int getPlayerY(){
    return y;
  }

  public void draw(Canvas canvas) {
    canvas.drawBitmap(playerImage, getPlayerX(), getPlayerY(), null);
  }

}