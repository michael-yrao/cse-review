package review.interview.simonMarket;

import java.util.*;

public class universityCareerFair
{

    /*
    * https://leetcode.com/discuss/interview-question/374846/Twitter-or-OA-2019-or-University-Career-Fair
    * */

    /*
    * Interval Scheduling Algorithm
    * */

    public static int maxEvents(List<Integer> arrival, List<Integer> duration)
    {
        int[][] events = new int[arrival.size()][2];

        // Put all events into an Array of Start Time and Finish Time

        for(int i=0;i<arrival.size();i++)
        {
            events[i] = new int[] {arrival.get(i), arrival.get(i) + duration.get(i)};
        }

        // Sort by earliest Finish Time

        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        // Use a Min Heap to keep track of all the rejects

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));

        // firstEvent (Arrival, Finish) is initialized as the event with the earliest finish time

        int[] firstEvent = events[0];

        // Going through the rest of the events
        // Remember that events is already sorted by finish time ascending
        // Therefore if Start Time of Current Event is less than Finish Time of First Event, it is a reject, thus add to heap
        // If Start Time of Current Time is greater than end time of First Event, that means it is a perfect candidate
        // For the Greedy algorithm since it is the next smallest Finish Time
        // Update the firstEvent's Finish time to the Current Time's Finish Time since those events are now set in stone

        for(int i=1;i<events.length;i++)
        {
            int[] currentEvent = events[i];
            if(currentEvent[0] < firstEvent[1])
            {
                minHeap.offer(currentEvent);
            }
            else
            {
                firstEvent[1] = events[i][1];
            }
        }

        // Returns Total number of events minus the rejects

        return arrival.size() - minHeap.size();

    }
}
