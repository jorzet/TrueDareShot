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

package com.jorzet.truedareshot.services.firebase

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.jorzet.truedareshot.models.Image
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import com.jorzet.truedareshot.request.AbstractRequestTask
import com.jorzet.truedareshot.services.firebase.images.DownloadImageTask
import java.lang.Exception

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 19/05/19.
 * jorzet.94@gmail.com
 */

class DownloadImages: Service() {

    /*
     * Tags
     */
    companion object {
        const val TAG : String = "DownloadImages"
        const val DOWNLOAD_IMAGES_BR = "com.jorzet.truedareshot.services.firebase.DownloadImages"
        const val DOWNLOAD_COMPLETE = "download_complete"
        const val DOWNLOAD_PROGRESS = "download_progress"
        const val DOWNLOAD_ERROR = "download_error"
        const val ERROR_WHEN_DOWNLOAD = "error_when_download"
    }

    /*
     * Broadcast intent
     */
    var bi = Intent(DOWNLOAD_IMAGES_BR)

    /*
     * images list path
     */
    private lateinit var mImages : List<Image>

    /*
     * Variable
     */
    private var downloadProgress = 0
    private var downloadCount = 0
    private var downloadWithError = false

    override fun onCreate() {
        super.onCreate()

        // get images from data base
        try {
            Log.d(TAG, "call DownloadImages service")
            // instance download image task and set listeners
            val downloadImageTask = DownloadImageTask(this)

            downloadImageTask.setOnRequestSuccess(object : AbstractRequestTask.OnRequestListenerSuccess<String> {
                override fun onSuccess(result: String) {
                    //bi.putExtra(DOWNLOAD_COMPLETE, true)
                    //sendBroadcast(bi)
                }
            })

            downloadImageTask.setOnRequestFailed(object : AbstractRequestTask.OnRequestListenerFailed {
                override fun onFailed(throwable: Throwable) {

                    bi.putExtra(DOWNLOAD_ERROR, true)

                    if (throwable is GenericError &&
                        throwable.errorType.equals(ErrorType.CANNOT_DOWNLOAD_CONTENT_FILE_NOT_FOUND)) {
                        bi.putExtra(ERROR_WHEN_DOWNLOAD, ErrorType.CANNOT_DOWNLOAD_CONTENT_FILE_NOT_FOUND)
                    }

                    sendBroadcast(bi)
                }
            })

            downloadImageTask.setOnDownloadStatus(object: AbstractRequestTask.OnDownloadStatusListener {
                override fun onImageDownloaded() {
                    downloadCount++
                    downloadProgress = (downloadCount * 100) / mImages.size
                    bi.putExtra(DOWNLOAD_PROGRESS, downloadProgress)
                    sendBroadcast(bi)
                    if (downloadCount == mImages.size - 1) {
                        bi.putExtra(DOWNLOAD_COMPLETE, true)
                        sendBroadcast(bi)
                    }
                }
                override fun onImagesError() {
                    downloadCount++
                    downloadProgress = (downloadCount * 100) / mImages.size

                    downloadWithError = true

                    bi.putExtra(DOWNLOAD_PROGRESS, downloadProgress)
                    sendBroadcast(bi)

                    if (downloadCount == mImages.size - 1) {
                        bi.putExtra(DOWNLOAD_COMPLETE, false)
                        sendBroadcast(bi)
                    }
                }
            })

            Log.d(TAG, "start download task")
            downloadImageTask.execute(mImages)

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}