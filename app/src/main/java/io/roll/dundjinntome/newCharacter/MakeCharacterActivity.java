package io.roll.dundjinntome.newCharacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.roll.dundjinntome.R;
import io.roll.dundjinntome.data.CharInst;
import io.roll.dundjinntome.start.StartActivity;
import io.roll.dundjinntome.viewCharacter.ViewCharacterActivity;


public class MakeCharacterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CharInst charInst = CharInst.getInstance();
    private int[] attributes = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_character);
        Spinner classSpinner = findViewById(R.id.classSpinner);
        Spinner raceSpinner = findViewById(R.id.raceSpinner);
        Spinner strSpinner = findViewById(R.id.strSpinner);
        Spinner dexSpinner = findViewById(R.id.dexSpinner);
        Spinner conSpinner = findViewById(R.id.conSpinner);
        Spinner intSpinner = findViewById(R.id.intSpinner);
        Spinner wisSpinner = findViewById(R.id.wisSpinner);
        Spinner chaSpinner = findViewById(R.id.chaSpinner);
        EditText chaName = findViewById(R.id.editName);
        EditText exp = findViewById(R.id.editExp);
        EditText hp = findViewById(R.id.editHP);
        EditText ac = findViewById(R.id.editAC);
        Button save = findViewById(R.id.saveButton);
        Button cancel = findViewById(R.id.cancelButton);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MakeCharacterActivity.this, ViewCharacterActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                MakeCharacterActivity.this.startActivity(activityChangeIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent(MakeCharacterActivity.this, StartActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                MakeCharacterActivity.this.startActivity(activityChangeIntent);
            }
        });

        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this,
                R.array.charClass,android.R.layout.simple_spinner_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this,
                R.array.race,android.R.layout.simple_spinner_item);
        raceSpinner.setAdapter(raceAdapter);
        raceSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> strAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        strSpinner.setAdapter(strAdapter);
        strSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> dexAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        dexSpinner.setAdapter(dexAdapter);
        dexSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> conAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        conSpinner.setAdapter(conAdapter);
        conSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> intAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        intSpinner.setAdapter(intAdapter);
        intSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> wisAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        wisSpinner.setAdapter(wisAdapter);
        wisSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> chaAdapter = ArrayAdapter.createFromResource(this,
                R.array.attrLevel,android.R.layout.simple_spinner_item);
        chaSpinner.setAdapter(chaAdapter);
        chaSpinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();*/
        char[] first = parent.getItemAtPosition(position).toString().toCharArray();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
