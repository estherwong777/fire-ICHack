package group43.fire;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by jasmi on 27/01/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private DummyArmy dummyArmy;
    private Player player;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void update() {
        dummyArmy.update();
        dummyArmy.spawnDummy();
        player.update();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        dummyArmy = new DummyArmy(new Dummy(BitmapFactory.decodeResource(getResources(),R.drawable.redcircle)), getContext());
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.triangle));

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            dummyArmy.draw(canvas);
            Paint paintScore = new Paint();
            paintScore.setColor(Color.rgb(0,0,0));
            paintScore.setTextSize(100);
            canvas.drawText("score:", 450, 60, paintScore);

            player.draw(canvas);
        }
    }

}