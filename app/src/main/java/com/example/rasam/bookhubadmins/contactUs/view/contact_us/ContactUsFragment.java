package com.example.rasam.bookhubadmins.contactUs.view.contact_us;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rasam.bookhubadmins.R;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactDependency;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsPresenter;
import com.example.rasam.bookhubadmins.contactUs.presenter.ContactUsState;
import com.example.rasam.bookhubadmins.maintanance.parent.ParentFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by R.Arabzadeh Taktell on 7/27/2018.
 */

public class ContactUsFragment extends ParentFragment<ContactUsView,ContactUsPresenter> implements ContactUsView {


    @BindView(R.id.MainMassage)
    EditText mainMassage;


    @BindView(R.id.subjectEditTxt)
    EditText subjectEditTxt;

    @OnClick(R.id.sendButton_contactUs)
    public void SendButton(){
        if (subjectEditTxt.getText().toString().isEmpty()){
            subjectEditTxt.setError("لطفا موضوع را وارد کنید");
        }else if (mainMassage.getText().toString().isEmpty()){
            subjectEditTxt.setError("لطفا متن را وارد کنید");
        }else {
            presenter.sendMassageToServer(mainMassage.getText().toString(),subjectEditTxt.getText().toString());
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void render(ContactUsState contactUsState) {
        if (contactUsState instanceof ContactUsState.MassageSentState){
            Toast.makeText(getContext(),"پیام با موفقیت ارسال شد",Toast.LENGTH_SHORT).show();

        }else if (contactUsState instanceof ContactUsState.OnNetError){
            Toast.makeText(getContext(),"خطا در ارسال پیام",Toast.LENGTH_SHORT).show();
        }else {
            throw new IllegalArgumentException("Wrong State Generated in Contact US");
        }
    }

    @Override
    public ContactUsPresenter stableshPresenter() {
        return new ContactUsPresenter(this, ContactDependency.inject());
    }
}
