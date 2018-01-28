package group43.fire;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

/**s
 * Created by jasmi on 27/01/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private MainThread thread;
    private DummyArmy dummyArmy;
    private Player player;
    private FireArmy fireArmy;
    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public int highScore = 0;
    private SoundPlayer soundPlayer;



    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        setOnTouchListener(this);
        startThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void update() {
        dummyArmy.update();
        player.update();
        fireArmy.update();
        dummyArmy.removeOutOfBoundDummies();
        fireArmy.removeOutOfBoundDummies();

        if (fireArmy.removeHits(dummyArmy)) {
            dummyArmy.incrementScore(1);
            soundPlayer.playOverSound();
        }

        if (dummyArmy.isGameOver()) {
            soundPlayer.playGameOverSound();
            if (dummyArmy.getScore() > highScore) {
                highScore = dummyArmy.getScore();
            }

            dummyArmy.resetScore();
            dummyArmy.resetProbability();
            dummyArmy.resetFrequency();
            dummyArmy.resetLevel();

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        dummyArmy = new DummyArmy(new Dummy(BitmapFactory.decodeResource(getResources(),R.drawable.bluecircle)), getContext());
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.triangle));
        fireArmy = new FireArmy();
        soundPlayer = new SoundPlayer(getContext());
    }

    @Override
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
                canvas.drawColor(Color.BLACK);
                Paint paint = new Paint();
                paint.setColor(Color.rgb(250, 0, 0));
                dummyArmy.draw(canvas);
                Paint paintScore = new Paint();
                paintScore.setColor(Color.rgb(255, 255, 255));
                paintScore.setTextSize(90);
                Paint highScorePaint = new Paint();
                highScorePaint.setColor(Color.rgb(150, 150, 150));
                highScorePaint.setTextSize(50);
                canvas.drawText("Score: " + dummyArmy.getScore(), screenWidth - 400, 80, paintScore);
                canvas.drawText("Level " + dummyArmy.getLevel(), 200, 80, paintScore);
                canvas.drawText("Highscore " + highScore, 200, 130, highScorePaint);


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
            soundPlayer.playHitSound();
            return true;
        }
        return false;
    }
    //=======================
    //SOUND PART ============
    //=======================

    private AudioRecord audio        = null;
    private int SAMPLE_RATE          = 8000;
    private int CHANNEL_CONFIG       = AudioFormat.CHANNEL_IN_DEFAULT;
    private int AUDIO_FORMAT         = AudioFormat.ENCODING_PCM_16BIT;
    private int BUFFER_SIZE_BYTES    = AudioRecord.getMinBufferSize(SAMPLE_RATE,
      CHANNEL_CONFIG, AUDIO_FORMAT);
    private short[] audioBuffer;
    private int thresholdAmplitude   = 40;
    private boolean getVolRunning;

    private void startAudioRecording() {
        audio = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL_CONFIG,
          AUDIO_FORMAT, BUFFER_SIZE_BYTES);
        getVolRunning = true;
        audio.startRecording();
    }

    private void stopAudioRecording() {
        audio.stop();
        audio.release();
        audio = null;
        getVolRunning = false;
    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startAudioRecording();
                audioBuffer = new short[BUFFER_SIZE_BYTES];
                while (getVolRunning) {
                    Float amplitude = getAmplitude();
                    if (amplitude > thresholdAmplitude) {
                        fireArmy.addFire(player.getPlayerX(), player.getPlayerY(),
                          BitmapFactory.decodeResource(getResources(), R.drawable.fire));
                    }
                }
                stopAudioRecording();
            }
        }).start();
    }

    public float getAmplitude() {
        final int numBytesRead = Math.abs(audio.read(audioBuffer, 0, BUFFER_SIZE_BYTES));
        if (numBytesRead <= 0) {
            return 0;
        }
        long sumVol = 0;
        for (int i = 0; i < BUFFER_SIZE_BYTES; i++) {
            sumVol += audioBuffer[i];
        }
        return ((float) (Math.abs(sumVol / ((numBytesRead)))));
    }

}
