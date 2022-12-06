package review.interview.bloomberg;

import java.util.List;

public class findMinInArray
{
    // Bloomberg Fixed Income (June 2022)
    // Given a list that traverses down and up
    // e.g. [100, 50, 30, 20, 10, 25, 35]
    // Find a specific value in list and return position
    // Very similar to https://leetcode.com/problems/search-in-rotated-sorted-array/

    public int findMinInArray(int[] inputList, int left, int right)
    {
        int mid = (right-left)/2;

        if(inputList[mid] < inputList[mid-1] && inputList[mid] < inputList[mid+1]) // Return value
        {
            return mid;
        }
        else if (inputList[mid] < inputList[mid-1] && inputList[mid] > inputList[mid+1]) // Traverse right
        {
            return mid + findMinInArray(inputList,mid,inputList.length);
        }
        else if (inputList[mid] > inputList[mid-1] && inputList[mid] < inputList[mid+1]) // Traverse left
        {
            return findMinInArray(inputList,0,mid);
        }

        return -1;
    }

    public int findMinInArray(List<Integer> inputList)
    {
        int mid = inputList.size()/2;

        if(inputList.get(mid) < inputList.get(mid-1) && inputList.get(mid) < inputList.get(mid+1)) // Return value
        {
            return mid;
        }
        else if (inputList.get(mid) < inputList.get(mid-1) && inputList.get(mid) > inputList.get(mid+1)) // Traverse right
        {
            return mid + findMinInArray(inputList.subList(mid,inputList.size()));
        }
        else if (inputList.get(mid) > inputList.get(mid-1) && inputList.get(mid) < inputList.get(mid+1)) // Traverse right
        {
            return findMinInArray(inputList.subList(0,mid));
        }

        return -1;
    }
}
