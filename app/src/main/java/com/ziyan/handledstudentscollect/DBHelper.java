package com.ziyan.handledstudentscollect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2015/12/31.
 */
public class DBHelper {
    private static final  String _ID="ID";
    private static final String USERNAME="USERNAME";
    private static  final String PASSWORD="PASSWORD";
    private static final String IS_SAVED="IS_SAVED";
    public static final int DB_VERSION=2;
    public static final String DB_NAME="user.db";
    public static final String USER_TABLE_NAME="user_table";
    public static final String []USER_COLS={_ID,USERNAME,PASSWORD,IS_SAVED};
    private static final String CREATE_USER_TABLE="CREATE TABLE "+USER_TABLE_NAME+"("
            +USER_COLS[0]+"  INTEGER PRIMARY KEY AUTOINCREMENT,"
            +USER_COLS[1]+"  TEXT,"
            +USER_COLS[2]+"  TEXT,"
            +USER_COLS[3]+"  INTEGER);";

    private SQLiteDatabase db;
    private DBopenHelper dbopenHelper;

    private static class DBopenHelper extends SQLiteOpenHelper
    {
        public DBopenHelper(Context context) {
            super(context,DB_NAME,null,DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISIS"+USER_TABLE_NAME);
            onCreate(db);
        }
    }

    public DBHelper(Context context)
    {
        this.dbopenHelper=new DBopenHelper(context);
        establishDB();
    }
    private void  establishDB()
    {
        if(null==this.db)
        {
            this.db=this.dbopenHelper.getWritableDatabase();
            Log.e("test","数据库创建成功！");
        }
    }



    public long delete(String userName) {
        long id = db.delete(USER_TABLE_NAME, USERNAME + " = '" + userName
                + "'", null);
        return id;
    }

    public long insert(String userName, String password, int isSaved) {
        ContentValues values=new ContentValues();
        values.put(USERNAME,userName);
        values.put(PASSWORD,password);
        values.put(IS_SAVED,isSaved);
        long id=db.insert(USER_TABLE_NAME,null,values);
        return id;
    }

    public long update(String userName, String password, int isSaved) {
        ContentValues values=new ContentValues();
        values.put(USERNAME,userName);
        values.put(PASSWORD,password);
        values.put(IS_SAVED,isSaved);
        long id = db.update(USER_TABLE_NAME, values, USERNAME + " = '"
                + userName + "'", null);
        return id;
    }


    public String queryPasswordByName(String userName)
    {
        String sql = "select * from " + USER_TABLE_NAME + " where "
                + USERNAME + " = '" + userName + "'";
        Cursor cursor=db.rawQuery(sql,null);
        String password="";
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            password=cursor.getString(cursor.getColumnIndex(PASSWORD));
        }
        return password;
    }

    public int queryIsSavedByName(String userName) {

        String sql = "select * from " + USER_TABLE_NAME + " where "
                + USERNAME + " = '" + userName + "'";
        Cursor cursor = db.rawQuery(sql, null);
        int isSaved = 0;
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            isSaved=cursor.getInt(cursor.getColumnIndex(IS_SAVED));
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
                    userNames[i]=cursor.getString(cursor.getColumnIndex(USERNAME));
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
