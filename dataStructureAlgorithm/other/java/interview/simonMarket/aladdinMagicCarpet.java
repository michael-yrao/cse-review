package review.interview.simonMarket;

import java.util.List;

public class aladdinMagicCarpet
{
    /*
    * https://leetcode.com/discuss/interview-question/406135/ibm-oa-2019-aladdin-and-his-carpet-backend
    * */

    public static int optimalPoint(List<Integer> magic, List<Integer> dist)
    {
        int startingIndex = 0;
        int magicPoint = 0;
        int difference = 0;

        // Greedy Rule: Start with highest net (e.g. magic - dist)


        // If total net is < 0, we can immediately return -1 since it is impossible to complete

        if(magic.stream().mapToInt(Integer::intValue).sum()
                < dist.stream().mapToInt(Integer::intValue).sum())
            return -1;

        // If net < 0, reset net

        for(int i=0;i<magic.size();i++)
        {
            magicPoint += magic.get(i) - dist.get(i);

            if (magicPoint < 0)
            {
                startingIndex = i+1;
                difference+=magicPoint;
                magicPoint=0;
            }
        }

        return difference+magicPoint>=0 ? startingIndex : -1;

    }
}
