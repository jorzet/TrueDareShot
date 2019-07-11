package com.jorzet.truedareshot.utils

class Utils {
    companion object {

        fun unicodeToEmoji(playerNickName: String): String {


            val playerNameSplited = playerNickName.split("U+").toMutableList()
            val playerName: StringBuilder = StringBuilder(playerNameSplited[0])

            if (playerNameSplited.size > 1) {
                for (i in 1 .. playerNameSplited.size - 1) {

                    playerNameSplited[i] = "U+" + playerNameSplited[i]
                    val aux: StringBuilder = StringBuilder(playerNameSplited[i])
                    val withoutSpaces = playerNameSplited[i].replace(" ", "")

                    if (withoutSpaces.length == 7) {
                        val utf = playerNameSplited[i].substring(0, 7)

                        val hexString = utf.replace("U+", "").replace(" ", "")
                        val emoji = String(Character.toChars(Integer.parseInt(hexString, 16)))

                        aux.replace(0, 7, emoji)
                        playerNameSplited[i] = aux.toString()

                    }

                    playerName.append(aux)

                }
            }

            return playerName.toString()
        }

    }
}