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

package com.jorzet.truedareshot.request

import android.os.AsyncTask

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 19/05/19.
 * jorzet.94@gmail.com
 */

abstract class AbstractRequestTask<A, B, C> : AsyncTask<A, B, C>() {

    private lateinit var mResponse : String

    protected lateinit var onRequestListenerSucces : OnRequestListenerSuccess<C>
    protected lateinit var onRequestLietenerFailed : OnRequestListenerFailed
    protected lateinit var onDownloadStatusListener: OnDownloadStatusListener

    interface OnRequestListenerSuccess<C> {
        fun onSuccess(result: C)
    }

    interface OnRequestListenerFailed {
        fun onFailed(throwable: Throwable)
    }

    interface OnDownloadStatusListener {
        fun onImageDownloaded()
        fun onImagesError()
    }

    fun setOnRequestSuccess(onRequestSuccess: OnRequestListenerSuccess<C>) {
        this.onRequestListenerSucces = onRequestSuccess
    }

    fun setOnRequestFailed(onRequestFailed: OnRequestListenerFailed) {
        this.onRequestLietenerFailed = onRequestFailed
    }

    fun setOnDownloadStatus(onDownloadStatusListener: OnDownloadStatusListener) {
        this.onDownloadStatusListener = onDownloadStatusListener
    }

    protected open fun getUrl() : String {
        return ""
    }

    override protected fun onPreExecute() {
        super.onPreExecute()
    }

    override protected fun doInBackground(vararg p0: A): C? {
        return null
    }

    override protected fun onPostExecute(result: C) {
        super.onPostExecute(result)
        mResponse = result as String
    }

    protected open fun onGettingResponse(response : C) {

    }

}