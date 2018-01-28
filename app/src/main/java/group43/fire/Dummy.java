package group43.fire;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
/**
 * Created by jasmi on 27/01/2018.
 */

public class Dummy {
  //dummies are the bad guys, the player aims to fire at them

    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private Bitmap image;
    private int x;
    private int y;
    private int yVelocity = 6;

    public Dummy(Bitmap bmp) {
        this.image = bmp;
        this.x = (int) (Math.random() * (screenWidth - 2 * image.getWidth()) + image.getWidth());
        this.y = 1;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        y += yVelocity;
    }

    public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

}