package com.mcxiaoke.koi.samples

import android.app.Activity
import android.app.Fragment
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mcxiaoke.koi.KoiConfig
import com.mcxiaoke.koi.ext.*
import com.mcxiaoke.koi.log.*
import java.io.File

/**
 * Author: mcxiaoke
 * Date:  2016/2/2 20:38
 */

class ActivityExtensionSample : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val act = getActivity() // Activity
        act.restart() // restart Activity
        val app = act.getApp() // Application
        val app2 = act.application  // Application
        val textView = act.find<TextView>(android.R.id.text1)

    }

    fun toastSample() {
        // available in Activity/Fragment/Service/Context
        toast(R.string.app_name)
        toast("this is a toast")
        longToast(R.string.app_name)
        longToast("this is a long toast")
    }
}

class FragmentExtensionSample : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val act = activity // Activity
        finish() // call Activity.finish()


    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = activity // Activity
        val app = getApp() // Application
        val textView = find<TextView>(android.R.id.text1) // view.findViewById
        val imageView = find<TextView>(android.R.id.icon1) // view.findViewById

    }
}

class ContextExtensionSample : Activity() {

    fun inflateLayout() {
        val view1 = inflate(R.layout.activity_main)
        val viewGroup = view1 as ViewGroup
        val view2 = inflate(android.R.layout.activity_list_item, viewGroup, false)
    }

    fun usefulFunctions() {
        val hasCamera = hasCamera()

        mediaScan(Uri.parse("file:///sdcard/Pictures/koi/cat.png"))
        addToMediaStore(File("/sdcard/Pictures/koi/cat.png"))

        val batteryStatusIntent = getBatteryStatus()
        val colorValue = getResourceValue(android.R.color.darker_gray)
    }

    fun intentSample() {
        val extras = Bundle { putString("key", "value") }
        val intent1 = newIntent<MainActivity>()
        val intent2 = newIntent<MainActivity>(Intent.FLAG_ACTIVITY_NEW_TASK, extras)
    }

    fun startActivitySample() {
        startActivity<MainActivity>()
        // equal to
        startActivity(Intent(this, MainActivity::class.java))

        startActivity<MainActivity>(Intent.FLAG_ACTIVITY_SINGLE_TOP, Bundle())
        startActivity<MainActivity>(Bundle())

        startActivityForResult<MainActivity>(100)
        startActivityForResult<MainActivity>(Bundle(), 100)
        startActivityForResult<MainActivity>(200, Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    fun startServiceSample() {
        startService<BackgroundService>()
        startService<BackgroundService>(Bundle())
    }

    fun networkSample() {
        val name = networkTypeName()
        val operator = networkOperator()
        val type = networkType()
        val wifi = isWifi()
        val mobile = isMobile()
        val connected = isConnected()
    }

    fun notificationSample() {
        val notification = newNotification() {
            this.setColor(0x0099cc)
                    .setAutoCancel(true)
                    .setContentTitle("Notification Title")
                    .setContentText("Notification Message Text")
                    .setDefaults(0)
                    .setGroup("koi")
                    .setVibrate(longArrayOf(1, 0, 0, 1))
                    .setSubText("this is a sub title")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setLargeIcon(null)
        }
    }

    fun packageSample() {
        val isYoutubeInstalled = isAppInstalled("com.google.android.youtube")
        val isMainProcess = isMainProcess()
        val disabled = isComponentDisabled(MainActivity::class.java)
        enableComponent(MainActivity::class.java)

        val sig = getPackageSignature()
        val sigString = getSignature()
        println(dumpSignature())
    }

    fun systemServices() {
        val wm = getWindowService()
        val tm = getTelephonyManager()
        val nm = getNotificationManager()
        val cm = getConnectivityManager()
        val am = getAccountManager()
        val acm = getActivityManager()
        val alm = getAlarmManager()
        val imm = getInputMethodManager()
        val inflater = getLayoutService()
        val lm = getLocationManager()
        val wifi = getWifiManager()
    }

    fun logSample() {
        KoiConfig.logEnabled = true //default is false
        // true == Log.VERBOSE
        // false == Log.ASSERT
        // optional
        KoiConfig.logLevel = Log.VERBOSE // default is Log.ASSERT
        //

        logv("log functions available in Context")  //Log.v
        logd("log functions available in Context")  //Log.d
        logi("log functions available in Context")  //Log.i
        logw("log functions available in Context")  //Log.w
        loge("log functions available in Context")   //Log.e
        logf("log functions available in Context")  //Log.wtf

        logv { "lazy eval message lambda" }  //Log.v
        logd { "lazy eval message lambda" }  //Log.d
        logi { "lazy eval message lambda" }  //Log.i
        logw { "lazy eval message lambda" }  //Log.w
        loge { "lazy eval message lambda" }   //Log.e
        logf { "lazy eval message lambda" }  //Log.wtf
    }
}



