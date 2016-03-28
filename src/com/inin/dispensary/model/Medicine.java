package com.inin.dispensary.model;

/**
 * Created by sangam on 28/3/16.
 */
public class Medicine {
    String name;
    int quantity = 0;
    boolean beforeFood = false;
    int timeDiff = 0;

    /**
     * store medicines
     * @param name name of medicine
     * @param quantity quantity of medicine
     * @param beforeFood before food or after food
     * @param timeDiff how much time before / after food
     */
    public Medicine(String name, int quantity, boolean beforeFood, int timeDiff)
    {
        this.name = name;
        this.quantity = quantity;
        this.beforeFood = beforeFood;
        this.timeDiff = timeDiff;
    }

    /**
     *
     * @param name
     * @param quantity
     * @param beforeFood
     * @param timeDiff
     * @return
     */
    public static Medicine create(String name, int quantity, boolean beforeFood, int timeDiff) {
        return new Medicine(name, quantity, beforeFood, timeDiff);
    }
}
