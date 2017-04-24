package android.toandoan.contentprovinder.data.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Toan on 4/24/2017.
 */

public class ContactProvinder extends ContentProvider {
    public static final String AUTHORITY = "android.toandoan.ContentProvinder";
    public static final String SCHEME = "content://";
    public static final String URL_DATA_BASE = SCHEME + AUTHORITY + "/contact";
    public static final Uri CONTENT_URI = Uri.parse(URL_DATA_BASE);
    private static final int URI_CONTACT = 0;

    private static UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, "contact", URI_CONTACT);
    }

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_CONTACT:
                return mSqLiteDatabase.query(ContactContract.ContactEntry.TABLE_NAME,
                        projection, selection, selectionArgs, sortOrder, null, null);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long index;
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_CONTACT:
                index = mSqLiteDatabase.insert(ContactContract.ContactEntry.TABLE_NAME,
                        null, values);
                Uri uri_ = null;
                if (index != -1) {
                    uri_ = ContentUris.withAppendedId(CONTENT_URI, index);
                }
                return uri_;
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case URI_CONTACT:
                return mSqLiteDatabase.delete(ContactContract.ContactEntry.TABLE_NAME,
                        selection, selectionArgs);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case URI_CONTACT:
                return mSqLiteDatabase.update(ContactContract.ContactEntry.TABLE_NAME, values,
                        selection, selectionArgs);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
