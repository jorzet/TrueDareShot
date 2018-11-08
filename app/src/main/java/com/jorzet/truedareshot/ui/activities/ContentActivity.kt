/*
 * Copyright [2018] [Jorge Zepeda Tinoco]
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

package com.jorzet.truedareshot.ui.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.adapters.ViewPagerAdapter

/**
 * This class call all components and adapters to build home view
 * with its tabs
 *
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class ContentActivity: BaseActivityLifeCycle() {

    /*
     * Tags
     */
    private val TAB_COUNT : Int = 5

    /*
     * UI accessors
     */
    private lateinit var mBottomTabLayout : TabLayout
    private lateinit var mViewPager : ViewPager

    /*
    * Adapters
    */
    private lateinit var mViewPagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        mBottomTabLayout = findViewById(R.id.tab_layout)
        mViewPager = findViewById(R.id.pager)

        mBottomTabLayout.addTab(mBottomTabLayout.newTab().setTag(""))
        mBottomTabLayout.addTab(mBottomTabLayout.newTab().setTag(""))
        mBottomTabLayout.addTab(mBottomTabLayout.newTab().setTag(""))
        mBottomTabLayout.addTab(mBottomTabLayout.newTab().setTag(""))

        mViewPagerAdapter = ViewPagerAdapter(baseContext, supportFragmentManager, TAB_COUNT)

        mViewPager.setAdapter(mViewPagerAdapter)
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mBottomTabLayout))
        mBottomTabLayout.setupWithViewPager(mViewPager)

    }

    private fun setTabIcons() {

    }

    private fun deleteAndCleanTabs() {

    }
}