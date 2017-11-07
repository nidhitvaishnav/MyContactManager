package com.utd.castlesword.mycontactmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**assignment of subject 6326 Human computer interaction
 * Designed by: Nidhi Vaishnav (ntv170030)**/

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private List<Contacts> contactsList;

    public ContactAdapter(Context inContext, List<Contacts> inContacts){
        context = inContext;
        contactsList = inContacts;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get selected entry
        Contacts entry = contactsList.get(position);

        // inflating list view layout if null
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.contact_list_contents, null);
        }

        // set name
        TextView tvName = (TextView)convertView.findViewById(R.id.fullName_tv);
        String fullName = entry.getFirstName()+" "+entry.getLastName();
        tvName.setText(fullName);

        // set phone
        TextView tvPhone = (TextView)convertView.findViewById(R.id.contact_tv);
        tvPhone.setText(entry.getContactNumber());

        return convertView;

    }
}
