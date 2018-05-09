package com.harpreet.mybussiness.Views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.harpreet.mybussiness.Model.Home_Fragment;
import com.harpreet.mybussiness.Model.NotificationFragment;
import com.harpreet.mybussiness.Model.OrderFragment;
import com.harpreet.mybussiness.Model.ProfileFragment;
import com.harpreet.mybussiness.R;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        uid = auth.getCurrentUser().getUid();

        loadFragment(new Home_Fragment());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.signOut){

            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();

            Intent intent = new Intent(ProfileActivity.this,EntersActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new Home_Fragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new OrderFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new NotificationFragment();
                break;

            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }

}
