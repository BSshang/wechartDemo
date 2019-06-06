package com.chogge.speaker.activity;

import android.app.Service;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chogge.speaker.R;
import com.chogge.speaker.utils.SendThread;
import com.chogge.speaker.view.Knob;
import com.chogge.speaker.view.VolumeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.freq_1)
    Button freq01;
    @BindView(R.id.freq_2)
    Button freq02;
    @BindView(R.id.freq_3)
    Button freq03;
    @BindView(R.id.freq_4)
    Button freq04;
    @BindView(R.id.freq_5)
    Button freq05;
    @BindView(R.id.freq_6)
    Button freq06;
    @BindView(R.id.freq_7)
    Button freq07;
    @BindView(R.id.freq_8)
    Button freq08;
    @BindView(R.id.freq_9)
    Button freq09;
    @BindView(R.id.freq_10)
    Button freq10;
    @BindView(R.id.freq_11)
    Button freq11;
    @BindView(R.id.freq_12)
    Button freq12;
    @BindView(R.id.freq_13)
    Button freq13;
    @BindView(R.id.freq_14)
    Button freq14;
    @BindView(R.id.freq_15)
    Button freq15;
    @BindView(R.id.freq_16)
    Button freq16;
    @BindView(R.id.knob_left)
    Knob knobLeft;
    @BindView(R.id.knob_right)
    Knob knobRight;
    int lastValue = 0;
    float level = 0.9F;
    SoundPool mSoundPool;
    int preState = 0;
    int soundID_1;
    private StringBuffer receiveData = new StringBuffer();
    private String mIp = "";
    private int mPort = 0;
    private int put_info = 0;
    private SendThread sendthread;
    //创建震动服务对象
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);
        clinet();

        //获取手机震动服务
        mVibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);

        mSoundPool = new SoundPool(1, 3, 0);
        soundID_1 = mSoundPool.load(getApplicationContext(), R.raw.wavefile, 1);

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

            }
        });


        knobLeft.setNumberOfStates(9);
        knobLeft.setState(4);
        knobLeft.setOnStateChanged(new Knob.OnStateChanged() {
            public void onState(int state) {
                if (preState != state) {
                    preState = state;
                    putInfo(state+"");
                    mSoundPool.play(soundID_1, level, level, 10, 1, 1.0f);
                }
            }
        });

        knobRight.setNumberOfStates(9);
        knobRight.setState(4);
        knobRight.setOnStateChanged(new Knob.OnStateChanged() {
            public void onState(int state) {
                if (preState != state) {
                    preState = state;
                    putInfo(state+"");
                    mSoundPool.play(soundID_1, level, level, 10, 1, 1.0f);
                }
            }
        });

        freq01.setOnTouchListener(this);
        freq02.setOnTouchListener(this);
        freq03.setOnTouchListener(this);
        freq04.setOnTouchListener(this);
        freq05.setOnTouchListener(this);
        freq06.setOnTouchListener(this);
        freq07.setOnTouchListener(this);
        freq08.setOnTouchListener(this);
        freq09.setOnTouchListener(this);
        freq10.setOnTouchListener(this);
        freq11.setOnTouchListener(this);
        freq12.setOnTouchListener(this);
        freq13.setOnTouchListener(this);
        freq14.setOnTouchListener(this);
        freq15.setOnTouchListener(this);
        freq16.setOnTouchListener(this);


    }

    @OnClick({R.id.freq_1, R.id.freq_2, R.id.freq_3, R.id.freq_4,R.id.freq_5,R.id.freq_6,R.id.freq_7,R.id.freq_8,R.id.freq_9,R.id.freq_10,R.id.freq_11,R.id.freq_12,R.id.freq_13,R.id.freq_14,R.id.freq_15,R.id.freq_16})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.freq_1:
                putInfo("0000");
                break;
            case R.id.freq_2:
                putInfo("0001");
                break;
            case R.id.freq_3:
                putInfo("0010");
                break;
            case R.id.freq_4:
                putInfo("0011");
                break;
            case R.id.freq_5:
                putInfo("0100");
                break;
            case R.id.freq_6:
                putInfo("0101");
                break;
            case R.id.freq_7:
                putInfo("0110");
                break;
            case R.id.freq_8:
                putInfo("0111");
                break;
            case R.id.freq_9:
                putInfo("1000");
                break;
            case R.id.freq_10:
                putInfo("1001");
                break;
            case R.id.freq_11:
                putInfo("1010");
                break;
            case R.id.freq_12:
                putInfo("1011");
                break;
            case R.id.freq_13:
                putInfo("1100");
                break;
            case R.id.freq_14:
                putInfo("1101");
                break;
            case R.id.freq_15:
                putInfo("1110");
                break;
            case R.id.freq_16:
                putInfo("1111");
                break;

        }
    }

    /**
     *
     * wifi连接
     *
     */
    public void clinet(){

        mIp =  "192.168.1.1";
        mPort = 6060;

        sendthread = new SendThread(mIp, mPort, mHandler);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendthread.run();
                    if(sendthread.isConnected()){
                        mHandler.sendEmptyMessage(0x04);
                    }
                }
            }).start();
    }

    /**
     *
     * 发送数据
     *
     */

    public  void putInfo(final String num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                  sendthread.send(num);
            }
        }).start();

    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch(msg.what){
                case 0x00:
                    Log.i("mr_收到的数据： ", msg.obj.toString());
                    receiveData.append("接收到：" + msg.obj.toString());
                    receiveData.append("\r\n");
                    break;
                case 0x01:
                    break;
                case 0x02:
                    receiveData.append("连接中断：" );
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x03:
                    receiveData.append("连接建立：" );
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x04:
                    receiveData.append("连接成功");
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x05:
                    receiveData.append("连接失败");
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x06:
                    receiveData.append("连接开始");
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x07:
                    receiveData.append("创建连接失败");
                    Toast.makeText(SendActivity.this, receiveData, Toast.LENGTH_SHORT).show();
                    receiveData.append("\r\n");
                    break;
                case 0x08:
                    receiveData.append(msg.obj);
                    receiveData.append("\r\n");
                    break;
            }
        }
    };

    private void VIBRATE(){
        //   mVibrator.vibrate(new long[]{100,100},-1);
        mSoundPool.play(soundID_1, level, level, 10, 1, 1.0f);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int count = 0;
        switch (v.getId()) {
            case R.id.freq_1 /*2131558502*/:
                count = 1;
                break;
            case R.id.freq_2 /*2131558503*/:
                count = 2;
                break;
            case R.id.freq_3 /*2131558504*/:
                count = 3;
                break;
            case R.id.freq_4 /*2131558505*/:
                count = 4;
                break;
            case R.id.freq_5 /*2131558506*/:
                count = 5;
                break;
            case R.id.freq_6 /*2131558507*/:
                count = 6;
                break;
            case R.id.freq_7 /*2131558508*/:
                count = 7;
                break;
            case R.id.freq_8 /*2131558509*/:
                count = 8;
                break;
            case R.id.freq_9 /*2131558510*/:
                count = 9;
                break;
            case R.id.freq_10/*2131558510*/:
                count = 10;
                break;
            case R.id.freq_11 /*2131558510*/:
                count = 11;
                break;
            case R.id.freq_12 /*2131558510*/:
                count = 12;
                break;
            case R.id.freq_13 /*2131558510*/:
                count = 13;
                break;
            case R.id.freq_14 /*2131558510*/:
                count = 14;
                break;
            case R.id.freq_15 /*2131558510*/:
                count = 15;
                break;
            case R.id.freq_16/*2131558510*/:
                count = 16;
                break;
        }
        final int frequency = getSharedPreferences("bstar", 0).getInt("frequency", 30);
        final int temp = count;
        new Thread() {
            public void run() {
                super.run();
                for (int i = 0; i < temp; i++) {
                    SendActivity.this.mSoundPool.play(SendActivity.this.soundID_1, SendActivity.this.level, SendActivity.this.level, 10, 1, 1.0f);
                    try {
                        Thread.sleep((long) (1000 / frequency));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return false;
    }



    @Override
    protected void onDestroy()
    {
        sendthread.close();
        super.onDestroy();
    }
}
