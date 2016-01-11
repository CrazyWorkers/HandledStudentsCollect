package com.ziyan.handledstudentscollect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/12/31.
 */
public class DBHelper {
    public static final int DB_VERSION=1;
    public static final String DB_NAME="users.db";
    public static final String USER_TABLE_NAME="user_table";
    public static final String []USER_COLS={User._ID,User.USERNAME,User.PASSWORD,User.IS_SAVED};

    private SQLiteDatabase db;
    private DBopenHelper dbopenHelper;

    private static class DBopenHelper extends SQLiteOpenHelper
    {
        public DBopenHelper(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table"+USER_TABLE_NAME+"("+User._ID+"integer primary key,"+User.USERNAME+"text,"+User.PASSWORD+"text,"+User.IS_SAVED+"INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISIS"+USER_TABLE_NAME);
            onCreate(db);
        }
    }

    private DBHelper(Context context)
    {
        this.dbopenHelper=new DBopenHelper(context);
        establishDB();
    }
    private void  establishDB()
    {
        if(null==this.db)
        {
            this.db=this.dbopenHelper.getWritableDatabase();
        }
    }

    public long insertOrUpdate(String userName,String password,int isSaved)
    {
        boolean isUpdate=false;
        String[]userNames=queryAllUserName();
        for(int i=0;i<userNames.length;i++)
        {
            if(userName.equals(userNames[i]));
            isUpdate=true;
            break;
        }
        long id=-1;
        if(isUpdate)
        {
            id=update(userName,password,isSaved);
        }
        else
        {
            if(db!=null)
            {

                id=insert(userName,password,isSaved);
            }
        }
        return id;
    }

    public long delete(String userName) {
        long id = db.delete(USER_TABLE_NAME, User.USERNAME + " = '" + userName
                + "'", null);
        return id;
    }

    public long insert(String userName, String password, int isSaved) {
        ContentValues values=new ContentValues();
        values.put(User.USERNAME,userName);
        values.put(User.PASSWORD,password);
        values.put(User.IS_SAVED,isSaved);
        long id=db.insert(USER_TABLE_NAME,null,values);
        return id;
    }

    public long update(String userName, String password, int isSaved) {
        ContentValues values=new ContentValues();
        values.put(User.USERNAME,userName);
        values.put(User.PASSWORD,password);
        values.put(User.IS_SAVED,isSaved);
        long id = db.update(USER_TABLE_NAME, values, User.USERNAME + " = '"
                + userName + "'", null);
        return id;
    }


    public String queryPasswordByName(String userName)
    {
        String sql = "select * from " + USER_TABLE_NAME + " where "
                + User.USERNAME + " = '" + userName + "'";
        Cursor cursor=db.rawQuery(sql,null);
        String password="";
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            password=cursor.getString(cursor.getColumnIndex(User.PASSWORD));
        }
        return password;
    }

    public int queryIsSavedByName(String userName) {

        String sql = "select * from " + USER_TABLE_NAME + " where "
                + User.USERNAME + " = '" + userName + "'";
        Cursor cursor = db.rawQuery(sql, null);
        int isSaved = 0;
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            isSaved=cursor.getInt(cursor.getColumnIndex(User.IS_SAVED));
        }
        return isSaved;
    }

    public String[] queryAllUserName() {
        if(db!=null)
        {
            Cursor cursor=db.query(USER_TABLE_NAME,null,null,null,null,null,null);
            int count=cursor.getCount();
            String[] userNames=new String[count];
            if(count>0)
            {
                cursor.moveToFirst();
                for (int i=0;i<count;i++)
                {
                    userNames[i]=cursor.getString(cursor.getColumnIndex(User.USERNAME));
                    cursor.moveToNext();
                }
            }
            return userNames;
        }
        else
        {
            return new String[0];
        }


    }

    public void cleanup() {
        if (this.db != null) {
            this.db.close();
            this.db = null;
        }
    }
}
