package com.example.administrator.experiment9_2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final int Contacts_DIR=0;
    public static final int Contacts_ITEM=1;
    public static final String AUTHORITY="com.example.MyContentProvider.provider";
    private MyDatabaseHelper dbHelper;
    private static UriMatcher uriMatcher;
    static{
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contacts",Contacts_DIR);
        uriMatcher.addURI(AUTHORITY,"contacts/#",Contacts_ITEM);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case Contacts_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.MyContentProvider.provider.contacts";
            case Contacts_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.MyContentProvider.provider.contacts";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        dbHelper=new MyDatabaseHelper(getContext(),"ContactPerson.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch(uriMatcher.match(uri)){
            case Contacts_DIR:
                cursor=db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Contacts_ITEM:
                String id=uri.getPathSegments().get(1);
                cursor=db.query("contacts",projection,"id=?",new String[]{id},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
