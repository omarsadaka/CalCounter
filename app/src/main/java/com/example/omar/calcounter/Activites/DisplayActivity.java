package com.example.omar.calcounter.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.omar.calcounter.Data.DatabaseHandler;
import com.example.omar.calcounter.Data.ListViewAdapter;
import com.example.omar.calcounter.Model.Food;
import com.example.omar.calcounter.R;
import com.example.omar.calcounter.Util.Utils;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ListViewAdapter adapter;
    ArrayList<Food> dbFood = new ArrayList<> ( );
    Food myfood;
    TextView totalCals , totalFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_display );

        listView = (ListView)findViewById ( R.id.listview );
        totalCals = (TextView)findViewById ( R.id.totalCals );
        totalFood = (TextView)findViewById ( R.id.totalFood );
        refreshData();
    }

    private void refreshData() {
        dbFood.clear ();
        databaseHandler = new DatabaseHandler ( this );
        ArrayList<Food> foodsFromDb = databaseHandler.getFoods ();
        int calsValue = databaseHandler.totalCalories ();
        int totalItems = databaseHandler.getTotalItems ();
        String formattedValue = Utils.FormatNumner ( calsValue );
        String formattedItems = Utils.FormatNumner ( totalItems );
        totalCals.setText ( "Total Calories :" + formattedValue );
        totalFood.setText ( "Total Food :" + formattedItems );

        for (int i = 0 ; i<foodsFromDb.size () ; i ++){
            String name = foodsFromDb.get ( i ).getFoodName ();
            String dateText = foodsFromDb.get ( i ).getRecordDate ();
            int cals = foodsFromDb.get ( i ).getCalories ();
            int foodID = foodsFromDb.get ( i ).getFoodId ();

            Log.e ( "Food ID" , String.valueOf ( foodID ) );
            myfood = new Food (  );
            myfood.setFoodName ( name );
            myfood.setCalories ( cals );
            myfood.setRecordDate ( dateText );
            myfood.setFoodId ( foodID );
            dbFood.add ( myfood );
        }
        databaseHandler.close ();
        adapter = new ListViewAdapter ( DisplayActivity.this , R.layout.item_row , dbFood );
        listView.setAdapter ( adapter );
        adapter.notifyDataSetChanged ();

    }
}
