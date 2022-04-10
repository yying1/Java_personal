//Frank Yue Ying | yying2@
package com.cmu.androidapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    MainActivity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Referenced from https://stackoverflow.com/questions/19266553/android-caused-by-android-os-networkonmainthreadexception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.fragment_first);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        final MainActivity ma = this;

        Button searchbutton = (Button)findViewById(R.id.button_search);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((EditText)findViewById(R.id.playerID)).getText().toString().isEmpty()){
                } else{
                    int searchID = Integer.parseInt(((EditText)findViewById(R.id.playerID)).getText().toString());
                    boolean overall = ((Switch)findViewById(R.id.search_action)).isChecked();
                    fpl FPL = new fpl();
                    System.out.println(searchID);
                    System.out.println(overall);
                    String result = FPL.handle_search(searchID,overall);
                    System.out.println(result);
                    TextView result_text = (TextView) findViewById(R.id.search_result);
                    result_text.setText(result);
                    TextView searchView = (EditText)findViewById(R.id.playerID);
                    searchView.setText("");
                }

            }
        });

//        Button search_switch_button = (Button)findViewById(R.id.button_switch);
//        search_switch_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                me.setContentView(R.layout.fragment_second);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}