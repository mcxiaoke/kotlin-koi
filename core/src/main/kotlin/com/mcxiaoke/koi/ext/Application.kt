package com.mcxiaoke.koi.ext

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.app.Service
import android.view.View
import android.support.v4.app.Fragment as SupportFragment

/**
 * User: mcxiaoke
 * Date: 16/1/31
 * Time: 10:30
 */

fun Activity.getApp(): Application = application

fun Service.getApp(): Application = application

fun View.getApp(): Application = context.applicationContext as Application

fun Fragment.getApp(): Application = activity.application

fun SupportFragment.getApp(): Application = activity.application


