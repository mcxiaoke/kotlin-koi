package com.mcxiaoke.koi.ext

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:35
 */

import android.accounts.AccountManager
import android.annotation.TargetApi
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothAdapter
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.service.wallpaper.WallpaperService
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager


fun Context.getAccessibilityManager(): AccessibilityManager =
        getSystemServiceAs(Context.ACCESSIBILITY_SERVICE)

fun Context.getAccountManager(): AccountManager =
        getSystemServiceAs(Context.ACCOUNT_SERVICE)

fun Context.getActivityManager(): ActivityManager =
        getSystemServiceAs(Context.ACTIVITY_SERVICE)

fun Context.getAlarmManager(): AlarmManager =
        getSystemServiceAs(Context.ALARM_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getAppWidgetManager(): AppWidgetManager =
        getSystemServiceAs(Context.APPWIDGET_SERVICE)

@TargetApi(Build.VERSION_CODES.KITKAT)
fun Context.getAppOpsManager(): AppOpsManager =
        getSystemServiceAs(Context.APP_OPS_SERVICE)

fun Context.getAudioManager(): AudioManager =
        getSystemServiceAs(Context.AUDIO_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getBatteryManager(): BatteryManager =
        getSystemServiceAs(Context.BATTERY_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Context.getBluetoothAdapter(): BluetoothAdapter =
        getSystemServiceAs(Context.BLUETOOTH_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getCameraManager(): CameraManager =
        getSystemServiceAs(Context.CAMERA_SERVICE)

@TargetApi(Build.VERSION_CODES.KITKAT)
fun Context.getCaptioningManager(): CaptioningManager =
        getSystemServiceAs(Context.CAPTIONING_SERVICE)

fun Context.getClipboardManager(): ClipboardManager =
        getSystemServiceAs(Context.CLIPBOARD_SERVICE)

fun Context.getConnectivityManager(): ConnectivityManager =
        getSystemServiceAs(Context.CONNECTIVITY_SERVICE)

@TargetApi(Build.VERSION_CODES.KITKAT)
fun Context.getConsumerIrManager(): ConsumerIrManager =
        getSystemServiceAs(Context.CONSUMER_IR_SERVICE)

fun Context.getDevicePolicyManager(): DevicePolicyManager =
        getSystemServiceAs(Context.DEVICE_POLICY_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.getDisplayManager(): DisplayManager =
        getSystemServiceAs(Context.DISPLAY_SERVICE)

fun Context.getDownloadManager(): DownloadManager =
        getSystemServiceAs(Context.DOWNLOAD_SERVICE)

fun Context.getDropBoxManager(): DropBoxManager =
        getSystemServiceAs(Context.DROPBOX_SERVICE)

fun Context.getInputMethodManager(): InputMethodManager =
        getSystemServiceAs(Context.INPUT_METHOD_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
fun Context.getInputManager(): InputManager =
        getSystemServiceAs(Context.INPUT_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getJobScheduler(): JobScheduler =
        getSystemServiceAs(Context.JOB_SCHEDULER_SERVICE)

fun Context.getKeyguardManager(): KeyguardManager =
        getSystemServiceAs(Context.KEYGUARD_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getLauncherApps(): LauncherApps =
        getSystemServiceAs(Context.LAUNCHER_APPS_SERVICE)

fun Context.getLayoutService(): LayoutInflater =
        getSystemServiceAs(Context.LAYOUT_INFLATER_SERVICE)

fun Context.getLocationManager(): LocationManager =
        getSystemServiceAs(Context.LOCATION_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getMediaProjectionManager(): MediaProjectionManager =
        getSystemServiceAs(Context.MEDIA_PROJECTION_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
fun Context.getMediaRouter(): MediaRouter =
        getSystemServiceAs(Context.MEDIA_ROUTER_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getMediaSessionManager(): MediaSessionManager =
        getSystemServiceAs(Context.MEDIA_SESSION_SERVICE)

fun Context.getNfcManager(): NfcManager =
        getSystemServiceAs(Context.NFC_SERVICE)

fun Context.getNotificationManager(): NotificationManager =
        getSystemServiceAs(Context.NOTIFICATION_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
fun Context.getNsdManager(): NsdManager =
        getSystemServiceAs(Context.NSD_SERVICE)

fun Context.getPowerManager(): PowerManager =
        getSystemServiceAs(Context.POWER_SERVICE)

@TargetApi(Build.VERSION_CODES.KITKAT)
fun Context.getPrintManager(): PrintManager =
        getSystemServiceAs(Context.PRINT_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getRestrictionsManager(): RestrictionsManager =
        getSystemServiceAs(Context.RESTRICTIONS_SERVICE)

fun Context.getSearchManager(): SearchManager =
        getSystemServiceAs(Context.SEARCH_SERVICE)

fun Context.getSensorManager(): SensorManager =
        getSystemServiceAs(Context.SENSOR_SERVICE)

fun Context.getStorageManager(): StorageManager =
        getSystemServiceAs(Context.STORAGE_SERVICE)

fun Context.getTelephonyManager(): TelephonyManager =
        getSystemServiceAs(Context.TELEPHONY_SERVICE)

fun Context.getTextServicesManager(): TextServicesManager =
        getSystemServiceAs(Context.TEXT_SERVICES_MANAGER_SERVICE)

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getTvInputManager(): TvInputManager =
        getSystemServiceAs(Context.TV_INPUT_SERVICE)

fun Context.getUiModeManager(): UiModeManager =
        getSystemServiceAs(Context.UI_MODE_SERVICE)

fun Context.getUsbManager(): UsbManager =
        getSystemServiceAs(Context.USB_SERVICE)

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.getUserManager(): UserManager =
        getSystemServiceAs(Context.USER_SERVICE)

fun Context.getVibrator(): Vibrator =
        getSystemServiceAs(Context.VIBRATOR_SERVICE)

fun Context.getWallpaperService(): WallpaperService =
        getSystemServiceAs(Context.WALLPAPER_SERVICE)

fun Context.getWifiP2pManager(): WifiP2pManager =
        getSystemServiceAs(Context.WIFI_P2P_SERVICE)

fun Context.getWifiManager(): WifiManager =
        getSystemServiceAs(Context.WIFI_SERVICE)

fun Context.getWindowService(): WindowManager =
        getSystemServiceAs(Context.WINDOW_SERVICE)

@Suppress("UNCHECKED_CAST")
fun <T> Context.getSystemServiceAs(serviceName: String): T =
        this.getSystemService(serviceName) as T
