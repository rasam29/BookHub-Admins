package com.example.rasam.bookhubadmins.auth.fragments.reportVerify.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TabHost
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rasam.bookhubadmins.R
import com.example.rasam.bookhubadmins.auth.fragments.BlockedFragment
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.ReportViewState
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.bussiness.intractor.ReportIntractorImple
import com.example.rasam.bookhubadmins.auth.fragments.reportVerify.presenter.ReportVerificationPresenter
import com.example.rasam.bookhubadmins.maintanance.infraStructure.DataBase.DAQ
import com.example.rasam.bookhubadmins.maintanance.infraStructure.net.RequestManager
import com.example.rasam.bookhubadmins.maintanance.parent.ParentActivity
import com.example.rasam.bookhubadmins.maintanance.parent.ParentFragment

class ReportVerifyActivity : ParentActivity<ReportVerifyView, ReportVerificationPresenter>(), ReportVerifyView, TextWatcher {
    var context = this
    override fun stablishPresenter(): ReportVerificationPresenter {
        return ReportVerificationPresenter(ReportIntractorImple(RequestManager(), DAQ()), this)
    }


//    @BindView(R.id.verify_container)
    lateinit var verify_container: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_report_verification)
        ButterKnife.bind(this)
        verify_container = findViewById(R.id.verify_container)
        verify_container.addTextChangedListener(this)


    }


    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(chars: CharSequence?, p1: Int, p2: Int, p3: Int) {

            if (verify_container.text.length == 4) {
                presenter.sendCodeToServer(chars.toString(), context)
            }


    }

    override fun render(viewState: ReportViewState?) {
        if (viewState is ReportViewState.OnCodeOk) {
            onCodeOK()
        } else if (viewState is ReportViewState.OnCodeWrong) {
            onCodeWrong()
        } else if (viewState is ReportViewState.OnNetError) {
            Toast.makeText(context, "خطا در ارتباط با سرور", Toast.LENGTH_SHORT).show()
        } else throw IllegalArgumentException("wrong state recieved in render")
    }

    override fun onCodeOK() {
      Log.d("verify Report","EveryThing is ok")
    }

    override fun onCodeWrong() {
        verify_container.error = "کد وارد شده صحیح نمی باشد"
    }


}