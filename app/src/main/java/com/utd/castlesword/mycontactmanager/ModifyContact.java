package com.utd.castlesword.mycontactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

/**assignment of subject 6326 Human computer interaction
 * Designed by: Nidhi Vaishnav (ntv170030)**/

public class ModifyContact extends AppCompatActivity {
    //TODO: list of Contact object is not getting passed in intent :(
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String contactNo = intent.getStringExtra(getString(R.string.contact_intent));

        // Capture the layout's TextView and set the string as its text
        TextView tvContact = (TextView)findViewById(R.id.data_contactNumber);
        tvContact.setText(contactNo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //creating menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //performing task to delete items
        FileOutputStream outputStream = null;
        //rewriting file to remove containt
        String outStr = "";
        try {
            outputStream  =  openFileOutput(getString(R.string.contact_filePath), getApplicationContext().MODE_PRIVATE);
            outputStream.write(outStr.getBytes());
            outputStream.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClickModifyBtn(View view){
        //TODO: code to modify file is remaining as all data is not received
    }

}
