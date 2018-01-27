package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jasmi on 27/01/2018.
 */

public class Dummy {

    private Bitmap image;

    public Dummy(Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 100, 100, null);
    }
}