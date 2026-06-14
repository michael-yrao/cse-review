package review.interview.palantir.leetcodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals_56_M_INTERVALS
{
    /*
    * https://leetcode.com/problems/merge-intervals/
    * */


    /*
    * So key here is to identify whether or not two intervals are overlapping
    * One thing it seems we can do is start[i-1] < start[i] && end[i-1] >= start[i]
    * This relies on the fact that the array is sorted by start time, which it seems to be the case in the problem
    *
    * But to ensure this is the case, what we should do is convert these intervals to Pairs and use a minHeap based on start time
    * */

    public int[][] mergeIntervals(int[][] intervals)
    {
        // Sort by start value
        Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));

        // Create the result list, we'll use an arraylist of int[] since we don't know end size
        ArrayList<int[]> ans = new ArrayList<>();

        // We'll insert the first interval into the result first so we can use it as a base for the rest of our intervals
        ans.add(new int[]{intervals[0][0],intervals[0][1]});

        for(int i=1;i<intervals.length;i++)
        {
            int prevStart = ans.get(ans.size()-1)[0];
            int prevEnd = ans.get(ans.size()-1)[1];
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // Don't need to compare starting since we are sorted
            if(currentStart <= prevEnd)
            {
                // Given intervals [1,5] and [2,4]
                // We need to take the max of these two ends and not just the current end time
                int[] newInterval = new int[]{prevStart,Math.max(prevEnd,currentEnd)};
                ans.remove(ans.size()-1);
                ans.add(newInterval);
            }
            else ans.add(new int[]{intervals[i][0], intervals[i][1]});
        }
        int[][] result = new int[ans.size()][2];
        ans.toArray(result);
        return result;
    }
}
