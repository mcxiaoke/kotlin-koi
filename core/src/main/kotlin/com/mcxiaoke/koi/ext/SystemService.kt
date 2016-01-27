package com.mcxiaoke.koi.ext

/**
 * User: mcxiaoke
 * Date: 16/1/26
 * Time: 17:35
 */

import android.accounts.AccountManager
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


fun Context.accessibilityManager(): AccessibilityManager? =
        systemService(Context.ACCESSIBILITY_SERVICE)

fun Context.accountManager(): AccountManager? =
        systemService(Context.ACCOUNT_SERVICE)

fun Context.activityManager(): ActivityManager =
        systemService(Context.ACTIVITY_SERVICE)

fun Context.alarmManager(): AlarmManager =
        systemService(Context.ALARM_SERVICE)

fun Context.appWidgetManager(): AppWidgetManager? =
        systemService(Context.APPWIDGET_SERVICE)

fun Context.appOpsManager(): AppOpsManager? =
        systemService(Context.APP_OPS_SERVICE)

fun Context.audioManager(): AudioManager =
        systemService(Context.AUDIO_SERVICE)

fun Context.batteryManager(): BatteryManager? =
        systemService(Context.BATTERY_SERVICE)

fun Context.bluetoothAdapter(): BluetoothAdapter? =
        systemService(Context.BLUETOOTH_SERVICE)

fun Context.cameraManager(): CameraManager? =
        systemService(Context.CAMERA_SERVICE)

fun Context.captioningManager(): CaptioningManager? =
        systemService(Context.CAPTIONING_SERVICE)

fun Context.clipboardManager(): ClipboardManager =
        systemService(Context.CLIPBOARD_SERVICE)

fun Context.connectivityManager(): ConnectivityManager =
        systemService(Context.CONNECTIVITY_SERVICE)

fun Context.consumerIrManager(): ConsumerIrManager? =
        systemService(Context.CONSUMER_IR_SERVICE)

fun Context.devicePolicyManager(): DevicePolicyManager? =
        systemService(Context.DEVICE_POLICY_SERVICE)

fun Context.displayManager(): DisplayManager? =
        systemService(Context.DISPLAY_SERVICE)

fun Context.downloadManager(): DownloadManager? =
        systemService(Context.DOWNLOAD_SERVICE)

fun Context.dropBoxManager(): DropBoxManager? =
        systemService(Context.DROPBOX_SERVICE)

fun Context.inputMethodManager(): InputMethodManager? =
        systemService(Context.INPUT_METHOD_SERVICE)

fun Context.inputManager(): InputManager? =
        systemService(Context.INPUT_SERVICE)

fun Context.jobScheduler(): JobScheduler? =
        systemService(Context.JOB_SCHEDULER_SERVICE)

fun Context.keyguardManager(): KeyguardManager =
        systemService(Context.KEYGUARD_SERVICE)

fun Context.launcherApps(): LauncherApps? =
        systemService(Context.LAUNCHER_APPS_SERVICE)

fun Context.layoutInflater(): LayoutInflater =
        systemService(Context.LAYOUT_INFLATER_SERVICE)

fun Context.locationManager(): LocationManager =
        systemService(Context.LOCATION_SERVICE)

fun Context.mediaProjectionManager(): MediaProjectionManager? =
        systemService(Context.MEDIA_PROJECTION_SERVICE)

fun Context.mediaRouter(): MediaRouter? =
        systemService(Context.MEDIA_ROUTER_SERVICE)

fun Context.mediaSessionManager(): MediaSessionManager? =
        systemService(Context.MEDIA_SESSION_SERVICE)

fun Context.nfcManager(): NfcManager? =
        systemService(Context.NFC_SERVICE)

fun Context.notificationManager(): NotificationManager =
        systemService(Context.NOTIFICATION_SERVICE)

fun Context.nsdManager(): NsdManager? =
        systemService(Context.NSD_SERVICE)

fun Context.powerManager(): PowerManager =
        systemService(Context.POWER_SERVICE)

fun Context.printManager(): PrintManager? =
        systemService(Context.PRINT_SERVICE)

fun Context.restrictionsManager(): RestrictionsManager? =
        systemService(Context.RESTRICTIONS_SERVICE)

fun Context.searchManager(): SearchManager =
        systemService(Context.SEARCH_SERVICE)

fun Context.sensorManager(): SensorManager =
        systemService(Context.SENSOR_SERVICE)

fun Context.storageManager(): StorageManager? =
        systemService(Context.STORAGE_SERVICE)

fun Context.telephonyManager(): TelephonyManager =
        systemService(Context.TELEPHONY_SERVICE)

fun Context.textServicesManager(): TextServicesManager? =
        systemService(Context.TEXT_SERVICES_MANAGER_SERVICE)

fun Context.tvInputManager(): TvInputManager? =
        systemService(Context.TV_INPUT_SERVICE)

fun Context.uiModeManager(): UiModeManager? =
        systemService(Context.UI_MODE_SERVICE)

fun Context.usbManager(): UsbManager? =
        systemService(Context.USB_SERVICE)

fun Context.userManager(): UserManager? =
        systemService(Context.USER_SERVICE)

fun Context.vibrator(): Vibrator =
        systemService(Context.VIBRATOR_SERVICE)

fun Context.wallpaperService(): WallpaperService =
        systemService(Context.WALLPAPER_SERVICE)

fun Context.wifiP2pManager(): WifiP2pManager? =
        systemService(Context.WIFI_P2P_SERVICE)

fun Context.wifiManager(): WifiManager =
        systemService(Context.WIFI_SERVICE)

fun Context.windowService(): WindowManager =
        systemService(Context.WINDOW_SERVICE)

/*
 * -----------------------------------------------------------------------------
 *  Private functions
 * -----------------------------------------------------------------------------
 */
private fun <T> Context.systemService(serviceName: String): T =
        this.getSystemService(serviceName) as T
