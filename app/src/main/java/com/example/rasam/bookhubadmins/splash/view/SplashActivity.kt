package com.example.rasam.bookhubadmins.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import butterknife.ButterKnife
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.auth.view.RegisterActivity

import com.example.rasam.bookhubadmins.main.view.MainActivity
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity
import com.example.rasam.bookhubadmins.pojos.UpdateModel
import com.example.rasam.bookhubadmins.splash.Bussiness.SplashViewState
import com.example.rasam.bookhubadmins.splash.Bussiness.interactor.SplashIntractor
import com.example.rasam.bookhubadmins.splash.Presenter.SplashPresenter

class SplashActivity : SplashView, ParentActivity<SplashView, SplashPresenter>() {
    override fun stablishPresenter(): SplashPresenter {
        return SplashPresenter(this, SplashIntractor(RequestManager(), DAQ()))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)
        presenter.attachView(this)
        presenter.checkForUpdate()

    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }


    override fun render(splashState: SplashViewState?) {
        if (splashState is SplashViewState.NoUpdateState) {
            presenter.checkForToken()

        } else if (splashState is SplashViewState.OnForceUpdate) {
            showForceUpdate(splashState.updateModel)

        } else if (splashState is SplashViewState.OnHaveToken) {
            gotoMain()
        } else if (splashState is SplashViewState.OnDoesntToken) {
            gotoAuth()

        } else if (splashState is SplashViewState.OnNetError) {
            showNetError()
        } else throw IllegalArgumentException("wrong splash View State Generated")
    }


    fun showForceUpdate(massage: UpdateModel) {
        var updateDialog = ForceUpdateDialog(massage,this)
        updateDialog.show()

    }

    fun showNetError() {
        Toast.makeText(this, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show()
    }

    fun gotoMain() {
        doSomeAnimation()
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },2000)
    }

    fun gotoAuth() {
        doSomeAnimation()
        Handler().postDelayed(Runnable {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        },2000)
    }
    fun doSomeAnimation(){
        //todo its optional could iplemented in next release
    }
}
