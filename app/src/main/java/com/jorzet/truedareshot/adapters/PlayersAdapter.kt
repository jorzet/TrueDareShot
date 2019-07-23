/*
 * Copyright [2018] [Jorge Zepeda Tinoco]
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

package com.jorzet.truedareshot.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.utils.Utils

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 24/05/19.
 */

class PlayersAdapter(context: Context, players: List<Player>?) : BaseAdapter() {

    /**
     * Attributes
     */
    private val mContext: Context = context
    lateinit var mOnPlayerClickListener: OnPlayerClickListener

    /**
     * Model
     */
    val mPlayers: List<Player>? = players


    interface OnPlayerClickListener {
        fun onPlayerClick(selectedPlayer: Player)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentPlayer = getItem(position)

        val playerView = LayoutInflater.from(mContext).inflate(R.layout.custom_player_item, null, false)
        if (currentPlayer != null) {
            val playerViewHolder = PlayerViewHolder(playerView)

            val nickName = Utils.unicodeToEmoji(currentPlayer.playerName)

            playerViewHolder.mPlayerNickName.append(nickName)

            playerViewHolder.mPlayerNickName.setOnClickListener {
                if (::mOnPlayerClickListener.isInitialized) mOnPlayerClickListener.onPlayerClick(currentPlayer)
            }
        }
        return playerView
    }

    override fun getItem(position: Int): Player? {
        if (mPlayers != null)
            return mPlayers[position]
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        if (mPlayers != null)
            return mPlayers.size
        return 0
    }
}

class PlayerViewHolder (view: View) {
    val mPlayerNickName: EditText = view.findViewById(R.id.ed_player_name)
}