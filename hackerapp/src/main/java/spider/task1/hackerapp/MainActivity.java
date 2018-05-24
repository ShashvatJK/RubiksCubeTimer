package spider.task1.hackerapp;


import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public Handler h;
    TextView txtview,txt,textView2,txtview2,txt2,txtview3;
    long starttime,milliseconds,seconds,minutes;
    int click=0,c=0 ;
    MediaPlayer m;
    ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m= MediaPlayer.create(this,R.raw.beep);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bg=(ConstraintLayout)findViewById(R.id.bg);
        txtview=findViewById(R.id.txtview);
        txtview2=findViewById(R.id.txtview2);
        txtview3=findViewById(R.id.txtview3);
        txt2=findViewById(R.id.txt2);
        txt=findViewById(R.id.txt);
        h=new Handler();
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starttime= SystemClock.uptimeMillis();
                click++;
                if (click % 4 == 1) {
                    h.postDelayed(r, 0);
                }
                if (click % 4 == 2) {
                    txtview2.setText(txtview.getText());
                    txt2.setText("Inspection Timer");
                    txt.setText("Stop-watch");
                    txtview.setText("00:00:000");
                    c=15000;
                }
                if (click % 4 == 3) {
                    h.removeCallbacks(r);
                }
                if (click % 4 == 0) {
                    txt.setText("Inspection Timer");
                    txtview.setText("15:000");
                    txtview2.setText("");
                    txt2.setText("");
                    //txt2.setText("Inspection Timer");
                    //txtview2.setText("00:00");
                    c=0;
                }
            }
        });
    }
    public Runnable r =new Runnable() {
        @Override
        public void run(){
            milliseconds=SystemClock.uptimeMillis()-starttime-15000+c;
            seconds= milliseconds/1000;
            minutes= seconds/60;
            if(milliseconds>0) {
                if(click%4==1){
                    txt2.setText("Inspection Timer");
                    txtview2.setText("00:000");
                    click++;
                }
                txt.setText("Stop-watch");
                txtview.setText(String.format("%02d", minutes) +
                        ":" + String.format("%02d", (seconds%60)) +
                        ":" + String.format("%03d", (milliseconds%1000)));
            }
            else{
                if(seconds==-3){
                    m.start();
                }
                txt.setText("Inspection Timer");
                txtview.setText(String.format("%02d", Math.abs(seconds%60)) +
                        ":" + String.format("%03d", Math.abs(milliseconds%1000)));
            }


            h.postDelayed(this,0);
        }
    };
}
