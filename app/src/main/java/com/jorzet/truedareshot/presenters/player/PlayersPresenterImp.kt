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

package com.jorzet.truedareshot.presenters.player

import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.services.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.services.sharedpreferences.SharedPreferencesManager
import com.jorzet.truedareshot.views.PlayersView
import java.util.*

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 16/07/19.
 * jorzet.94@gmail.com
 */

class PlayersPresenterImp: PlayersPresenter {

    /**
     * View
     */
    private lateinit var mPlayersView: PlayersView

    /**
     * Manager
     */
    private var mSharedPreferencesManager: SharedPreferencesManager? = null
    private var mRequestManager : FirebaseRequestManager? = null

    /**
     * Model
     */
    private lateinit var mPlayers: List<Player>

    override fun create(view: PlayersView) {
        mPlayersView = view

        // init manager
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(view.getBaseContext())
        mRequestManager = FirebaseRequestManager.getInstance(view.getBaseContext())

        // init
        initializeView()
    }

    private fun initializeView() {
        requestGetPlayers()
    }

    override fun destroy() {
        mSharedPreferencesManager?.destroy()
        mRequestManager?.destroy()
    }

    override fun requestGetPlayers() {
        mRequestManager?.requestGetPlayers(object: FirebaseRequestManager.OnGetPlayersListener {
            override fun onGetPlayersLoaded(players: List<Player>) {

                if (players.isNotEmpty() && ::mPlayersView.isInitialized) {
                    mPlayers = players
                    mPlayersView.showPlayerList(true)
                    mPlayersView.updatePlayersData(players)

                } else {
                    mPlayersView.showPlayerList(false)
                }
            }

            override fun onGetPlayersError(throwable: Throwable) {
                if (::mPlayersView.isInitialized)
                    mPlayersView.showPlayerList(false)
            }
        })
    }

    override fun requestAddPlayer(playerNickName: String) {
        val id = getLastPlayerId(mPlayers) + 1
        val newPlayer = Player("p$id", playerNickName)
        val players = mPlayers as ArrayList
        players.add(newPlayer)

        mPlayersView.updatePlayersData(players)
    }

    override fun requestEditPlayer(playerId: String, playerNickName: String) {
        if (playerId.isNotEmpty() && ::mPlayers.isInitialized) {
            for (player in mPlayers) {
                if (player.playerId == playerId) {
                    player.playerName = playerNickName

                    mPlayersView.updatePlayersData(mPlayers)
                    return
                }
            }
        }
    }

    override fun getLastPlayerId(players: List<Player>): Int {
        val ids = arrayListOf<Int>()

        for (player in players)
            ids.add(Integer.parseInt(player.playerId.replace("p","")))

        return Collections.max(ids)
    }

}