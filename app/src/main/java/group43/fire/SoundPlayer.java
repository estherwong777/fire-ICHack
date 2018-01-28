package group43.fire;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by majesticyak on 28/1/2018.
 */

public class SoundPlayer {

  private static SoundPool soundPool;
  private static int hitSound; //fire sound
  private static int overSound; // when one gets hit
  private static int gameOver;

  public SoundPlayer(Context context) {
    soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

    hitSound = soundPool.load(context, R.raw.hitsound, 1);
    overSound = soundPool.load(context, R.raw.oversound, 1);
    gameOver = soundPool.load(context, R.raw.gameover, 1);

  }

  public void playHitSound() {
    soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
  }

  public void playOverSound() {
    soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
  }

  public void playGameOverSound() {
    soundPool.play(gameOver, 1.0f, 1.0f, 1, 0, 1.0f);

  }
}
