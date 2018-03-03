package tonimor.vdkans.mainhandlersample;

import android.app.Application;
import android.os.Looper;

public class AppObject extends Application {

    public 	MainHandler m_mainHandler = null;

    public AppObject()
    {
    }

    public void onCreate()
    {
        super.onCreate();
        m_mainHandler = new MainHandler(Looper.getMainLooper());
    }

}
