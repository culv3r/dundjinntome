package io.roll.dundjinntome.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import io.roll.dundjinntome.R;
import io.roll.dundjinntome.viewCharacter.ViewCharacterActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void newCharacter(View view) {
        Intent intent = new Intent(this, ViewCharacterActivity.class);



        startActivity(intent);
    }

    public void loadCharacter(View view){
        Intent intent = new Intent(this, ViewCharacterActivity.class);



        startActivity(intent);
    }
}
