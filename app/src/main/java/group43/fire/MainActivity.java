package group43.fire;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final int RECORD_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.d("permission", "denied");
            makeRequest();
        }
        setContentView(new GameView(this));
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{
          Manifest.permission.RECORD_AUDIO}, RECORD_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {

                if (grantResults.length == 0
                  || grantResults[0] !=
                  PackageManager.PERMISSION_GRANTED) {

                    Log.i("permission", "Permission has been denied by user");
                } else {
                    Log.i("permission", "Permission has been granted by user");
                }
                return;
            }
        }
    }

}
