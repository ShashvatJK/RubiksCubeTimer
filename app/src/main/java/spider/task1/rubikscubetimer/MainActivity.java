package spider.task1.rubikscubetimer;

import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    public Handler h;
    TextView txtview,txt,textView2;
    long starttime,milliseconds,seconds,minutes;
    int click=0;
    ConstraintLayout bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bg=(ConstraintLayout)findViewById(R.id.bg);
        txtview=findViewById(R.id.txtview);
        textView2=findViewById(R.id.textView2);
        txt=findViewById(R.id.txt);
        h=new Handler();
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starttime=SystemClock.uptimeMillis();
                click++;
                if(click%3==1) {
                    h.postDelayed(r, 0);
                }
                if (click % 3 == 2 ) {
                    h.removeCallbacks(r);
                }
                if (click % 3 == 0) {
                    txt.setText("Inspection Timer");
                    txtview.setText("15:00");
                }
            }
        });
    }
    public Runnable r =new Runnable() {
        @Override
        public void run(){
            milliseconds=SystemClock.uptimeMillis()-starttime-15000;
            seconds= milliseconds/1000;
            minutes= seconds/60;
            if(milliseconds>0) {
                txt.setText("Stop-watch");
                txtview.setText(String.format("%02d", minutes) +
                        ":" + String.format("%02d", (seconds%60)) +
                        ":" + String.format("%03d", (milliseconds%1000)));
            }
            else{
                txt.setText("Inspection Timer");
                txtview.setText(String.format("%02d", Math.abs(seconds%60)) +
                         ":" + String.format("%03d", Math.abs(milliseconds%1000)));
            }
            //textView2.setText(milliseconds+"");
            h.postDelayed(this,0);
        }
    };
}

