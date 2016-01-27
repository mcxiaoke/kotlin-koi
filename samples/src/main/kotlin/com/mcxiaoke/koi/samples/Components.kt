package com.mcxiaoke.koi.samples

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.mcxiaoke.next.ui.dialog.v4.ProgressDialogFragment

/**
 * User: mcxiaoke
 * Date: 16/1/20
 * Time: 17:39
 */


abstract class BaseActivity : AppCompatActivity() {

    val app: MainApp
        get() = application as MainApp

    val activity: BaseActivity
        get() = this
}

abstract class BaseFragment : Fragment() {
    protected var mProgressDialog: ProgressDialogFragment? = null

    protected val app: MainApp
        get() = activity.application as MainApp

    protected fun finish() {
        activity ?: activity.finish()
    }

    protected fun showProgressDialog(title: CharSequence, message: CharSequence) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialogFragment.create(title, message)
            mProgressDialog?.show(fragmentManager)
        }
    }

    protected fun dismissProgressDialog() {
        mProgressDialog?.dismissAllowingStateLoss()
        mProgressDialog = null
    }

    override fun onDestroy() {
        dismissProgressDialog()
        super.onDestroy()
    }
}
