package android.toandoan.contentprovinder;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.toandoan.com.contentprovinder.R;
import android.toandoan.contentprovinder.data.local.ContactProvinder;
import android.toandoan.contentprovinder.data.model.Contact;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Contact> mContacts;
    private ArrayAdapter<Contact> mAdapter;
    private ListView mListViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContacts = getData();
        mListViewContact = (ListView) findViewById(R.id.list_contact);
        findViewById(R.id.button_add_contact).setOnClickListener(this);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mContacts);
        mListViewContact.setAdapter(mAdapter);
    }

    private List<Contact> getData() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactProvinder.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                contacts.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }
        return contacts;
    }

    @Override
    public void onClick(View v) {
        int id = new Random().nextInt(10000);
        Contact contact = new Contact("Name " + id, "Phone " + id, "Address" + id);
        if (getContentResolver().insert(ContactProvinder.CONTENT_URI, contact.getContentValues()) != null) {
            // reload data
            mContacts.clear();
            mContacts.addAll(getData());
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Insert failed", Toast.LENGTH_SHORT).show();
        }
    }
}
