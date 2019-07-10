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
    val mPlayers: List<Player> = players

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentPlayer = getItem(position) as Player

        val playerView = LayoutInflater.from(mContext).inflate(R.layout.custom_player_item, null, false)

        val nickName = unicodeToEmoji(currentPlayer.playerName)

        playerView.tv_player_name.text = nickName

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

    private fun unicodeToEmoji(playerNickName: String): String {


        val playerNameSplited = playerNickName.split("U+").toMutableList()
        var playerName = playerNameSplited[0]

        if (playerNameSplited.size > 1) {
            for (i in 1 .. playerNameSplited.size - 1) {
                playerNameSplited[i] = "U+" + playerNameSplited[i]
                val withoutSpaces = playerNameSplited[i].replace(" ", "")
                if (withoutSpaces.length == 7) {
                    val utf = playerNameSplited[i].substring(0, 7)

                    val hexString = utf.replace("U+", "").replace(" ", "")
                    val emoji = String(Character.toChars(Integer.parseInt(hexString, 16)))

                    playerNameSplited[i].replace(utf, emoji)

                }

                playerName += playerNameSplited[i]
            }
        }

        return playerName
    }

}