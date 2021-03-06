package tk.iovr.gamejoystick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

import tk.iovr.gamejoystick.controller.DefaultController;
import tk.iovr.gamejoystick.listener.JoystickTouchViewListener;
import tk.iovr.gamejoystick.model.PadStyle;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button switchMode = findViewById(R.id.buttonSwitch);
        TextView controlInfo = findViewById(R.id.controlInfo);

        //隐藏标题栏
        Objects.requireNonNull(getSupportActionBar()).hide();

        //隐藏系统状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //添加 控制 遥感
        DefaultController mDefaultController =
                new DefaultController(
                        getApplicationContext(),
                        findViewById(R.id.frame)
                );

        mDefaultController.createViews();
        mDefaultController.showViews(false);

        //左遥感
        mDefaultController.setLeftTouchViewListener(new JoystickTouchViewListener() {
             @Override
            public void onTouch(float horizontalPercent, float verticalPercent) {
                Log.d(TAG, "onTouch left: " + horizontalPercent + ", " + verticalPercent);
                controlInfo.setText("onTouch left: H:" + horizontalPercent + ", V:" + verticalPercent);
            }

            @Override
            public void onReset() {
                Log.d(TAG, "onReset: left");
                controlInfo.setText("onReset: left");

            }

            @Override
            public void onActionDown() {
                Log.d(TAG, "onActionDown: left");
                controlInfo.setText("onActionDown: left");
            }

            @Override
            public void onActionUp() {
                Log.d(TAG, "onActionUp: left");
                controlInfo.setText("onActionUp: left");

            }

        });
        //右遥感
        mDefaultController.setRightTouchViewListener(new JoystickTouchViewListener() {
            @Override
            public void onTouch(float horizontalPercent, float verticalPercent) {
                Log.d(TAG, "onTouch right: " + horizontalPercent + ", " + verticalPercent);
                controlInfo.setText("onTouch right: H:" + horizontalPercent + ", V:" + verticalPercent);

            }

            @Override
            public void onReset() {
                Log.d(TAG, "onReset: right");
                controlInfo.setText("onReset: right");

            }

            @Override
            public void onActionDown() {
                Log.d(TAG, "onActionDown: right");
                controlInfo.setText("onActionDown: right");

            }

            @Override
            public void onActionUp() {
                Log.d(TAG, "onActionUp: right");
                controlInfo.setText("onActionUp: right");

            }
        });


        //切换遥感模式
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDefaultController.getPadStyle()== PadStyle.FIXED){
                    mDefaultController.setPadStyle(PadStyle.FLOATING);
                    switchMode.setText("CurrentMode: "+ mDefaultController.getPadStyle());


                }else if (mDefaultController.getPadStyle()== PadStyle.FLOATING){
                    mDefaultController.setPadStyle(PadStyle.FIXED);
                    switchMode.setText("CurrentMode: "+ mDefaultController.getPadStyle());


                }
            }
        });

        switchMode.setText("CurrentMode: "+ mDefaultController.getPadStyle());

    }
}