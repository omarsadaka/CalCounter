package com.example.omar.calcounter.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omar.calcounter.Activites.FoodItemDetailsActivity;
import com.example.omar.calcounter.Model.Food;
import com.example.omar.calcounter.R;

import java.util.ArrayList;

/**
 * Created by OMAR on 11/12/2018.
 */

public class ListViewAdapter extends ArrayAdapter<Food> {
    private  int layoutResource;
    private Activity activity;
    private ArrayList<Food> arrayList = new ArrayList<> ( );

    public ListViewAdapter(@NonNull Activity act , int resource, ArrayList<Food> data) {
        super ( act, resource, data );
        layoutResource = resource;
        activity = act;
        arrayList = data;
        notifyDataSetChanged ();
    }

    @Override
    public int getCount() {
        return arrayList.size ();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return arrayList.get ( position );
    }

    @Override
    public int getPosition(@Nullable Food item) {
        return super.getPosition ( item );
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId ( position );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = convertView;
                 viewHolder holder = null;
                  if (view == null || (view.getTag () == null)){
                      LayoutInflater inflater = LayoutInflater.from ( activity );
                      view = inflater.inflate ( layoutResource , null );
                      holder = new viewHolder ();
                      holder.foodName = (TextView)view.findViewById ( R.id.foodName );
                      holder.foodCalories = (TextView)view.findViewById ( R.id.calories);
                      holder.foodDate = (TextView)view.findViewById ( R.id.foodDate);
                      view.setTag ( holder );
                  }else {
                      holder = (viewHolder)view.getTag ( );
                  }
                  holder.food = getItem ( position );
                  holder.foodName.setText ( holder.food.getFoodName () );
                  holder.foodCalories.setText (String.valueOf (  holder.food.getCalories () ) );
                  holder.foodDate.setText (  holder.food.getRecordDate () );
        final viewHolder finalHolder = holder;
        view.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (activity , FoodItemDetailsActivity.class );
                Bundle bundle = new Bundle ( );
                bundle.putSerializable ( "foodObj" , finalHolder.food );
                intent.putExtras ( bundle );
                activity.startActivity ( intent );


            }
        } );

        return view;
    }

    public class viewHolder{
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;


    }
}
