package com.mcxiaoke.koi.ext

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import com.mcxiaoke.koi.HASH
import com.mcxiaoke.koi.HEX
import java.io.ByteArrayInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

/**
 * User: mcxiaoke
 * Date: 16/1/27
 * Time: 11:33
 */


fun Context.isAppInstalled(packageName: String): Boolean {
    try {
        return this.packageManager.getPackageInfo(packageName, 0) != null
    } catch (e: PackageManager.NameNotFoundException) {
        return false
    }

}

fun Context.isMainProcess(): Boolean {
    val am: ActivityManager = this.activityManager()
    val processes = am.runningAppProcesses
    val mainProcessName = this.packageName
    val myPid = android.os.Process.myPid()
    return processes.any { p ->
        p.pid == myPid
                && mainProcessName == p.processName
    }
}

fun Context.isComponentDisabled(clazz: Class<*>): Boolean {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    return pm.getComponentEnabledSetting(componentName) ==
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
}

fun Context.isComponentEnabled(clazz: Class<*>): Boolean {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    return pm.getComponentEnabledSetting(componentName) !=
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
}

fun Context.enableComponent(clazz: Class<*>) {
    return setComponentState(clazz, true)
}

fun Context.disableComponent(context: Context, clazz: Class<*>) {
    return setComponentState(clazz, false)
}

fun Context.setComponentState(clazz: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, clazz)
    val pm = this.packageManager
    val oldState = pm.getComponentEnabledSetting(componentName)
    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    if (newState != oldState) {
        val flags = PackageManager.DONT_KILL_APP
        pm.setComponentEnabledSetting(componentName, newState, flags)
    }
}


fun Context.getPackageSignature(): Signature? {
    val pm = this.packageManager
    var info: PackageInfo? = null
    try {
        info = pm.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
    } catch (ignored: Exception) {
    }
    return info?.signatures?.get(0)
}

fun Context.getSignature(context: Context): String {
    val signature = this.getPackageSignature()
    if (signature != null) {
        try {
            return HASH.sha1(signature.toByteArray())
        } catch (e: Exception) {
            return ""
        }
    }
    return ""
}

fun Context.dumpSignature(): String {
    val signature = this.getPackageSignature() ?: return ""
    val builder = StringBuilder()
    try {
        val signatureBytes = signature.toByteArray()
        val certFactory = CertificateFactory.getInstance("X.509")
        val input = ByteArrayInputStream(signatureBytes)
        val cert = certFactory.generateCertificate(input) as X509Certificate
        val chars = signature.toCharsString()
        val hex = HEX.encodeHex(signatureBytes, false)
        val md5 = HASH.md5(signatureBytes)
        val sha1 = HASH.sha1(signatureBytes)
        builder.append("SignName:").append(cert.sigAlgName).append("\n")
        builder.append("Chars:").append(chars).append("\n")
        builder.append("Hex:").append(hex).append("\n")
        builder.append("MD5:").append(md5).append("\n")
        builder.append("SHA1:").append(sha1).append("\n")
        builder.append("SignNumber:").append(cert.serialNumber).append("\n")
        builder.append("SubjectDN:").append(cert.subjectDN.name).append("\n")
        builder.append("IssuerDN:").append(cert.issuerDN.name).append("\n")
    } catch (e: Exception) {
        builder.append("Error:" + e)
    }
    return builder.toString()
}

