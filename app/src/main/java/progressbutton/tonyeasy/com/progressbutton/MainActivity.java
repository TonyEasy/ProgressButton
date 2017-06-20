package progressbutton.tonyeasy.com.progressbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import progressbutton.tonyeasy.com.mylibrary.ProgressButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressButton          progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressButton = (ProgressButton) findViewById(R.id.button);
        progressButton.animOff();

        progressButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                progressButton.startRotate();
                HandlerUtil
                        .getInstance(MainActivity.this)
                        .postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressButton.animOn();
                            }
                        }, 1000);
                break;
        }
    }
}
