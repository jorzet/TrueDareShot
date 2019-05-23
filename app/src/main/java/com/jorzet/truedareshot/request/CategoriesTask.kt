package com.jorzet.truedareshot.request

import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class CategoriesTask(): AbstractRequestTask<Void, Void, Void>() {

    private val TAG: String = "CategoriesTask"
    private val CATEGORIES_REFERENCE: String = "category"
    private val SUBCATEGORIES_REFERENCE: String = "subcategory"

    /*
     * Database object
     */
    private lateinit var mFirebaseDatabase: DatabaseReference

    fun requestGetCategories() {
        mFirebaseDatabase = FirebaseDatabase
            .getInstance()
            .getReference(CATEGORIES_REFERENCE)

        mFirebaseDatabase.keepSynced(true)

        // Attach a listener to read the data at our posts reference
        mFirebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.value
                if (post != null) {
                    try {
                        val map = (post as List<HashMap<*, *>>)
                        Log.d(TAG, "subcategories size: " + map.size)
                        val categories = ArrayList<Category>()
                        for (item in map) {
                            val category = Gson().fromJson(JSONObject(item).toString(), Category::class.java)
                            categories.add(category)
                        }
                        if (categories.isNotEmpty()) {
                            Log.d(TAG, "categories success")
                            onRequestListenerSucces.onSuccess(categories)
                        } else {
                            Log.d(TAG, "categories null response")
                            val error = GenericError(ErrorType.NULL_RESPONSE, "")
                            onRequestLietenerFailed.onFailed(error)
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "categories null response")
                        val error = GenericError(ErrorType.NULL_RESPONSE, "")
                        onRequestLietenerFailed.onFailed(error)
                    }
                } else {
                    Log.d(TAG, "categories null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
                onRequestLietenerFailed.onFailed(databaseError.toException())
            }
        })
    }

    fun requestGetSubcategories() {
        mFirebaseDatabase = FirebaseDatabase
            .getInstance()
            .getReference(SUBCATEGORIES_REFERENCE)

        mFirebaseDatabase.keepSynced(true)

        // Attach a listener to read the data at our posts reference
        mFirebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.value
                if (post != null) {
                    try {
                        val map = (post as HashMap<*, *>)
                        Log.d(TAG, "subcategories size: " + map.size)
                        val subcategories = ArrayList<Subcategory>()
                        for (key in map.keys) {
                            val subcategoryMap = map[key] as kotlin.collections.HashMap<*, *>
                            val subcategory = Gson().fromJson(JSONObject(subcategoryMap).toString(), Subcategory::class.java)
                            subcategory.subcategoryId = key.toString()
                            subcategories.add(subcategory)
                        }

                        if (subcategories.isNotEmpty()) {
                            Log.d(TAG, "categories success")
                            onRequestListenerSucces.onSuccess(subcategories)
                        } else {
                            Log.d(TAG, "categories null response")
                            val error = GenericError(ErrorType.NULL_RESPONSE, "")
                            onRequestLietenerFailed.onFailed(error)
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "categories null response")
                        val error = GenericError(ErrorType.NULL_RESPONSE, "")
                        onRequestLietenerFailed.onFailed(error)
                    }
                } else {
                    Log.d(TAG, "categories null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
                onRequestLietenerFailed.onFailed(databaseError.toException())
            }
        })
    }
}