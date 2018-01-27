package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Fire  {
  private int x;
  private int y;
  private Player player;
  private Bitmap bmp;

  public Fire(int playerX, int playerY, Bitmap bmp) {
    this.bmp = bmp;
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

  public void draw(Canvas canvas) {
    canvas.drawBitmap(bmp, x, y, null);
  }

}
