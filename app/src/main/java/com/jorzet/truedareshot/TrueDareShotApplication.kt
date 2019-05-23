/*
 * Copyright [2019] [Jorge Zepeda Tinoco]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jorzet.truedareshot

import android.support.multidex.MultiDexApplication
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.jorzet.truedareshot.services.sharedpreferences.SharedPreferencesManager

class TrueDareShotApplication: MultiDexApplication() {

    private val TAG: String = "TrueDareShotApplication"

    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        SharedPreferencesManager(this).setFirstQuestionShown(false)

        val fontRequest = android.support.v4.provider.FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs)
        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
            .setReplaceAll(true)
            .registerInitCallback(object : EmojiCompat.InitCallback() {
                override fun onInitialized() {
                    super.onInitialized()
                    Log.i(TAG, "Emojicompact Initialize")
                }

                override fun onFailed(throwable: Throwable?) {
                    super.onFailed(throwable)
                    Log.e(TAG, "Emojicompact Initialize failed " + throwable!!)
                }
            })

        EmojiCompat.init(config)
    }

}