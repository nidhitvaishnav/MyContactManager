package com.utd.castlesword.mycontactmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**assignment of subject 6326 Human computer interaction
 * Designed by: Nidhi Vaishnav (ntv170030)**/


public class MainActivity extends AppCompatActivity {
    //initializing objects
    List<Contacts> listPhoneBook;
    ListView contact_lv;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting contact_lv object
        contact_lv=(ListView)findViewById(R.id.contact_lv);
        //creating arraylist of contact
        listPhoneBook = new ArrayList<Contacts>();

        //reading contact list from file
        readFile();

        //storing contact details in the list view
        adapter = new ContactAdapter(this, listPhoneBook);
        contact_lv.setAdapter(adapter);

        //list onItemClick listener, which gives indication what is supposed
        //to be done if item of the list is clicked
        contact_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;
                TextView tvContact = (TextView) view.findViewById(R.id.contact_tv);
                String  contactNo    = tvContact.getText().toString();
                String firstName = findFirstName(contactNo);
                String lastName = findLastName(contactNo);
                String mailId = findMailId(contactNo);
                Intent selectedContactIntent = new Intent(getApplicationContext(),ModifyContact.class);
                selectedContactIntent.putExtra(getString(R.string.contactNo_intent), contactNo);
                selectedContactIntent.putExtra(getString(R.string.contactFirstName_intent), firstName);
                selectedContactIntent.putExtra(getString(R.string.contactLastName_intent), lastName);
                selectedContactIntent.putExtra(getString(R.string.contactMail_id_intent), mailId);
                startActivity(selectedContactIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creating add menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item add
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), CreateContact.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        //when activity is resumed, read file and load items
        contact_lv=(ListView)findViewById(R.id.contact_lv);
        listPhoneBook = new ArrayList<Contacts>();
        readFile();
        Collections.sort(listPhoneBook, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contacts c1 = (Contacts) o1;
                Contacts c2 = (Contacts) o2;
                return c1.getFirstName().compareToIgnoreCase(c2.getFirstName());
            }
        });
        adapter = new ContactAdapter(this, listPhoneBook);
        contact_lv.setAdapter(adapter);
        super.onResume();
    }

    public void readFile(){
        //read file
        Context context = getApplicationContext();
        // open the file for reading
        FileInputStream instream = null;
        try {
            instream = context.openFileInput(getString(R.string.contact_filePath));
            try {
                // if file the available for reading
                if (instream != null) {
                    // prepare the file for reading
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);

                    String line;

                    // read every line of the file into the line-variable, on line at the time
                    do {
                        line = buffreader.readLine();
                        List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

                        listPhoneBook.add(new Contacts(
                                items.get(0), items.get(1), items.get(2), items.get(3)));
                    } while (line != null);
                    instream.close();
                }
            }
            catch (Exception ex) {
                // print stack trace.
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    String findFirstName(String contactNumber){
        String firstName="";
        for(int i = 0 ; i < listPhoneBook.size() ; i++){
            Contacts contact = (Contacts) listPhoneBook.get(i);
            if (contact.getContactNumber()==contactNumber) {
                firstName = contact.getFirstName();
            }
        }
        return firstName;
    }
    String findLastName(String contactNumber){
        String lastName = "";
        for(int i = 0 ; i < listPhoneBook.size() ; i++){
            Contacts contact = (Contacts) listPhoneBook.get(i);
            if (contact.getContactNumber()==contactNumber) {
                lastName = contact.getLastName();
            }
        }
        return lastName;
    }
    String findMailId(String contactNumber){
        String mailId="";
        for (int i=0; i<listPhoneBook.size(); i++){
            Contacts contact = (Contacts)listPhoneBook.get(i);
            if(contact.getContactNumber()==contactNumber){
                mailId=contact.getMailId();
            }
        }
        return mailId;
    }
}
