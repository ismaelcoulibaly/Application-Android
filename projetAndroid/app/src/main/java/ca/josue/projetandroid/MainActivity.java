package ca.josue.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ca.josue.projetandroid.fragments.Help;
import ca.josue.projetandroid.fragments.Propos;
import ca.josue.projetandroid.navigation.Add;
import ca.josue.projetandroid.navigation.Home;
import ca.josue.projetandroid.navigation.Message;
import ca.josue.projetandroid.navigation.Profile;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView nav_bar = findViewById(R.id.nav_bar);
        nav_bar.setOnNavigationItemSelectedListener(this);
        showFragment(Home.class);
    }

    /*****************  Menu de Navigation  *****************/
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch(item.getItemId()){
            case R.id.optionHome:
                showFragment(Home.class);
                break;
            case R.id.optionAdd:
                showFragment(Add.class);
                break;
            case R.id.optionMessage:
                showFragment(Message.class);
                break;
            case R.id.optionProfile:
                showFragment(Profile.class);
                break;
        }
        return false;
    }

    /*****************  Menu d'options *****************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionPropos:
                showFragment(Propos.class);
                return true;
            case R.id.optionHelp:
                showFragment(Help.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /*****************  Affichage des Fragments  *****************/
    private void showFragment(Class<? extends Fragment> fragment) {
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragment.getName());

            if(currentFragment == null)
                currentFragment = fragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, currentFragment, fragment.getName()).commit();

        } catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            Log.i("error","erreur au moment d'instancier fragment");
        }
    }
}