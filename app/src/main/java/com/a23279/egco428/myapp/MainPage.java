package com.a23279.egco428.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private LoginDataSource dataSource;
    private ArrayAdapter<LoginMessage> loginArrayAdapter;
    public static final String User = "username";
    public static final String La = "latitude";
    public static final String Long = "longitude";

    public static final int DETAIL_REQ_CODE = 1001;
    protected List<LoginMessage> values;

    private ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle(Html.fromHtml("<font color=\"white\">" + "Main Page" + "</font>"));
        mActionBar.setDisplayHomeAsUpEnabled(true);

        dataSource = new LoginDataSource(this);
        dataSource.open();
        values = dataSource.getAllComments();
        loginArrayAdapter = new loginArrayAdapter(this,0,values);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(loginArrayAdapter); //push data in adapter into listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                LoginMessage login = loginArrayAdapter.getItem(position);
                displayDetail(login);
            }
        });
    }

    private void displayDetail(LoginMessage login){
        Log.d("MainPage","Displaying : "+login.getUsername()+login.getLatitude()+login.getLongitude());
        Intent intent = new Intent(this,LocationPage.class);
        intent.putExtra(User,login.getUsername());
        intent.putExtra(La,login.getLatitude());
        intent.putExtra(Long,login.getLongitude());
        startActivityForResult(intent,DETAIL_REQ_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.dismiss();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
            builder.setTitle("Main Page");
            builder.setMessage("Do you want to logout?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();


        }

        return super.onOptionsItemSelected(item);
    }

    class loginArrayAdapter extends ArrayAdapter<LoginMessage> {
        Context context;
        List<LoginMessage> objects;

        public loginArrayAdapter(Context context, int resource, List<LoginMessage> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LoginMessage login = objects.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.username_list, null);

            TextView txt = (TextView) view.findViewById(R.id.textView3);
            txt.setText(String.valueOf(login.getUsername()));

            return view;
        }

    }

    @Override
    protected void onResume(){
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause(){
        dataSource.close();
        super.onPause();
    }

}
