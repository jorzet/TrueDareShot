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

import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import org.json.JSONObject

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

/**
 * Constants
 */
private const val TAG: String = "PlayersTaks"
private const val PLAYERS_REFERENCE: String = "players"

class PlayersTask: AbstractRequestTask<Void, List<Player>>() {

    override fun getReference(): String? {
        return PLAYERS_REFERENCE
    }

    override fun keepSync(): Boolean {
        return true
    }

    override fun onGettingResponse(successResponse: DataSnapshot) {
        val post = successResponse.value
        if (post != null) {
            try {
                val map = (post as HashMap<*, *>)
                Log.d(TAG, "players size: " + map.size)
                val players = ArrayList<Player>()
                map.keys.forEach {
                    val playerMap = map[it] as HashMap<*, *>
                    val player = Gson().fromJson(JSONObject(playerMap).toString(), Player::class.java)
                    player.playerId = it.toString()
                    players.add(player)
                }

                if (players.isNotEmpty()) {
                    Log.d(TAG, "players success")
                    onRequestListenerSucces.onSuccess(players)
                } else {
                    Log.d(TAG, "players null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            } catch (e: Exception) {
                Log.d(TAG, "players null response")
                val error = GenericError(ErrorType.NULL_RESPONSE, "")
                onRequestLietenerFailed.onFailed(error)
            }
        } else {
            Log.d(TAG, "players null response")
            val error = GenericError(ErrorType.NULL_RESPONSE, "")
            onRequestLietenerFailed.onFailed(error)
        }
    }

    override fun onGettingError(errorResponse: DatabaseError) {
        println("The read failed: " + errorResponse.code)
        onRequestLietenerFailed.onFailed(errorResponse.toException())
    }

}