package com.example.rasam.bookhubadmins.main.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.main.Bussiness.MainIntractorImple
import com.example.rasam.bookhubadmins.main.Bussiness.MainState
import com.example.rasam.bookhubadmins.main.presenter.MainDependency
import com.example.rasam.bookhubadmins.main.presenter.MainPresenter
import com.example.rasam.bookhubadmins.main.view.mainList.MainAdapter
import com.example.rasam.bookhubadmins.main.view.mainList.OnSwipeButtonClicked
import com.example.rasam.bookhubadmins.main.view.mainList.OnSwipeData
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity
import com.example.rasam.bookhubadmins.pojos.ads.Ads

class MainActivity : MainView, ParentActivity<MainView, MainPresenter>(),OnSwipeData {



    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var context: Context = this
    lateinit var listView:RecyclerView
    lateinit var adapter:MainAdapter
    lateinit var adsList:ArrayList<Ads>



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        listView = findViewById(R.id.main_list)
        adsList  = ArrayList()
        adapter = MainAdapter(this,adsList,this)
        listView.adapter = adapter
        var manager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listView.layoutManager = manager
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            presenter.refreshList()
        }

        listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val position = (recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            }
        })



    }

    override fun onResume() {
        super.onResume()
        presenter.refreshList()
    }


    override fun render(mainState: MainState?) {
        if (mainState is MainState.RefreshState) {
            refreshList(mainState.list)
        } else if (mainState is MainState.DeleteState) {
            Toast.makeText(context, "آگهی با موفقیت پاک شد", Toast.LENGTH_SHORT).show()
            deleteAds(mainState.ads)
        } else if (mainState is MainState.NextPaginationState) {
            appendListToRecyclerView(mainState.list)
        } else if (mainState is MainState.PromoteState) {
            Toast.makeText(context, "آگهی تایید شد", Toast.LENGTH_SHORT).show()
            deleteAds(mainState.ads)
        } else throw IllegalArgumentException("wrong state in main")
    }


    override fun onDelete(view: View?, ads: Ads) {
        presenter.deleteAdvertisment(ads)

    }

    override fun onPromote(view: View?, ads: Ads) {
        presenter.promoteAdvertisment(ads)

    }

    override fun appendListToRecyclerView(adsList: MutableList<Ads>?) {
        adapter.appendList(adsList)
    }

    override fun refreshList(adsList: MutableList<Ads>?) {
        adapter.refreshList(adsList)
    }
    fun deleteAds(ads:Ads){
        adapter.delete(ads)
    }



    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stablishPresenter(): MainPresenter {
        return MainPresenter(this,MainDependency.inject())
    }


}