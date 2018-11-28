package com.example.omar.calcounter.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omar.calcounter.Data.DatabaseHandler;
import com.example.omar.calcounter.Model.Food;
import com.example.omar.calcounter.R;

public class FoodItemDetailsActivity extends AppCompatActivity {
    TextView foodName , calories , foodDate;
    Button share;
    Button delete;
    int foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_food_item_details );

        foodName = (TextView)findViewById ( R.id.detlsFoodName );
        calories = (TextView)findViewById ( R.id.detlsCaloriesValue );
        foodDate = (TextView)findViewById ( R.id.detlsDateText );
        share = (Button)findViewById ( R.id.shareButton );
        delete = (Button)findViewById ( R.id.deleteButton );

        Food food = (Food) getIntent ().getSerializableExtra ( "foodObj" );
        foodName.setText ( food.getFoodName () );
        calories.setText (String.valueOf (  food.getCalories () ) );
        foodDate.setText ( food.getRecordDate () );

        foodID = food.getFoodId ();

        calories.setTextSize ( 35.9f );
        calories.setTextColor ( Color.RED );

        share.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                shareCals ();

            }
        } );

        delete.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder ( FoodItemDetailsActivity.this );
                alert.setTitle ( "Delete ?" );
                alert.setMessage ( "Are You Sure You Want To Delete This Item?" );
                alert.setNegativeButton ( "No" , null );
                alert.setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler dba = new DatabaseHandler ( getApplicationContext () );
                        dba.deleteFood ( foodID );

                        Toast.makeText ( getApplicationContext ( ), "Food Item Deleted Succesfully!", Toast.LENGTH_SHORT ).show ( );
                        startActivity ( new Intent ( FoodItemDetailsActivity.this , DisplayActivity.class ) );
                        FoodItemDetailsActivity.this.finish ();
                    }
                } );
                alert.show ();

            }
        } );
    }


    public void shareCals(){

        StringBuilder builder = new StringBuilder ( );
        String name = foodName.getText ().toString ();
        String cals = calories.getText ().toString ();
        String date = foodDate.getText ().toString ();

        builder.append ( " Food : " + name + "\n" );
        builder.append ( " Calories : "+ cals +"\n" );
        builder.append ( " Eaten On : " + date );

        Intent sharingIntent = new Intent ( Intent.ACTION_SEND );
        sharingIntent.setType ( "text/plain" );
        sharingIntent.putExtra ( Intent.EXTRA_SUBJECT, "My Calories Intake" );
        sharingIntent.putExtra ( Intent.EXTRA_TEXT,  builder.toString () );
        startActivity ( Intent.createChooser ( sharingIntent ,"Sharing By" ));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.my_menu , menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        if (id == R.id.delete){
            AlertDialog.Builder alert = new AlertDialog.Builder ( this );
            alert.setTitle ( "Delete ?" );
            alert.setMessage ( "Are You Sure You Want To Delete This Item?" );
            alert.setNegativeButton ( "No" , null );
            alert.setPositiveButton ( "Yes", new DialogInterface.OnClickListener ( ) {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHandler dba = new DatabaseHandler ( getApplicationContext () );
                    dba.deleteFood ( foodID );

                    Toast.makeText ( getApplicationContext ( ), "Food Item Deleted Succesfully!", Toast.LENGTH_SHORT ).show ( );
                    startActivity ( new Intent ( FoodItemDetailsActivity.this , DisplayActivity.class ) );
                    FoodItemDetailsActivity.this.finish ();
                }
            } );
            alert.show ();


        }
        return super.onOptionsItemSelected ( item );
    }
}
