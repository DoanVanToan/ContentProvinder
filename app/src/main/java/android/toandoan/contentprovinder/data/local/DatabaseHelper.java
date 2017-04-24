package android.toandoan.contentprovinder.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Toan on 4/24/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact.db";
    private static final String SQL_CREATE_CONTACTS = "CREATE TABLE "
            + ContactContract.ContactEntry.TABLE_NAME
            + " ("
            + ContactContract.ContactEntry._ID
            + " INTEGER PRIMARY KEY,"
            + ContactContract.ContactEntry.COLUMN_NAME
            + " TEXT,"
            + ContactContract.ContactEntry.COLUMN_PHONE_NUMBER
            + " TEXT,"
            + ContactContract.ContactEntry.COLUMN_ADDRESS
            + " TEXT)";

    private static final String SQL_DELETE_CONTACTS =
            "DROP TABLE IF EXISTS " + ContactContract.ContactEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_CONTACTS);
        sqLiteDatabase.execSQL(SQL_CREATE_CONTACTS);
    }
}
