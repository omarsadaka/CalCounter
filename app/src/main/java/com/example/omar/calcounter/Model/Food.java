package com.example.omar.calcounter.Model;

import java.io.Serializable;

/**
 * Created by OMAR on 11/12/2018.
 */

public class Food implements Serializable {
    private static final long SerialVirtionUID = 10L;
    private String foodName;
    private int calories;
    private int foodId;
    private String recordDate;

    public Food(String foodName, int calories, int foodId, String recordDate) {
        this.foodName = foodName;
        this.calories = calories;
        this.foodId = foodId;
        this.recordDate = recordDate;
    }

    public Food() {
    }

    public static long getSerialVirtionUID() {
        return SerialVirtionUID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
