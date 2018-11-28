package com.example.omar.calcounter.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omar.calcounter.Data.DatabaseHandler;
import com.example.omar.calcounter.Model.Food;
import com.example.omar.calcounter.R;

public class MainActivity extends AppCompatActivity {

    EditText foodName , foodCals;
    Button submit;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

         databaseHandler = new DatabaseHandler ( this );
         foodName = (EditText)findViewById ( R.id.foodEditText );
         foodCals = (EditText)findViewById ( R.id.caloriesEditText );
         submit = (Button)findViewById ( R.id.submitButton );
         submit.setOnClickListener ( new View.OnClickListener ( ) {
             @Override
             public void onClick(View v) {
                 saveDataToDB();
             }


         } );

    }

    private void saveDataToDB() {
        Food food = new Food ( );
        String name = foodName.getText ().toString ().trim ();
        String calsString = foodCals.getText ().toString ().trim ();

        if (name.isEmpty () || calsString.isEmpty ()){
            Toast.makeText ( this, "No Empity Field Allowed", Toast.LENGTH_SHORT ).show ( );
        }else {
            int Cals = Integer.parseInt ( calsString );
            food.setFoodName ( name );
            food.setCalories ( Cals );

            databaseHandler.addFood ( food );
            databaseHandler.close ();
            foodName.setText ( "" );
            foodCals.setText ( "" );
            startActivity ( new Intent ( MainActivity.this  , DisplayActivity.class) );
        }


    }

}
