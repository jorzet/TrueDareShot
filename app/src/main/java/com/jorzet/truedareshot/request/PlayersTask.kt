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
 * @author
 * Created by Jorge Zepeda Tinoco on 16/07/19.
 * jorzet.94@gmail.com
 */

/**
 * Tags
 */
private const val TAG: String = "PlayersTaks"
private const val PLAYERS_REFERENCE: String = "players"

class PlayersTask: AbstractRequestTask<Void, Void, List<Player>>() {

    /*
     * Database object
     */
    private lateinit var mFirebaseDatabase: DatabaseReference


    fun requestGetPlayers() {
        mFirebaseDatabase = FirebaseDatabase
            .getInstance()
            .getReference(PLAYERS_REFERENCE)

        mFirebaseDatabase.keepSynced(true)

        // Attach a listener to read the data at our posts reference
        mFirebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.value
                if (post != null) {
                    try {
                        val map = (post as HashMap<*, *>)
                        Log.d(TAG, "players size: " + map.size)
                        val players = ArrayList<Player>()
                        for (key in map.keys) {
                            val playerMap = map[key] as HashMap<*, *>
                            val player = Gson().fromJson(JSONObject(playerMap).toString(), Player::class.java)
                            player.playerId = key.toString()
                            players.add(player)
                        }

                        if (players.isNotEmpty()) {
                            Log.d(TAG, "playrs success")
                            onRequestListenerSucces.onSuccess(players)
                        } else {
                            Log.d(TAG, "playrs null response")
                            val error = GenericError(ErrorType.NULL_RESPONSE, "")
                            onRequestLietenerFailed.onFailed(error)
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "playrs null response")
                        val error = GenericError(ErrorType.NULL_RESPONSE, "")
                        onRequestLietenerFailed.onFailed(error)
                    }
                } else {
                    Log.d(TAG, "categories null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
                onRequestLietenerFailed.onFailed(databaseError.toException())
            }
        })
    }

}