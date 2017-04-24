package android.toandoan.contentprovinder.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.toandoan.contentprovinder.data.local.ContactContract;

public class Contact {
    private int mID;
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(String name, String phone, String address) {
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public Contact(Cursor cursor) {
        mName = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME));
        mPhone = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE_NUMBER));
        mAddress = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_ADDRESS));
        mID = cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry._ID));
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        if (mName != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_NAME, mName);
        }
        if (mAddress != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_ADDRESS, mAddress);
        }
        if (mPhone != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_PHONE_NUMBER, mPhone);
        }
        if (mID != 0) {
            contentValues.put(ContactContract.ContactEntry._ID, mID);
        }
        return contentValues;
    }

    @Override
    public String toString() {
        return mID + " - " + mName;
    }
}