package com.utd.castlesword.mycontactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**assignment of subject 6326 Human computer interaction
 * Designed by: Nidhi Vaishnav (ntv170030)**/

public class CreateContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        Intent intent = getIntent();

    }

    public void onClickCreateContactBtn(View view){
        //perform this method when create contact button is clicked
        List<Contacts> listPhoneBook = new ArrayList<Contacts>();
        //getting content from editText
        EditText etFirstName = (EditText)findViewById(R.id.et_firstName);
        String firstName = etFirstName.getText().toString();
        EditText etLastName = (EditText)findViewById(R.id.et_lastName);
        String lastName = etLastName.getText().toString();
        EditText etContactNo = (EditText)findViewById(R.id.et_contactNumber);
        String contactNo = etContactNo.getText().toString();
        EditText etEmailId = (EditText)findViewById(R.id.et_emailId);
        String emailId = etEmailId.getText().toString();
        //storing content in phoneBook
        listPhoneBook.add(new Contacts(
                firstName, lastName, contactNo, emailId));

        //storing contacts in the form of string
        String outStr = firstName+","+lastName+","+contactNo+","+emailId+"\r\n";

        //TODO: below code rewrites the file
        FileOutputStream outputStream = null;
        try {
//            outputStream  =  openFileOutput(getString(R.string.contact_filePath), getApplicationContext().MODE_PRIVATE);
            outputStream  =  openFileOutput(getString(R.string.contact_filePath), getApplicationContext().MODE_APPEND);

            outputStream.write(outStr.getBytes());

//            outputStream.w
            outputStream.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
