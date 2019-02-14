package instruction.waterworld.com.instruction;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity implements ViewSwitcher.ViewFactory{

    private static final String TAG = "Instruction";
    private ListView mListView;
    private InstructionAdapter  mAdapter;
    private List<Bean> mDatas;

    private ImageSwitcher mImageSwitcher;
    private int  index =0;
    private static final int[] imagelist = {R.mipmap.banner, R.mipmap.banner1, R.mipmap.banner2};
    private  Handler handler;
    private  Timer timer;
    private  TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initImageSwitcher();
        initListView();
        initTimer( );
    }

    private void initImageSwitcher( ){
        mImageSwitcher = findViewById( R.id.image_switcher );
        mImageSwitcher.setFactory(this);
        mImageSwitcher.setImageResource(imagelist[index]);
        mImageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            float downX = 0,upX =0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: begin "+event.getAction());
                switch (event.getAction() ){
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        Log.d(TAG, "onTouch: down:"+downX);

                        break;
                    case MotionEvent.ACTION_UP:
                        upX = event.getX();
                        Log.d(TAG, "onTouch: up x:"+upX +" down x:"+downX);
                        if( (upX - downX)> 20){
                            index --;
                            if( index == -1){
                                index =2;
                            }
                            mImageSwitcher.setImageResource( imagelist[index]);
                        }else if( (downX-upX) > 20){
                            index ++;
                            if( index == 3 ){
                                index = 0;
                            }
                            mImageSwitcher.setImageResource( imagelist[index]);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: move");
                        break;
                    default:
                        break;
                }
                Log.d(TAG, "onTouch: end");
                return true;
            }
        });
    }
    //方法；初始化Data
     private void initListView() {
         mListView = findViewById( R.id.list_instruction );

         //为数据绑定适配器
         mAdapter = new InstructionAdapter(this);
         mListView.setAdapter(mAdapter);
         mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Log.d(TAG, "onItemClick: "+ position+" : " +id);
                    Intent  intent = new Intent();
                    intent.setAction( Utils.INSTRUCTION_ACTION );
                    intent.putExtra(Utils.INSTRUCTION_ITEMID, position);
                    intent.putExtra(Utils.INSTRUCTION_ITEMNAME, mAdapter.getItemTitle(position));
                    startActivity( intent );

             }
         });
    }

    public View makeView() {
        // 将所有图片通过ImageView来显示
        return new ImageView(this);
    }

    public void initTimer( ){
        Log.d(TAG, "initTimer: ");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    //do something
                    index++;
                    if( index >2 ){
                        index = 0;
                    }
                    mImageSwitcher.setImageResource( imagelist[index]);
                    Log.d(TAG, "handleMessage: ");
                }
                super.handleMessage(msg); }
        };

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(timerTask,1000,5000);//延时1s，每隔500毫秒执行一次run方法
    }
}
