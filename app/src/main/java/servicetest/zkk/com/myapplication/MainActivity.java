package servicetest.zkk.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NetworkBroadcastReceiverHelper mNetworkBroadcastReceiverHelper;
    private Context ctx;
    private  View mViewNoNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=this;
        setContentView(R.layout.activity_main);
        mViewNoNetwork=(View)findViewById(R.id.include_home_fragment_network);
        registerBroadcast();
        Intent intents=new Intent(this, MyNetworkStateService.class);
        startService(intents);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Intent intents=new Intent(this, MyNetworkStateService.class);
        stopService(intents);
    }


    /**
     *注册广播
     */
    private void registerBroadcast() {
        mNetworkBroadcastReceiverHelper = new NetworkBroadcastReceiverHelper(ctx,
                new NetworkBroadcastReceiverHelper.OnNetworkStateChangedListener(){
                    @Override
                    public void onConnected() {
                        //连接时的事件
                        mViewNoNetwork.setVisibility(View.GONE);
                    }
                    @Override
                    public void onDisConnected() {
                        //没有连接时的事件
                        mViewNoNetwork.setVisibility(View.VISIBLE);
                    }
                });
        mNetworkBroadcastReceiverHelper.register();
    }

    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        mNetworkBroadcastReceiverHelper.unregister();
    }
}
