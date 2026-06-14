package review.interview.palantir.experience;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindWordsInString_ROUND2_CODING
{
    /*
    * Given: input is a space separated String
    *        query is a string with 2 words separated by space
    *        k is the number of spaces it is allowed to be different between w1 and w2 of query
    * */
    public int[] findWordsMapSolution(String input, String query, int k)
    {
        Map<String, List<Integer>> map = new HashMap<>();
        String[] inputArray = input.split(" ");
        String firstWord = query.split(" ")[0];
        String secondWord = query.split(" ")[1];
        for(int i=0;i<inputArray.length;i++)
        {
            map.putIfAbsent(inputArray[i], new ArrayList<>());
            map.get(inputArray[i]).add(i);
        }

        List<Integer> firstWordOccurence = map.get(firstWord);
        List<Integer> secondWordOccurence = map.get(secondWord);

        List<Integer> result = new ArrayList<>();

        int firstWordPointer = 0;
        int secondWordPointer = 1;

        while(firstWordPointer < firstWordOccurence.size() && secondWordPointer < secondWordOccurence.size())
        {
            if(Math.abs(firstWordPointer - secondWordPointer) != 0 && Math.abs(firstWordPointer - secondWordPointer) <= k)
            {
                result.add(firstWordPointer);
                firstWordPointer++;
            }
            else if(secondWordPointer - firstWordPointer <= 0)
            {
                secondWordPointer++;
            }
            else if(secondWordPointer - firstWordPointer > k)
            {
                firstWordPointer++;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public int[] findWordsSlidingWindow(String input, String query, int k)
    {
        Map<String, List<Integer>> map = new HashMap<>();
        String[] inputArray = input.split(" ");
        String firstWord = query.split(" ")[0];
        String secondWord = query.split(" ")[1];

        List<Integer> result = new ArrayList<>();

        int left=0;
        int right=1;

        while(left < inputArray.length && right < inputArray.length)
        {
            if(inputArray[left].equals(firstWord) && inputArray[right].equals(secondWord) && right - left <= k && right - left > 0)
            {
                result.add(left);
                left++;
            }
            else if(inputArray[left].equals(firstWord) && inputArray[right].equals(secondWord) && right - left <= 0
                    || inputArray[left].equals(firstWord) && !inputArray[right].equals(secondWord))
                right++;
            else if(inputArray[left].equals(firstWord) && inputArray[right].equals(secondWord) && right - left > k
                    || !inputArray[left].equals(firstWord) && inputArray[right].equals(secondWord))
                left++;
            else
            {
                left++;
                right++;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
