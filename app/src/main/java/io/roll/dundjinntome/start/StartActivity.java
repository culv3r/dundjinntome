package io.roll.dundjinntome.start;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import io.roll.dundjinntome.newCharacter.MakeCharacterActivity;
import io.roll.dundjinntome.start.CharacterFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.roll.dundjinntome.R;
import io.roll.dundjinntome.data.CharContent;
import io.roll.dundjinntome.viewCharacter.ViewCharacterActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class StartActivity extends AppCompatActivity implements CharacterFragment.OnListFragmentInteractionListener {
    private DialogFragment characterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void newCharacter(View view) {
        Intent intent = new Intent(this, MakeCharacterActivity.class);



        startActivity(intent);
    }

    public void loadCharacter(View view){

        // Create and show the dialog.
        characterFragment = CharacterFragment.newInstance(1);
        characterFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onListFragmentInteraction(CharContent.CharObj item) {
        Context context = getApplicationContext();
        if (item == null) {
            Toast.makeText(context, "Item Null", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Item Has Data", Toast.LENGTH_LONG).show();
        }

    }
}
