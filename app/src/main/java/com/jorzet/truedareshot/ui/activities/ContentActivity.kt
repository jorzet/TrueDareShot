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

package com.jorzet.truedareshot.ui.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import com.jorzet.truedareshot.R

import com.jorzet.truedareshot.adapters.ViewPagerAdapter
import com.jorzet.truedareshot.utils.ImagesUtil


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
    private val TAG : String = "ContentActivity"

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
        mBottomTabLayout.addTab(mBottomTabLayout.newTab().setTag(""))

        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, mBottomTabLayout.tabCount)
        mViewPager.setAdapter(mViewPagerAdapter)
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mBottomTabLayout))

        mBottomTabLayout.setupWithViewPager(mViewPager)
        mBottomTabLayout.setSelectedTabIndicatorHeight(0)
        mBottomTabLayout.setOnTabSelectedListener(onBottomTabLayoutListener)

        createTabs()

    }

    private fun createTabs() {
        val tab1 = ImageView(this);
        tab1.setImageResource(ImagesUtil.mBottomSelectedIcons[0]);
        tab1.setPadding(5,5,5,5)

        val tab2 = ImageView(this);
        tab2.setImageResource(ImagesUtil.mBottomUnselectedIcons[1]);
        tab2.setPadding(5,5,5,5)

        val tab3 = ImageView(this);
        tab3.setImageResource(ImagesUtil.mBottomUnselectedIcons[2]);
        tab3.setPadding(0,0,0,0)

        val tab4 = ImageView(this);
        tab4.setImageResource(ImagesUtil.mBottomUnselectedIcons[3]);
        tab4.setPadding(5,5,5,5)

        val tab5 = ImageView(this);
        tab5.setImageResource(ImagesUtil.mBottomUnselectedIcons[4]);
        tab5.setPadding(5,5,5,5)

        mBottomTabLayout.getTabAt(0)!!.setCustomView(tab1)
        mBottomTabLayout.getTabAt(1)!!.setCustomView(tab2)
        mBottomTabLayout.getTabAt(2)!!.setCustomView(tab3)
        mBottomTabLayout.getTabAt(3)!!.setCustomView(tab4)
        mBottomTabLayout.getTabAt(4)!!.setCustomView(tab5)
    }

    /*
     * Listener to know what tab from bottom TabLayout is selected
     */
    private val onBottomTabLayoutListener = object : TabLayout.OnTabSelectedListener {

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            val tabIcon = mBottomTabLayout.getTabAt(tab!!.position)!!.customView as ImageView
                tabIcon.setImageResource(ImagesUtil.mBottomUnselectedIcons[tab.position])
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            val tabIcon = mBottomTabLayout.getTabAt(tab!!.position)!!.customView as ImageView
            tabIcon.setImageResource(ImagesUtil.mBottomSelectedIcons[tab.position])
        }
    }

    private fun deleteAndCleanTabs() {

    }
}