package com.ziyan.handledstudentscollect;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ziyan.adapter.MySimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ziyan.com.zui.MyToast;

public class LoginAndRegister extends AppCompatActivity {
    private EditText accounts;
    private EditText password;
    private EditText _accounts;
    private EditText _password;
    private CheckBox rememberPassword;
    private DBHelper dbHelper;
    private Button registerToLogin;
    private Button ToRegister;
    private Button ToMain;
    private ImageView openRememberList;
    private PopupWindow popView;
    private ListView UserList;
    private RememberAccountsAdapter dropDownAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        init();
    }

    public void init() {

        dbHelper = new DBHelper(this);
        findViewByIdInLogin();
        findViewByIdInRegister();
    }

    public void findViewByIdInLogin() {
        accounts = (EditText) findViewById(R.id.accounts);
        password = (EditText) findViewById(R.id.password);
        rememberPassword = (CheckBox) findViewById(R.id.checkbox_RememberPassword);
    }

    public void findViewByIdInRegister() {
        _accounts = (EditText) findViewById(R.id._accounts);
        _password = (EditText) findViewById(R.id._password);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login:
                login();
                break;
            case R.id.ToRegister:
                getFragmentManager().beginTransaction().add(R.id.Container, new RegisterFragment(), "Register").commit();
                break;
            case R.id.Register:
                register();
                break;
            case R.id.open_rememberList:
                initPopView(dbHelper.queryAllUserName());
                popView.showAsDropDown(accounts);
                break;
            default:
                break;
        }
    }

    private void login() {
        findViewByIdInLogin();
        if (accounts.getText().length() == 0 && password.getText().length() == 0) {
            MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入账号和密码");
        } else {
            if (accounts.getText().length() > 0 && password.getText().length() == 0) {
                MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入密码");
            } else {
                if (accounts.getText().length() == 0 && password.getText().length() > 0) {
                    MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入账号");
                } else {
                    initPopView(dbHelper.queryAllUserName());
                    popView.showAsDropDown(accounts);
                    String nameFromDb = dbHelper.queryPasswordByName(accounts.getText().toString());
                    String nameFromUser = password.getText().toString();
                    if (nameFromUser.equals(nameFromDb)) {
                        if (rememberPassword.isChecked()) {
                            dbHelper.update(accounts.getText().toString(), password.getText().toString(), 1);
                        }
                        Intent intent = new Intent(LoginAndRegister.this, MainActivity.class);
                        intent.putExtra("name","Hello,"+accounts.getText());
                        startActivity(intent);
                        LoginAndRegister.this.finish();
                    }
                }
            }
        }
    }

    private void register() {
        findViewByIdInRegister();
        if (_accounts.getText().length() == 0 && _password.getText().length() == 0) {
            MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入账号和密码");
        } else {
            if (_accounts.getText().length() > 0 && _password.getText().length() == 0) {
                MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入密码");
            } else {
                if (_accounts.getText().length() == 0 && _password.getText().length() > 0) {
                    MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "请输入账号");
                } else {
                    dbHelper.insert(_accounts.getText().toString(), _password.getText().toString(), 0);
                    MyToast.getMyToast().ToastShow(this, (ViewGroup) findViewById(ziyan.com.zui.R.id.toast_layout_root), "注册成功！");
                    accounts.setText(_accounts.getText());
                    password.setText(_password.getText());
                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment Register = fragmentManager.findFragmentByTag("Register");
                    fragmentManager.beginTransaction().remove(Register).commit();
                }
            }
        }


    }

    public void initPopView(String[] usernames) {
        if (popView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.remeber_pop_list, null);
            List<HashMap<String, Object>> userList = new ArrayList<>();
            for (int i = 0; i < usernames.length; i++) {
                System.out.println(usernames[i]);
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("name", usernames[i]);
                userMap.put("drawable", R.mipmap.edit_delete);
                userList.add(userMap);
            }
            dropDownAdapter = new RememberAccountsAdapter(this, userList, R.layout.item_accounts, new String[]{"name", "drawable"}, new int[]{R.id.txt_Accounts, R.id.btn_DelUser});
            UserList = (ListView) v.findViewById(R.id.remember_list);
            UserList.setAdapter(dropDownAdapter);
            UserList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    accounts.setText(UserList.getItemAtPosition(position).toString());
                }
            });
            popView = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        popView.setFocusable(true);
        popView.setOutsideTouchable(true);
        popView.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_gray));
    }

    class RememberAccountsAdapter extends MySimpleAdapter {
        class ViewHolder {
            private TextView user_Account;
            private ImageButton user_Del;
        }

        private List<HashMap<String, Object>> data;

        public RememberAccountsAdapter(Context context, List<HashMap<String, Object>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.data = data;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(LoginAndRegister.this).inflate(R.layout.item_accounts, null);
                viewHolder.user_Account = (TextView) convertView.findViewById(R.id.txt_Accounts);
                viewHolder.user_Del = (ImageButton) convertView.findViewById(R.id.btn_DelUser);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.user_Account.setText(data.get(position).get("name").toString());
            viewHolder.user_Account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String UserName[] = dbHelper.queryAllUserName();
                    accounts.setText(UserName[position]);
                    popView.dismiss();
                }
            });
            viewHolder.user_Del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String UserName[] = dbHelper.queryAllUserName();
                    if (UserName.length > 0) {
                        dbHelper.delete(UserName[position]);
                    }
                    String newUserName[]= dbHelper.queryAllUserName();
                    if (UserName.length > 0) {
                        initPopView(newUserName);
                        popView.showAsDropDown(accounts);
                    } else {
                        popView.dismiss();
                        popView = null;
                    }
                }
            });
            return convertView;
        }
    }
}
