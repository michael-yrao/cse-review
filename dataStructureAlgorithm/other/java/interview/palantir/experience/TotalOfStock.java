package review.interview.palantir.experience;

import java.util.*;

public class TotalOfStock
{
    /*
    * Given a List of Map:
    *               5/1     5/5     5/8
    * Palantir     100      200     null
    * Microsoft    null     100     50
    * Google       150      null    100
    *
    * Return the total of each day's stock prices as a Map
    *
    * Example input: [ [<5/1,100>,<5/5,200>], [<5/5,200>,<5/8,50>], [<5/1,150>,<5/8,100>]
    * Expected output: [<5/1,250>,<5/5,450>,<5/8,350>]
    *
    * */


    /*
     * 1. Get list of distinct dates
     * 2. Populate maps with the missing dates with null values
     * 3. Make dates comparable
     * 4. Compare dates and populate null values with true values
     * 5. Sum up for return map
     * */

    public Map<String,Integer> totalOfStock(List<Map<String,Integer>> listOfMap)
    {

        HashMap<String,Integer> stringDateToIntDate = new HashMap<>();

        for(Map<String,Integer> map : listOfMap)
        {
            for(Map.Entry<String,Integer> entry : map.entrySet())
            {
                // This provides us a nice and easily accessible map from the initial String date to int date
                stringDateToIntDate.put(entry.getKey(),convertStringDateToComparableInt(entry.getKey()));
            }
        }

        // Put everything into the new list that can be traversed properly

        for(Map<String,Integer> map : listOfMap)
        {
            for(Map.Entry<String,Integer> entry : stringDateToIntDate.entrySet())
            {
                if(!map.containsKey(entry.getKey()))
                {
                    List<Integer> sortedList = new ArrayList<>(stringDateToIntDate.values());
                    map.put(entry.getKey(),populateMissingValue(sortedList, entry.getValue()));
                }
            }
        }

        Map<String,Integer> returnMap = new HashMap<>();

        for(Map<String,Integer> map : listOfMap)
        {
            for(Map.Entry<String,Integer> entry : map.entrySet())
            {
                returnMap.put(entry.getKey(),returnMap.getOrDefault(entry.getValue(),0)+entry.getValue());
            }
        }
        return returnMap;

    }

    public int convertStringDateToComparableInt(String date)
    {
        return Integer.parseInt(date.split("/")[0])*31 + Integer.parseInt(date.split("/")[1]);
    }

    public int populateMissingValue(List<Integer> sortedList, int target)
    {
        int left=0, right=sortedList.size()-1;
        while(right>=left)
        {
            int middle = (right+left)/2;
            if(sortedList.get(middle) == target)
            {
                if(middle>0) return sortedList.get(middle-1);
                else return 0;
            }
            else if(sortedList.get(middle) > target) left=middle+1;
            else right=middle-1;
        }
        return -1;
    }
}
