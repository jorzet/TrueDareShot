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

package com.jorzet.truedareshot.views

import android.content.res.Resources


/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

interface QuestionView: BaseView {

    /**
     * Show question text
     */
    fun showQuestionText()

    /**
     * Show waiting button
     */
    fun showButtonIsWaiting()

    /**
     * update text view question type
     *
     * @param questionType the question type text
     */
    fun updateQuestionType(questionType: String)

    /**
     * set the player nick name
     *
     * @param currentPlayer player nick name
     */
    fun setCurrentPlayerName(currentPlayer: String)

    /**
     * @param questionText
     */
    fun setQuestionText(questionText: String)

    fun getFragmentResources(): Resources
}