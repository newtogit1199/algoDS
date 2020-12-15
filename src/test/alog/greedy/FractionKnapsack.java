package test.alog.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionKnapsack {

    static public class ItemValue{
        int wt;
        int val;
        int index;
        Double cost;

        /**
         * this is the constructor
         * @param w correspond to weight
         * @param v correspond to value
         * @param i correspond to index
         */
        ItemValue(int w, int v, int i){
            this.wt = w;
            this.val = v;
            this.index = i;
            cost = (double)val/wt;
        }
    }

    public static double getMaxValue(int[] wt, int[] val, int capacity){
        // Creating an array which will keep wt, value and index in it and then will calculate cost = val/wt
        ItemValue[] itemValues = new ItemValue[wt.length];
        for(int i = 0; i < wt.length; i++){
            itemValues[i] = new ItemValue(wt[i], val[i], i);
        }

        // sort the array based on the cost in the itemValues
        Arrays.sort(itemValues, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        double totalValue = 0;
        // now following is the loop to figure out the total value
        for(ItemValue i:itemValues){
            int curWt = (int) i.wt;
            int curVal = (int) i.val;
            if( (capacity - curWt) > 0){
                capacity -= curWt;
                totalValue += curVal;
            }else{
                double fraction = (double)capacity/curWt; // calculate the fraction = capacity/curWt
                capacity -= (int)capacity*fraction; // calculate the remaining capacity
                totalValue += curVal*fraction; // calculate the total value
                break; // break to go out of the loop
            }
        }// end of for

        return totalValue;
    }

    /**
     * This is driver method
     * @param args
     * displays total value
     */
    public static void main(String[] args){
        int[ ] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double value = getMaxValue(wt, val, capacity);
        System.out.println("Total value: " +value);
        return;
    }
}
