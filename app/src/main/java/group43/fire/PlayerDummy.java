package group43.fire;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by majesticyak on 27/1/2018.
 */

public class PlayerDummy {

    private Bitmap image;
    private int x;
    private int y;

    public PlayerDummy(Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, 100, 100, null);
    }

    public void update() {
        y++;
    }

}
