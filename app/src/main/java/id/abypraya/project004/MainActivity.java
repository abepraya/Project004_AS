package id.abypraya.project004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import id.abypraya.project004.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbars;
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbars = findViewById(R.id.toolbar);
        toolbars.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbars);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    //toggle kanan
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_about_us:
                Intent implicitIntent = new Intent(Intent.ACTION_VIEW);
                implicitIntent.setData(Uri.parse("https://www.reddit.com"));
                startActivity(implicitIntent);
                break;
            case R.id.nav_contact_us:
                fragment = new ContactUsFragment();
                getSupportActionBar().setTitle("Contact");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_category:
                fragment = new CategoryFragment();
                getSupportActionBar().setTitle("Category");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_videos:
                fragment = new VideosFragment();
                getSupportActionBar().setTitle("Videos");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();

                getSupportActionBar().setTitle("Profile");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            default:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        //custom toolbar
        setSupportActionBar(binding.toolbar);

        //default fragment dibuka pertama kali -> Home
        //getSupportActionBar().setTitle("Home");
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        //membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);

        //drawer back button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        //sinkronikasi drawer
        toggle.syncState();

        //salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_about_us:
//                        fragment = new AboutUsFragment();
//                        getSupportActionBar().setTitle("About Us");
//                        binding.drawer.closeDrawer(GravityCompat.START);
//                        callFragment(fragment);
                        Intent implicitIntent = new Intent(Intent.ACTION_VIEW);
                        implicitIntent.setData(Uri.parse("https://www.reddit.com"));
                        startActivity(implicitIntent);
                        break;
                    case R.id.nav_contact_us:
                        fragment = new ContactUsFragment();
                        getSupportActionBar().setTitle("Contact");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_category:
                        fragment = new CategoryFragment();
                        getSupportActionBar().setTitle("Category");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_videos:
                        fragment = new VideosFragment();
                        getSupportActionBar().setTitle("Videos");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();

                        getSupportActionBar().setTitle("Profile");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                        break;
                    default:
                        fragment = new HomeFragment();
                        getSupportActionBar().setTitle("Home");
                        binding.drawer.closeDrawer(GravityCompat.START);
                        callFragment(fragment);
                }
                return true;
            }
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}