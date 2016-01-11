package com.ziyan.handledstudentscollect;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class RegisterFragment extends android.app.Fragment {


    private Button registerToLogin;
    private EditText _accounts;
    private EditText _password;
    private ImageView _clearAccounts;
    private ImageView _clearPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Register_View = inflater.inflate(R.layout.fragment_register, container, false);
        //寻找页面控件
        findViewById(Register_View);
        //完成控件事件
        Register();
        return Register_View;
    }

    //寻找页面控件并赋值
    private void findViewById(View view) {
        _accounts = (EditText) view.findViewById(R.id._accounts);
        _password = (EditText) view.findViewById(R.id._password);
        _clearAccounts = (ImageView) view.findViewById(R.id._image_clearAccounts);
        _clearPassword = (ImageView) view.findViewById(R.id._image_clearPassword);
        registerToLogin = (Button) view.findViewById(R.id.Register);
    }

    //完成页面控件事件
    private void Register() {
        setAccounts();
        setPassword();
        clearAccounts();
        clearPassword();
        registeredToLogin();
    }

    //完成注册并转到登录页面
    private void registeredToLogin()
    {
        registerToLogin .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.Container, new LoginFragment()).commit();
            }
        });
    }

    //注册账户
    @NonNull
    private EditText setAccounts() {
        _accounts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = _accounts.getText().toString();
                if (null != str) {
                    _clearAccounts.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return _accounts;
    }

    //清空注册账户
    private void clearAccounts() {
        _clearAccounts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _accounts.setText("");
                        _clearAccounts.setVisibility(View.INVISIBLE);

                    }
                }
        );
    }

    //输入注册账号密码
    @NonNull
    private EditText setPassword() {
        _password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = _password.getText().toString();
                if (null != str) {
                    _clearPassword.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return _password;
    }

    //清空注册账户密码
    private void clearPassword() {

        _clearPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _password.setText("");
                _clearPassword.setVisibility(View.INVISIBLE);
            }
        });
    }
}
