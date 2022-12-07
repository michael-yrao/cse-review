package review.interview.google;

import java.util.*;

public class MinimumInArray
{
    /*
    * Given an array and a window size k
    * Return a minimum array of size n-k
    * */


    /*
    * Not tested but general idea is coded below
    * */

    public List<Integer> minList(int[] numbers, int windowSize)
    {
        int left=0;
        int right=0;
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        List<Integer> result = new ArrayList<>();

        while(right<numbers.length)
        {
            AbstractMap.SimpleEntry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(right,numbers[right]);
            heap.offer(pair);

            // right - left gives current window size, which can be greater than windowSize

            while(heap.peek().getKey() < (right-left-windowSize))
            {
                heap.poll();
                left++;
            }
            result.add(heap.peek().getValue());
            right++;
        }
        return result;
    }
}
