package com.mcxiaoke.koi.samples;

import android.app.Activity;
import android.content.Context;
import com.mcxiaoke.koi.core.AsyncKt;
import com.mcxiaoke.koi.core.WeakContext;
import com.mcxiaoke.koi.log.LogKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/**
 * User: mcxiaoke
 * Date: 16/1/29
 * Time: 21:41
 */
public class JavaActivity extends Activity {

    public void test() {
        final Context context = this;
        AsyncKt.async2(new Function0<String>() {
                           @Override
                           public String invoke() {
                               return Thread.currentThread().getName();
                           }
                       },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(final String s) {
                        LogKt.logd(context, "action thread: " + s);
                        LogKt.logd(context, "callback thread: " + Thread.currentThread().getName());
                        return Unit.INSTANCE;
                    }
                });
        AsyncKt.asyncSafe(this, new Function1<WeakContext<Activity>, String>() {
            @Override
            public String invoke(final WeakContext<Activity> weakContext) {
                return "JavaActivity";
            }
        });
        AsyncKt.asyncSafe(context, new Function1<WeakContext<Context>, String>() {
            @Override
            public String invoke(final WeakContext<Context> weakContext) {
                return Thread.currentThread().getName();
            }
        });
    }
}
