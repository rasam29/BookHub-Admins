package com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.AuthKeyViewState
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.bussiness.intractor.AuthKeyIntractorImple
import com.example.rasam.bookhubadmins.auth.fragments.AuthKeyVerification.presenter.AuthKeyPresenter
import com.example.rasam.bookhubadmins.auth.fragments.BlockedFragment
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.view.ReportVerifyActivity
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager
import com.example.rasam.bookhubadmins.maintanance.parent.androidComponent.ParentActivity

class AuthKeyActivity : ParentActivity<AuthKeyView, AuthKeyPresenter>(), AuthKeyView {


    lateinit var verifyButton: Button

    @BindView(R.id.auth_key_input)
    lateinit var planeText: EditText


    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar


    override fun stablishPresenter(): AuthKeyPresenter {
        return AuthKeyPresenter(AuthKeyIntractorImple(RequestManager()), this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_auth_key)
        ButterKnife.bind(this)
        verifyButton = findViewById(R.id.verifyButton)
        verifyButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            presenter.sendPhoneToServer(planeText.text.toString(), this)
        }
        progressBar = findViewById(R.id.progressBar)
        planeText = findViewById(R.id.auth_key_input)

        presenter.attachView(this)
    }


    override fun render(viewState: AuthKeyViewState?) {
        if (viewState is AuthKeyViewState.OnNetError) {
            progressBar.visibility = View.GONE
            Toast.makeText(this, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show()
        } else if (viewState is AuthKeyViewState.OnAuthKeyNotFound) {
            progressBar.visibility = View.GONE
            onAuthKeyNotFound()
        } else if (viewState is AuthKeyViewState.OnAuthNotValid) {
            progressBar.visibility = View.GONE
            planeText.error = "لطفا کلید را وارد کنید"

        } else if (viewState is AuthKeyViewState.OnUserMoreThan3Attemts) {
            onMoreThanThreeAttemts()

        } else if (viewState is AuthKeyViewState.OnAuthOk) {
            progressBar.visibility = View.GONE
            gotoReportVerification()
        }
    }


    override fun gotoReportVerification() {
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, ReportVerifyActivity::class.java))
        }, 1000)


    }

    override fun onMoreThanThreeAttemts() {
        startActivity(Intent(this, BlockedFragment::class.java))
        finish()
    }

    override fun onAuthKeyNotFound() {
        planeText.error = "کلیدی با این مشخصات یافت نشد"
    }


}