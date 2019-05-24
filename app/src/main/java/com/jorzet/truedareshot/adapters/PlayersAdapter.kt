package com.jorzet.truedareshot.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Player
import kotlinx.android.synthetic.main.custom_player_item.view.*

class PlayersAdapter(context: Context, players: List<Player>) : BaseAdapter() {

    /**
     * Attributes
     */
    private val mContext: Context = context

    /**
     * Model
     */
    private val mPlayers: List<Player> = players

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentPlayer = getItem(position) as Player

        val playerView = LayoutInflater.from(mContext).inflate(R.layout.custom_player_item, null, false)

        playerView.tv_player_name.text = currentPlayer.playerName

        if (currentPlayer.playerEmoji.isNotEmpty()) {
            val hexString = currentPlayer.playerEmoji.replace("U+", "")
            val emoji = String(Character.toChars(Integer.parseInt(hexString, 16)))
            playerView.ed_player_emoji.text.append(emoji);
        }


        return playerView
    }

    override fun getItem(position: Int): Any {
        return mPlayers[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return mPlayers.size
    }

}