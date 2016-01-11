package com.ziyan.handledstudentscollect;

/**
 * Created by Administrator on 2015/12/25.
 */
public class User {
    private String UserAccounts;
    private String UserPassword;

    public static final String _ID="id";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    public static final String IS_SAVED="is_saved";
    public User(String UserAccounts, String UserPassword)
    {
        this.UserAccounts=UserAccounts;
        this.UserPassword=UserPassword;
    }
    public String getAccounts()
    {
        return  UserAccounts;
    }
    public String getPassword()
    {
        return  UserPassword;
    }
}
