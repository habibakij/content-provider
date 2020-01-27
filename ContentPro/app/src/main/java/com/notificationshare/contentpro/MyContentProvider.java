package com.notificationshare.contentpro;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private static final UriMatcher URI_MATCHER;
    private static final String AUTHORITY_URI= "trialcontentprovider";
    private static final String URI_STRING= "content://"+AUTHORITY_URI;
    public static final Uri CONTENT_URI= Uri.parse(URI_STRING);
    SQLiteDatabase liteDatabase;
    SQLHelper helper;

    static {
        URI_MATCHER= new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY_URI, SQLHelper.DATABASE_NAME, 1);
        URI_MATCHER.addURI(AUTHORITY_URI, SQLHelper.DATABASE_TABLE+"#",2);
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
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId= liteDatabase.insert(SQLHelper.DATABASE_TABLE,null, values);
        Uri insertUri= Uri.parse(CONTENT_URI+SQLHelper.DATABASE_TABLE+"/"+rowId);
        return insertUri;
    }

    @Override
    public boolean onCreate() {
        helper= new SQLHelper(getContext());
        liteDatabase= helper.getWritableDatabase();
        // TODO: Implement this to initialize your content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER.match(uri)){
            case 1:
                Cursor cursor= liteDatabase.query(SQLHelper.DATABASE_TABLE, projection, selection, selectionArgs, sortOrder, null, null);
                return cursor;
            case 2:
                String id= uri.getLastPathSegment();
                Cursor cursor1= liteDatabase.query(SQLHelper.DATABASE_TABLE, projection, SQLHelper.COLOUM_ID+" =?", new String[]{id}, null, null, null);
                return cursor1;

            default:
                try {
                    throw new IllegalAccessException("Invalid uri exception");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}