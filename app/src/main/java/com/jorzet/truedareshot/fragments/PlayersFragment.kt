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

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.adapters.PlayersAdapter
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.enums.DialogType
import com.jorzet.truedareshot.presenters.player.PlayersPresenter
import com.jorzet.truedareshot.presenters.player.PlayersPresenterImp
import com.jorzet.truedareshot.ui.dialogs.AddEditPlayerDialog
import com.jorzet.truedareshot.ui.dialogs.BaseDialog
import com.jorzet.truedareshot.views.PlayersView

import kotlinx.android.synthetic.main.players_fragment.*

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18.
 */

/**
 * Constants
 */
private const val TAG : String = "PlayersFragment"
private const val ADD_EDIT_DIALOG_TAG: String = "add_edit_dialog"
private const val REQUEST_ADD_PLAYER: Int = 0x22
private const val REQUEST_EDIT_PLAYER: Int = 0x23

class PlayersFragment: BaseFragment(), PlayersView, BaseDialog.OnDialogListener {

    /**
     * Adapters
     */
    private lateinit var mPlayerAdapter: PlayersAdapter

    /**
     * Presenter
     */
    private lateinit var mPlayersPresenter: PlayersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPlayersPresenter = PlayersPresenterImp()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.players_fragment, container, false)

        mAddPlayersButton.setOnClickListener(mAddPlayersButtonListener)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (::mPlayersPresenter.isInitialized)
            mPlayersPresenter.create(this)
    }

    private val mAddPlayersButtonListener = View.OnClickListener {
        AddEditPlayerDialog.newInstance(REQUEST_ADD_PLAYER, null, null,
            DialogType.ACCEPT_CANCEL_DIALOG, this)
            .show(fragmentManager!!, ADD_EDIT_DIALOG_TAG)
    }

    private val mPlayersItemClickListener = object: PlayersAdapter.OnPlayerClickListener{
        override fun onPlayerClick(selectedPlayer: Player) {
            AddEditPlayerDialog.newInstance(
                REQUEST_EDIT_PLAYER, selectedPlayer.playerName, selectedPlayer.playerId,
                DialogType.ACCEPT_CANCEL_DIALOG, this@PlayersFragment)
                .show(fragmentManager!!, ADD_EDIT_DIALOG_TAG)
        }
    }

    override fun getBaseContext(): Activity {
        return this.activity!!
    }

    override fun updatePlayersData(players: List<Player>?) {
        mPlayerAdapter = PlayersAdapter(context!!, players)
        mPlayerAdapter.mOnPlayerClickListener = mPlayersItemClickListener
        mPlayersListView.adapter = mPlayerAdapter
    }

    override fun showPlayerList(showPlayers: Boolean) {
        if (mNotPlayersTextView != null && mPlayersListView != null) {
            Log.d(TAG, "show player list $showPlayers")
            if (showPlayers) {
                mNotPlayersTextView.visibility = View.GONE
                mPlayersListView.visibility = View.VISIBLE
            } else {
                mNotPlayersTextView.visibility = View.VISIBLE
                mPlayersListView.visibility = View.GONE
            }
        }
    }

    override fun onConfirmationCancel() {
        context?.hideKeyboard(this.view)
    }

    override fun onConfirmationNeutral(arguments: Bundle) {
        context?.hideKeyboard(this.view)
    }

    override fun onConfirmationAccept(arguments: Bundle) {
        context?.hideKeyboard(this.view)

        when(arguments.getInt(BaseDialog.REQUEST_CODE)) {
            REQUEST_EDIT_PLAYER -> {
                val playerId = arguments.getString(BaseDialog.PLAYER_ID, "")
                val playerNickName = arguments.getString(BaseDialog.NICK_NAME, "")

                if (::mPlayersPresenter.isInitialized && playerNickName.isNotEmpty() && playerId.isNotEmpty())
                    mPlayersPresenter.requestEditPlayer(playerId, playerNickName)
            }
            REQUEST_ADD_PLAYER -> {
                val playerNickName = arguments.getString(BaseDialog.NICK_NAME, "")

                if (::mPlayersPresenter.isInitialized && playerNickName.isNotEmpty())
                    mPlayersPresenter.requestAddPlayer(playerNickName)
            }
        }
    }
}