package com.jorzet.truedareshot.views

import com.jorzet.truedareshot.models.Player

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

interface PlayersView: BaseView {

    /**
     *
     */
    fun updatePlayersData(players: List<Player>)

    /**
     *
     */
    fun showPlayerList(showPlayers: Boolean)
}