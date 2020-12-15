package algo.greedy;

//import javafx.beans.binding.ObjectExpression;

import java.util.Arrays;
import java.util.Comparator;

public class FractionKnapsack {

    /*
    this inner class holds the weight, value, index and calculate cost by value/weight
     */
    public static class ItemValue {
        int wt;
        int val;
        int index;
        Double cost;

        public ItemValue(int w, int v, int i) {
            wt = w;
            val = v;
            index = i;
            cost = new Double(val/wt);
        }
    }

    /*
    Returns the maximum value, this is the main function
    */
    public static double getMaxVal(int[] wt, int[] val, int capacity){
        ItemValue[] itemValues = new ItemValue[wt.length];
        // form the arrary with weight, value and index
        for(int i = 0; i < wt.length; i++){
            itemValues[i] = new ItemValue(wt[i], val[i], i);
        }

        // sort the array with cost in descending order
        Arrays.sort(itemValues, new Comparator<ItemValue>(){
            @Override
            public int compare(ItemValue ob1, ItemValue ob2){
                return ob2.cost.compareTo(ob1.cost);
            }
        });

        int totalValue = 0;
        // get the total value
        for(ItemValue i: itemValues){
            int curWt = (int) i.wt;
            int curVal = (int) i.val;
            if(capacity - curWt > 0){
                totalValue += curVal;
                capacity -= curWt;
            }else{
                double fraction = (double)(capacity)/(double)curWt;
                totalValue += (curVal * fraction);
                capacity -= (int)(capacity * fraction);
                break;
            }
        }
        return totalValue;
    }
    /*
    this is the driver program
    wt = is the weight of that the knapsack can hold
    val = value corresponding to each weight
    capacity = total capacity of the knapsack
     */
    public static void main(String[] args){
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double value = getMaxVal(wt, val, capacity);
        System.out.println("Total value: " +value);
        return;
    }
}