package com.jorzet.truedareshot.views

import com.jorzet.truedareshot.models.Player

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