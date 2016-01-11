package com.ziyan.handledstudentscollect;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginFragment extends android.app.Fragment {

    private Button ToRegister;
    private Button ToMain;
    private DBHelper dbHelper;
    private EditText accounts;
    private EditText password;
    private ImageView clearAccounts;
    private ImageView clearPassword;
    private ImageView openRememberList;
    private PopupWindow popView;

    public LoginFragment() {
        // Required empty public constructor
    }

    public void  initPopView()
    {
        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","1234");
        map.put("drawable", R.mipmap.drop_down);
        list.add(map);
        AccountsAdapter Accounts=new AccountsAdapter(getActivity(),list,R.layout.list_remember_accounts,new String[]{"name","drawable"},new int[]{R.id.rememberAccounts,R.id.rememberAccounts_clearBtn});
        ListView listView=new ListView(getActivity());
        listView.setAdapter(Accounts);
        popView=new PopupWindow(listView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popView.setFocusable(true);
        popView.setOutsideTouchable(true);
        popView.setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_gray));
        popView.showAsDropDown(accounts);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View Login_View = inflater.inflate(R.layout.fragment_login, container, false);
        //寻找页面控件
        findViewById(Login_View);
        //完成控件事件
        Login();
        return Login_View;
    }

    private void findViewById(View view) {
        ToMain=(Button)view.findViewById(R.id.Login);
        ToRegister = (Button) view.findViewById(R.id.ToRegister);
        accounts = (EditText) view.findViewById(R.id.accounts);
        password = (EditText) view.findViewById(R.id.password);
        clearAccounts = (ImageView) view.findViewById(R.id.image_clearAccounts);
        clearPassword = (ImageView) view.findViewById(R.id.image_clearPassword);
        openRememberList=(ImageView)view.findViewById(R.id.open_rememberList);
    }

    private void Login() {
        setAccounts();
        setPassword();
        clearAccounts();
        clearPassword();
        ToRegister();
        openRememberList();
        ToMainPage();
    }

    private void openRememberList() {
        openRememberList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                initPopView();
            }
        });
    }

    private void ToMainPage() {
        ToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    //切换到注册页面
    private void ToRegister() {
        ToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(R.id.Container, new RegisterFragment()).commit();
            }
        });
    }

    //输入账户
    @NonNull
    private EditText setAccounts() {
        accounts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = accounts.getText().toString();
                if (null != str) {
                    clearAccounts.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return accounts;
    }

    //清空账户
    private void clearAccounts() {
        clearAccounts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accounts.setText("");
                        clearAccounts.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

    //输入密码
    @NonNull
    private EditText setPassword() {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = password.getText().toString();
                if (null != str) {
                    clearPassword.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return password;
    }

    //清空密码
    private void clearPassword() {

        clearPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
                clearPassword.setVisibility(View.INVISIBLE);
            }
        });
    }

    class ViewHolder {
        private TextView rememberAccounts;
        private ImageView rememberAccounts_delete_btn;
    }

    class AccountsAdapter extends SimpleAdapter {
        private List<HashMap<String, Object>> accounts;

        public AccountsAdapter(Context context, List<HashMap<String, Object>> accounts, int resource, String[] from, int[] to) {
            super(context, accounts, resource, from, to);
            this.accounts = accounts;
        }

        public int getCount()
        {
            return accounts.size();
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position,View convertView,ViewGroup parent)
        {
            final ViewHolder holder;
            if(convertView==null)
            {
                holder=new ViewHolder();
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.list_remember_accounts,null);
                holder.rememberAccounts=(TextView)convertView.findViewById(R.id.rememberAccounts);
                holder.rememberAccounts_delete_btn=(ImageView)convertView.findViewById(R.id.rememberAccounts_clearBtn);
                convertView.setTag(holder);
            }
            else
            {
                holder=(ViewHolder)convertView.getTag();
            }
            holder.rememberAccounts.setText("123");
            holder.rememberAccounts_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return convertView;
        }
    }

}
