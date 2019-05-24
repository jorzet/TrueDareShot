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

package com.jorzet.truedareshot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.adapters.PlayersAdapter
import com.jorzet.truedareshot.components.NonScrollListView
import com.jorzet.truedareshot.models.Player

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class PlayersFragment: BaseFragment() {

    /*
     * Tags
     */
    private val TAG : String = "PlayersFragment"

    /*
     * UI accessors
     */
    private lateinit var mPlayersTextView: TextView
    private lateinit var mNotPlayersTextView: TextView
    private lateinit var mPlayersListView: NonScrollListView
    private lateinit var mAddPlayersButton: Button

    /**
     * Adapters
     */
    private lateinit var mPlayerAdapter: PlayersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.players_fragment, container, false)

        mPlayersTextView = rootView.findViewById(R.id.tv_players)
        mNotPlayersTextView = rootView.findViewById(R.id.tv_not_players)
        mPlayersListView = rootView.findViewById(R.id.nslv_player_list)
        mAddPlayersButton = rootView.findViewById(R.id.btn_add_player)

        mAddPlayersButton.setOnClickListener(mAddPlayersButtonListener)

        val players = arrayListOf<Player>()
        val player1 = Player("p1","Jorge Zepeda", "U+1F613")
        val player2 = Player("p2","Martin Roman", "U+1F631")
        players.add(player1)
        players.add(player2)

        if (players.isNotEmpty()) {
            showPlayerList(true)
            mPlayerAdapter = PlayersAdapter(context!!, players)
            mPlayersListView.adapter = mPlayerAdapter
        } else {
            showPlayerList(false)
        }

        return rootView
    }

    private val mAddPlayersButtonListener = View.OnClickListener {
        showPlayerList(true)
    }

    @Suppress("SENSELESS_COMPARISON")
    private fun showPlayerList(showPlayers: Boolean) {
        if (mNotPlayersTextView != null && mPlayersListView != null) {
            if (showPlayers) {
                mNotPlayersTextView.visibility = View.GONE
                mPlayersListView.visibility = View.VISIBLE
            } else {
                mNotPlayersTextView.visibility = View.VISIBLE
                mPlayersListView.visibility = View.GONE
            }
        }
    }




}