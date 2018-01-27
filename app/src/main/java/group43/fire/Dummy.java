package group43.fire;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
/**
 * Created by jasmi on 27/01/2018.
 */

public class Dummy {

    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Bitmap image;
    private int x;
    private int y;

    public Dummy(Bitmap bmp) {
        this.image = bmp;
        this.x = (int) (Math.random() * screenWidth);
        this.y = 1;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        y = y + 3;
    }

    public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

}