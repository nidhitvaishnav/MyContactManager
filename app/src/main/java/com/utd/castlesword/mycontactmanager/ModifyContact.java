package com.utd.castlesword.mycontactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        String firstName = intent.getStringExtra(getString(R.string.contactFirstName_intent));
        String lastName = intent.getStringExtra(getString(R.string.contactLastName_intent));
        String contactNo = intent.getStringExtra(getString(R.string.contactNo_intent));
        String mailId = intent.getStringExtra(getString(R.string.contactMail_id_intent));

        // Capture the layout's TextView and set the string as its text
        TextView tvFirstName = (TextView)findViewById(R.id.data_firstName);
        tvFirstName.setText(firstName);
        TextView tvLastName = (TextView)findViewById(R.id.data_lastName);
        tvLastName.setText(lastName);
        TextView tvContactNo = (TextView)findViewById(R.id.data_contactNumber);
        tvContactNo.setText(contactNo);
        TextView tvMailId = (TextView)findViewById(R.id.data_emailId);
        tvMailId.setText(mailId);
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

        // Handle action bar item delete
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
//            //performing task to delete items
//            FileOutputStream outputStream = null;
//            //rewriting file to remove containt
//            String outStr = "";
//            try {
//                outputStream = openFileOutput(getString(R.string.contact_filePath), getApplicationContext().MODE_PRIVATE);
//                outputStream.write(outStr.getBytes());
//                outputStream.close();
//                //display file saved message
//                Toast.makeText(getBaseContext(), "File saved successfully!",
//                        Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            File inputFile = new File(getString(R.string.contact_filePath));
            File tempFile = new File("myTempFile.csv");

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(tempFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextView tvContactNo = (TextView)findViewById(R.id.data_contactNumber);
            String contactNo = tvContactNo.getText().toString();

            String currentLine;

            try {
                while((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.contains(contactNo)) continue;
                    writer.write(currentLine + System.getProperty("\r\n"));
                    writer.close();
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean successful = tempFile.renameTo(inputFile);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickModifyBtn(View view){
        //TODO: code to modify file is remaining as all data is not received
    }

}
