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

package com.jorzet.truedareshot.managers.sharedpreferences

import com.google.gson.Gson
import org.json.JSONArray

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 20/05/19.
 */

class JsonParser {
    companion object {

        /**
         * @return
         *      A json string taht contains an object
         */
        fun parseObjectToJson(obj : Any) : String {
            return Gson().toJson(obj)
        }

        /**
         * @return
         *      An object
         */
        fun getObjectFromJson(json: String, clase: Class<*>): Any {
            return Gson().fromJson<Any>(json, clase)
        }

        /**
         * @return
         *      A json array string that contains object list
         */
        fun parseObjectListToJson(objects: List<Any>) : String {
            val objectsArray = JSONArray()

            for (obj in objects) {
                objectsArray.put(Gson().toJson(obj))
            }

            return objectsArray.toString()
        }
    }
}