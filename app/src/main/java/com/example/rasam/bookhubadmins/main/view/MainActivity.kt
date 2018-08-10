package com.example.rasam.bookhubadmins.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.contactUs.view.ContactUsActivity
import com.example.rasam.bookhubadmins.main.Bussiness.MainState
import com.example.rasam.bookhubadmins.main.presenter.MainDependency
import com.example.rasam.bookhubadmins.main.presenter.MainPresenter
import com.example.rasam.bookhubadmins.main.view.mainList.MainAdapter
import com.example.rasam.bookhubadmins.main.view.mainList.OnSwipeData
import com.example.rasam.bookhubadmins.maintanance.parent.androidComponent.ParentActivity
import com.example.rasam.bookhubadmins.pojos.ads.Ads
import com.example.rasam.bookhubadmins.productInfo.view.MoreDetailsActivity
import java.util.*

class MainActivity : MainView, ParentActivity<MainView, MainPresenter>(), OnSwipeData {


    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var context: Context = this
    lateinit var listView: RecyclerView
    lateinit var adapter: MainAdapter
    lateinit var adsList: ArrayList<Ads>
    lateinit var hambIcon: ImageView
    lateinit var drawer: DrawerLayout
    lateinit var coverPhoto: ImageView
    lateinit var contactUs: LinearLayout
    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        presenter.attachView(this)

        coverPhoto = findViewById(R.id.coverPhoto)


        Glide.with(this).load(R.mipmap.cover)
                .thumbnail(0.5f)
                .into(coverPhoto)

        contactUs = findViewById(R.id.contactUsContainer)
        contactUs.setOnClickListener {
            startActivity(Intent(context, ContactUsActivity::class.java))
        }

        hambIcon = findViewById(R.id.hamburgerIcon)
        drawer = findViewById(R.id.drawerLayout)


        hambIcon.setOnClickListener {
            drawer.openDrawer(Gravity.RIGHT)
        }

        listView = findViewById<RecyclerView>(R.id.main_list)
        adsList = ArrayList()
        adapter = MainAdapter(this, adsList, this)
        listView.adapter = adapter
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listView.layoutManager = manager
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.refreshList()
            isLoading = true
        }

        val onScrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (recyclerView != null) {
                    if (!recyclerView.canScrollVertically(1)) {

                        if (!isLoading) {
                            showLoadingInList()
                            presenter.getMoreAds()
                        }
                        isLoading = true
                    }
                }
            }
        }

        listView.setOnScrollListener(onScrollListener)


    }


    override fun onResume() {
        super.onResume()
        isLoading = true
        presenter.refreshList()
    }


    override fun render(mainState: MainState?) {
        if (mainState is MainState.RefreshState) {
            Handler().postDelayed({
                isLoading = false
                refreshList(mainState.list)
            }, 1000)
        } else if (mainState is MainState.DeleteState) {
            Toast.makeText(context, "آگهی با موفقیت پاک شد", Toast.LENGTH_SHORT).show()
            deleteAds(mainState.ads)
            isLoading = false
        } else if (mainState is MainState.NextPaginationState) {
            isLoading = false

            appendListToRecyclerView(mainState.list)

        } else if (mainState is MainState.PromoteState) {
            Toast.makeText(context, "آگهی تایید شد", Toast.LENGTH_SHORT).show()
            deleteAds(mainState.ads)
            isLoading = false
        } else throw IllegalArgumentException("wrong state in main")
    }


    override fun onDelete(view: View?, ads: Ads) {

        presenter.deleteAdvertisment(ads)

    }

    override fun onPromote(view: View?, ads: Ads) {
        presenter.promoteAdvertisment(ads)

    }

    override fun onItemClick(view: View?, adsItem: Ads?) {
        presenter.cachAdvertisment(adsItem)
        startActivity(Intent(context, MoreDetailsActivity::class.java))
    }

    fun showLoadingInList() {
        adapter.addLoading()
    }


    override fun appendListToRecyclerView(adsList: MutableList<Ads>?) {
        adapter.appendList(adsList)
    }

    override fun refreshList(adsList: MutableList<Ads>?) {

       try {
           swipeRefreshLayout.isRefreshing = false
       }catch (e:Exception){
           e.printStackTrace()
       }
        adapter.refreshList(adsList)

    }


    fun deleteAds(ads: Ads) {
        adapter.delete(ads)
    }


    override fun showLoading() {
        Toast.makeText(context, "در حال بارگزاری", Toast.LENGTH_SHORT).show()
    }

    override fun stablishPresenter(): MainPresenter {
        return MainPresenter(this, MainDependency.inject())
    }


}