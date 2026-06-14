package review.interview.audible.experience;

import DataStructure.Pair;

import java.util.Arrays;

public class RankImbalance
{
    /*
    * Given an array
    * Find the number of imbalances
    * e.g. [4,1,3,2]
    * [4,1] -> 1
    * [1,3] -> 1
    * [3,2] -> 0
    * [4,1,3] -> 1
    * [1,3,2] -> 0
    * [4,1,3,2] -> 0
    * Result = 3
    * */

    /*
    * If rank is length 4, rank will have 1,2,3,4 but not necessarily sorted
    *
    * What this tells me is rank at its max length will never have an imbalance
    * Same with ranks with length of 1
    *
    * Wrote an extremely inefficient algorithm below with O(logn * n^3) complexity
    * There must be a simpler solution, I am unable to see it at the moment
    *
    * */
    public int findImbalance(int[] rank)
    {
        int count=0;
        for(int i=0;i<rank.length;i++)
        {
            for(int j=i+1;j<rank.length;j++)
            {
                int[] copy = getContiguousSubarray(rank,i,j);
                if(!consecutive(copy)) count++;
            }
        }
        return count;
    }

    /*
    * copy of range is O(n) operation, so this will be quite the inefficient algorithm
    * */
    private int[] getContiguousSubarray(int[] rank, int start, int end)
    {
        return Arrays.copyOfRange(rank,start,end);
    }

    private boolean consecutive(int[] array)
    {
        Arrays.sort(array);
        for(int i=1;i<array.length;i++)
        {
            if(array[i] != array[i-1] + 1) return false;
        }
        return true;
    }
}
