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

package com.jorzet.truedareshot.utils

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 11/07/19
 */

class Utils {

    companion object {
        /**
         * Constants
         */
        private const val EMOJI_LENGTH: Int = 7 // example U+23451
        private const val EMOJI_IDENTIFIER: String = "U+"

        /**
         * This method gets all emojis in unicode and replace each one in original [String]
         * @param playerNickName The player nick name with unicode emojis in [String]
         * @return Player nick name with emojis in [String]
         */
        fun unicodeToEmoji(playerNickName: String): String {
            val playerNameSplitted = playerNickName.split(EMOJI_IDENTIFIER).toMutableList()
            val playerName: StringBuilder = StringBuilder(playerNameSplitted[0])

            if (playerNameSplitted.size > 1) {
                for (i in 1 until playerNameSplitted.size) {

                    playerNameSplitted[i] = EMOJI_IDENTIFIER + playerNameSplitted[i]
                    val aux: StringBuilder = StringBuilder(playerNameSplitted[i])
                    val withoutSpaces = playerNameSplitted[i].replace(" ", "")

                    if (withoutSpaces.length == EMOJI_LENGTH) {
                        val utf = playerNameSplitted[i].substring(0, EMOJI_LENGTH)

                        val hexString = utf.replace(EMOJI_IDENTIFIER, "").replace(" ", "")
                        val emoji = String(Character.toChars(Integer.parseInt(hexString, 16)))

                        aux.replace(0, EMOJI_LENGTH, emoji)
                        playerNameSplitted[i] = aux.toString()
                    }

                    playerName.append(aux)
                }
            }

            return playerName.toString()
        }
    }
}