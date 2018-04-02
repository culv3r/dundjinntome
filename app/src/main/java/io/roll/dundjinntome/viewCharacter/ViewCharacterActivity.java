package io.roll.dundjinntome.viewCharacter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import io.roll.dundjinntome.R;
import io.roll.dundjinntome.start.StartActivity;
import io.roll.dundjinntome.viewCharacter.dummy.DummyContent;

/**
 * Created by culv3r on 3/15/18.
 */

public class ViewCharacterActivity extends AppCompatActivity implements
        MainFragment.OnFragmentInteractionListener, SkillFragment.OnListFragmentInteractionListener,
        EquipmentFragment.OnListFragmentInteractionListener,
        SpellFragment.OnFragmentInteractionListener, FeatureFragment.OnFragmentInteractionListener,
        FAQFragment.OnFragmentInteractionListener, LegalFragment.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private MainFragment mainFragment = new MainFragment();
    private SkillFragment skillFragment = new SkillFragment();
    private EquipmentFragment equipmentFragment = new EquipmentFragment();
    private SpellFragment spellFragment = new SpellFragment();
    private FeatureFragment featureFragment= new FeatureFragment();
    private FAQFragment faqFragment = new FAQFragment();
    private LegalFragment legalFragment = new LegalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_character);

        TextView hp = findViewById(R.id.healthOver);
        TextView ac = findViewById(R.id.ACOver);
        TextView xp = findViewById(R.id.xpText);
        int testHealth,testMaxHlth,testArmor,testXP;
        testHealth = 39;
        testMaxHlth = 50;
        testArmor = 18;
        testXP = 349;
        hp.setText(getString(R.string.hpOver, testHealth, testMaxHlth));
        ac.setText(getString(R.string.armorclass, testArmor));
        xp.setText(getString(R.string.xp_text, testXP));

        // Get the Intent that started this activity
        Intent intent = getIntent();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionbar.setTitle("");
        }

        // Add the fragment to the 'fragment_placeholder' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_placeholder, mainFragment).commit();

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch(menuItem.toString()){
                            case "Main Page": {
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, mainFragment).commit();
                                break;
                            }
                            case "Skills": {
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, skillFragment).commit();
                                break;
                            }
                            case "Equipment":{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, equipmentFragment).commit();
                                break;
                            }
                            case "Spells":{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, spellFragment).commit();
                                break;
                            }
                            case "Compendium":{
                                Context context = getApplicationContext();
                                CharSequence text = "This would show the compendium.";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                break;
                            }
                            case "Class Features":{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, featureFragment).commit();
                                break;
                            }
                            case "FAQ":{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, faqFragment).commit();
                                break;
                            }
                            case "Legal":{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_placeholder, legalFragment).commit();
                                break;
                            }
                            default: {
                                Context context = getApplicationContext();
                                CharSequence text = "Something Broke";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
