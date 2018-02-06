package com.example.vladkliuchak.sampleapp

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import io.fabric.sdk.android.Fabric

/**
 * Created by vladkliuchak on 06.02.18.
 */
class SampleApp : Application() {

    private val TWITTER_KEY: String = getString(R.string.com_twitter_sdk_android_CONSUMER_KEY)
    private val TWITTER_SECRET: String = getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)

    override fun onCreate() {
        super.onCreate()

        val authConfig = TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET)
        Fabric.with(applicationContext, Twitter(authConfig), Crashlytics())

        Stetho.initializeWithDefaults(this)
    }
}