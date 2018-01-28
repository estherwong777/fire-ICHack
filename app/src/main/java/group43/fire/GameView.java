package group43.fire;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import java.util.List;
import java.util.ArrayList;

/**s
 * Created by jasmi on 27/01/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private MainThread thread;
    private DummyArmy dummyArmy;
    private Player player;
    private FireArmy fireArmy;
    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setOnTouchListener(this);
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
        fireArmy.update();
        dummyArmy.removeOutOfBoundDummies();
        fireArmy.removeOutOfBoundDummies();
        if (fireArmy.removeHits(dummyArmy)) {
            player.incrementScore();
        }

        if (dummyArmy.isGameOver()) {
            player.resetScore();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        dummyArmy = new DummyArmy(new Dummy(BitmapFactory.decodeResource(getResources(),R.drawable.redcircle)), getContext());
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.triangle));
        fireArmy = new FireArmy();

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
            canvas.drawText("score: " + player.getScore(), screenWidth / 2, 60, paintScore);

            player.draw(canvas);

            if (!fireArmy.getFireArmy().isEmpty()) {
                fireArmy.draw(canvas);
            }

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //When user touches screen
            fireArmy.addFire(player.getPlayerX(), player.getPlayerY(), BitmapFactory.decodeResource(getResources(),R.drawable.fire));
            return true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            //When user stops touching screen - may not be needed
        }
        return false;
    }

}