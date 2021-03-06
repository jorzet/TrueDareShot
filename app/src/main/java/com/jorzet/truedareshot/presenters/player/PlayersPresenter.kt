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
import com.jorzet.truedareshot.presenters.BasePresenter
import com.jorzet.truedareshot.views.PlayersView

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

interface PlayersPresenter: BasePresenter<PlayersView> {

    /**
     *
     */
    fun requestAddPlayer(playerNickName: String)

    /**
     *
     */
    fun requestEditPlayer(playerId: String, playerNickName: String)

    /**
     *
     */
    fun getLastPlayerId(players: List<Player>?): Int
}