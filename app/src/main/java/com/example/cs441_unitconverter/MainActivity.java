package com.example.cs441_unitconverter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Spinner unitSpinner = findViewById(R.id.inputUnitsSpinner);
        final ArrayAdapter<CharSequence> unitSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.unitsToConvert, R.layout.support_simple_spinner_dropdown_item);
        unitSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        unitSpinner.setAdapter(unitSpinnerAdapter);
        unitSpinner.setOnItemSelectedListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.inputValue);
                Double val = Double.parseDouble(input.getText().toString());
                Double stdVal = val;
                String unit = unitSpinner.getSelectedItem().toString();
                if(unit.length() == 2) {
                    if(unit.charAt(0) == 'm') {
                        stdVal = val / 1000;
                    }
                    else if(unit.charAt(0) == 'k') {
                        stdVal = val * 1000;
                    }
                }
                LinearLayout outputTable = findViewById(R.id.outputTable);
                LinearLayout tempLayout = (LinearLayout)outputTable.getChildAt(0);
                TextView tempView = (TextView)tempLayout.getChildAt(0);
                tempView.setText(Double.toString(stdVal / 1000));
                tempLayout = (LinearLayout)outputTable.getChildAt(1);
                tempView = (TextView)tempLayout.getChildAt(0);
                tempView.setText(Double.toString(stdVal));
                tempLayout = (LinearLayout)outputTable.getChildAt(2);
                tempView = (TextView)tempLayout.getChildAt(0);
                tempView.setText(Double.toString(stdVal * 1000));S
            }
        });
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
