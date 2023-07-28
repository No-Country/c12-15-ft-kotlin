package com.nocountry.movie_no_country.feature_home.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.nocountry.movie_no_country.HomeFragmentPageAdapter
import com.nocountry.movie_no_country.MainActivity
import com.nocountry.movie_no_country.R
import com.nocountry.movie_no_country.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    private lateinit var tabLayout: TabLayout
    private lateinit var vPager: ViewPager2
    private lateinit var fragmentPageAdapter: HomeFragmentPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupTabs()

        (activity as MainActivity).showBottomNav(true)

        return binding?.root
    }

    private fun setupTabs() {
        binding?.let {
            tabLayout = it.tabLayout
            vPager = it.viewPager
            fragmentPageAdapter = HomeFragmentPageAdapter(
                (activity as MainActivity).supportFragmentManager,
                lifecycle
            )
            tabLayout.addTab(tabLayout.newTab().setText(R.string.text_button1_home))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.text_button2_home))

            vPager.adapter = fragmentPageAdapter
            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        vPager.currentItem = tab.position
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}

            })

            vPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabLayout.selectTab(tabLayout.getTabAt(position))
                }
            })

            vPager.isUserInputEnabled = false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}