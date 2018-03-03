package tonimor.vdkans.mainhandlersample;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/*
1.- declare member in app object:
        public 	MainHandler m_mainHandler = null;

2.- In onCreate() app object init MainHandler:
        m_mainHandler = new MainHandler(Looper.getMainLooper());

3.- implements MainHandler.MainHandlerCallbacks in the activity where you want to process messages,
	and attach this activity with MainHandler (in MainActivity, for instance)
		in class declaration:
		public class MainActivity extends AppCompatActivity implements MainHandler.MainHandlerCallbacks
		in onCreate:
        AppObject app = ((AppObject)getApplicationContext());
		app.m_mainHandler.attachActivity(this);

4.- override notifyMessage in MainActivity for process notificacions
    @Override
    public void notifyMessage(int msgId, Object i_obj)
    {
        switch(msgId)
        {
            case MainHandler.MY_CUSTOM_MESSAGE:
                // process message...

                break;
        }
    }

5.- To send MY_CUSTOM_MESSAGE in any place:
		AppObject app = ((AppObject)getApplicationContext());
		Message msg = app.m_mainHandler.obtainMessage(MainHandler.MY_CUSTOM_MESSAGE);
		app.m_mainHandler.sendMessage(msg);
*/


public class MainHandler extends Handler
{
	final public static int MY_CUSTOM_MESSAGE = 9001;
	
	MainHandler(Looper i_looper)
	{
		super(i_looper);
	}
	
	public void attachActivity(Activity i_activity)
	{
		m_callbacks = (MainHandlerCallbacks)i_activity;
	}
	
	@Override
	public void handleMessage(Message i_msg)
    {
		Object obj = i_msg.obj;
        m_callbacks.onNotifyMessage(i_msg.what, obj);
		super.handleMessage(i_msg);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
// interfaces

	MainHandlerCallbacks m_callbacks;

	public interface MainHandlerCallbacks
	{
		public void onNotifyMessage(int msgId, Object i_obj);
	}
}
