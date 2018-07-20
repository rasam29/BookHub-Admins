package com.example.rasam.bookhubadmins.auth.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.auth.bussiness.RegisterIntractor
import com.example.rasam.bookhubadmins.auth.bussiness.RegisterViewState
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.view.AuthKeyActivity

import com.example.rasam.bookhubadmins.auth.fragments.BlockedFragment
import com.example.rasam.bookhubadmins.auth.presenter.RegisterPresenter

import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity

class RegisterActivity : ParentActivity<RegisterView, RegisterPresenter>(), RegisterView {


    @BindView(R.id.auth_progress_bar)
    lateinit var progressBr: ProgressBar


    @BindView(R.id.parrentView)
    lateinit var parentView: RelativeLayout

    override fun stablishPresenter(): RegisterPresenter {
        return RegisterPresenter(RegisterIntractor(RequestManager()), this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        ButterKnife.bind(this)
        parentView = findViewById(R.id.parrentView)
        progressBr = findViewById(R.id.auth_progress_bar)
        presenter.attachView(this)

    }


    override fun onResume() {
        super.onResume()
        presenter.registerDevice()
    }

    override fun render(viewState: RegisterViewState?) {
        if (viewState is RegisterViewState.DeviceRegisterState) {

            presenter.registerUser()


        } else if (viewState is RegisterViewState.UserRegisterState) {
            //todo on comment the following code whenever the backend is ready
//            progressBr.visibility = View.GONE
            gotoKeyVerification()

        } else if (viewState is RegisterViewState.OnUUidBlockedState) {
            progressBr.visibility = View.GONE
            showBlockFragment()
        } else if (viewState is RegisterViewState.ONetError) {
            onNetError(viewState.action.localizedMessage)
        } else {
            Log.d("kok",viewState.toString())
        }
    }

    @SuppressLint("ResourceType")
    fun onNetError(str: String) {
//        Snackbar.make(parentView, R.layout.snack_bar_net_error_actions, 2000).setAction(R.id.retry_button_snackBar, View.OnClickListener {
//            if (str == Constants.DEVICE_REGISTER) {
//                presenter.registerDevice()
//            } else if (str == Constants.USER_REGISTER) {
//                presenter.registerUser()
//            }
//        })

        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }


    fun showBlockFragment() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, BlockedFragment::class.java))
            finish()
        },2000)

    }


    fun gotoKeyVerification() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, AuthKeyActivity::class.java))
            finish()
        },1000)

    }


}