package com.example.omar.calcounter.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.omar.calcounter.Model.Food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by OMAR on 11/12/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private final ArrayList<Food> foodList = new ArrayList<> ( );
    public DatabaseHandler(Context context) {
        super ( context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERTION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KAY_ID + " INTEGER PRIMARY KEY," + Constants.FOOD_NAME + " TEXT, "
                + Constants.FOOD_CALORIES_NAME + " INT,"
                + Constants.DATE_NAME + " LONG);";
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);

    }

    public int getTotalItems(){
        int totalItems = 0;
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery , null);
        totalItems = cursor.getCount ();
        cursor.close ();
        return totalItems;
    }

    public int totalCalories(){
        int cals = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM ( " +Constants.FOOD_CALORIES_NAME + " ) " + "FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        if (cursor.moveToNext ()){
            cals = cursor.getInt ( 0 );
        }
        cursor.close ();
        db.close ();
        return  cals;
    }
    public void deleteFood(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME , Constants.KAY_ID + "=?" ,new String[]{String.valueOf(id)});
        db.close();
    }

    public void addFood(Food food){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.FOOD_NAME , food.getFoodName ());
        values.put(Constants.FOOD_CALORIES_NAME , food.getCalories ());
        values.put(Constants.DATE_NAME , java.lang.System.currentTimeMillis());
        db.insert(Constants.TABLE_NAME , null , values);
        Log.d("Added Food Item!" , "Yes");
        db.close ();
    }

    public ArrayList<Food> getFoods(){
        foodList.clear ();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME , new String[]{Constants.KAY_ID,Constants.FOOD_NAME ,
                        Constants.FOOD_CALORIES_NAME ,Constants.DATE_NAME},
                null,null,null,null,Constants.DATE_NAME + " DESC");
        if (cursor.moveToFirst()){
            do {


                Food food = new Food ();
                food.setFoodId (Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KAY_ID))));
                food.setFoodName (cursor.getString(cursor.getColumnIndex(Constants.FOOD_NAME)));
                food.setCalories (cursor.getInt (cursor.getColumnIndex(Constants.FOOD_CALORIES_NAME)));

                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formatDate = dateFormat.format(new Date (cursor.getLong(cursor.getColumnIndex(Constants.DATE_NAME))).getTime());
                food.setRecordDate (formatDate);
                foodList.add(food);
            }while (cursor.moveToNext());
        }
            cursor.close ();
        db.close ();

        return foodList;
    }

}
