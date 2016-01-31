package com.mcxiaoke.koi.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.mcxiaoke.koi.async.AsyncKt;
import com.mcxiaoke.koi.async.WeakContext;
import com.mcxiaoke.koi.log.LogKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/**
 * User: mcxiaoke
 * Date: 16/1/29
 * Time: 21:41
 */
public class JavaActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        testAsync();
    }

    public void testAsync() {
        final Context context = this;
        AsyncKt.asyncSafe(this, new Function1<WeakContext<Activity>, String>() {
            @Override
            public String invoke(final WeakContext<Activity> contextWeakContext) {
                LogKt.logd(context, "asyncSafe(action1) action running");
                return "asyncSafe(action1)";
            }
        });
        AsyncKt.asyncSafe(context, new Function1<WeakContext<Context>, String>() {
            @Override
            public String invoke(final WeakContext<Context> weakContext) {
                LogKt.logd(context, "asyncSafe(action2) action running");
                return "asyncSafe(action2)";
            }
        });
        AsyncKt.asyncSafe(this, new Function1<WeakContext<Context>, String>() {
                    @Override
                    public String invoke(final WeakContext<Context> weakContext) {
                        LogKt.logd(context, "asyncSafe(action,callback) action running");
                        if (System.currentTimeMillis() % 2 == 0) {
                            throw new RuntimeException("asyncSafe(action,callback) random exception for test");
                        }
                        return "asyncSafe(action,callback)";
                    }
                },
                new Function2<String, Throwable, Unit>() {
                    @Override
                    public Unit invoke(final String s, final Throwable e) {
                        LogKt.logd(context, "asyncSafe(action,callback) result:" + s + " error:" + e);
                        LogKt.logd(context, "asyncSafe(action,callback) callback");
                        return Unit.INSTANCE;
                    }
                });
        AsyncKt.asyncSafe(this, new Function1<WeakContext<Context>, String>() {
                    @Override
                    public String invoke(final WeakContext<Context> weakContext) {
                        LogKt.logd(context, "asyncSafe(action,success,failure) action running");
                        if (System.currentTimeMillis() % 2 == 0) {
                            throw new RuntimeException("asyncSafe(action,success,failure) random exception for test");
                        }
                        return "asyncSafe(action,success,failure)";
                    }
                },
                new Function1<String, Unit>() {
                    @Override
                    public Unit invoke(final String s) {
                        LogKt.logd(context, "asyncSafe(action,callback) result:" + s);
                        LogKt.logd(context, "asyncSafe(action,success,failure) success");
                        return Unit.INSTANCE;
                    }
                },
                new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(final Throwable throwable) {
                        LogKt.logd(context, "asyncSafe(action,callback) error:" + throwable);
                        LogKt.logd(context, "asyncSafe(action,success,failure) failure");
                        return Unit.INSTANCE;
                    }
                });
    }

}
