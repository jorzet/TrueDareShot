package com.jorzet.truedareshot.request

import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class CategoriesTask(): AbstractRequestTask<Void, Void, Void>() {

    private val TAG: String = "CategoriesTask"
    private val CATEGORIES_REFERENCE: String = "categories"

    /*
     * Database object
     */
    private lateinit var mFirebaseDatabase: DatabaseReference

    fun requestGetCategories() {
        mFirebaseDatabase = FirebaseDatabase
            .getInstance()
            .getReference(CATEGORIES_REFERENCE)

        mFirebaseDatabase = FirebaseDatabase
            .getInstance()
            .getReference(CATEGORIES_REFERENCE)
        // Attach a listener to read the data at our posts reference
        mFirebaseDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.getValue()
                if (post != null) {
                    val map = (post as HashMap<*, *>)
                    Log.d(TAG, "user data ------ " + map.size)
                    val courses = ArrayList<Category>()
                    for (key in map.keys) {
                        val courseMap = map.get(key) as HashMap<*, *>
                        val course = Gson().fromJson(JSONObject(courseMap).toString(), Category::class.java)
                        course.categoryId = key.toString()
                        courses.add(course)
                    }

                    Log.d(TAG, "courses data ------ " )
                    onRequestListenerSucces.onSuccess(courses)
                } else {
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