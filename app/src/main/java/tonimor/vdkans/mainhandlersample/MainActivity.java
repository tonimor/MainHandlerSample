package tonimor.vdkans.mainhandlersample;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainHandler.MainHandlerCallbacks
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppObject app = ((AppObject)getApplicationContext());
        app.m_mainHandler.attachActivity(this);

        Button myButtonUp = (Button)findViewById(R.id.my_button);
        myButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppObject app = ((AppObject)getApplicationContext());
                Message msg = app.m_mainHandler.obtainMessage(MainHandler.MY_CUSTOM_MESSAGE);
                app.m_mainHandler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onNotifyMessage(int msgId, Object i_obj)
    {
        final Context context = this;

        switch(msgId)
        {
            case MainHandler.MY_CUSTOM_MESSAGE:
                this.runOnUiThread(new Runnable() {
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("MainHandlerSample").setMessage("Se ha recibido el mensaje MY_CUSTOM_MESSAGE en MainActivity.onNotifyMessage").show();
                    }
                });
                break;
        }
    }
}
