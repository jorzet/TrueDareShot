package com.jorzet.truedareshot.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.utils.Utils
import kotlinx.android.synthetic.main.custom_player_item.view.*

class PlayersAdapter(context: Context, players: List<Player>) : BaseAdapter() {

    /**
     * Attributes
     */
    private val mContext: Context = context
    lateinit var mOnPlayerClickListener: OnPlayerClickListener

    /**
     * Model
     */
    val mPlayers: List<Player> = players


    interface OnPlayerClickListener {
        fun onPlayerClick(selectedPlayer: Player)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val currentPlayer = getItem(position) as Player

        val playerView = LayoutInflater.from(mContext).inflate(R.layout.custom_player_item, null, false)
        val playerViewHolder = PlayerViewHolder(playerView)

        val nickName = Utils.unicodeToEmoji(currentPlayer.playerName)

        playerViewHolder.mPlayerNickName.append(nickName)

        playerViewHolder.mPlayerNickName.setOnClickListener {
            if (::mOnPlayerClickListener.isInitialized) mOnPlayerClickListener.onPlayerClick(currentPlayer)
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

class PlayerViewHolder (view: View) {
    val mPlayerNickName: EditText = view.findViewById(R.id.ed_player_name)
}