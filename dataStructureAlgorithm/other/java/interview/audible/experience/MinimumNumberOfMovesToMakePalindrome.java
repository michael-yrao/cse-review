package review.interview.audible.experience;

public class MinimumNumberOfMovesToMakePalindrome
{
    /*
    * Given a string
    * return min number of swaps needed to make the string into a palindrome
    * moves are not restricted to adjacent moves
    * e.g. 1010 -> 1001 requires 1 move only
    *
    * 1 -> 1 ; 0
    * 10 -> 10 ; -1
    * 101 -> 101 ; 0
    * 110 -> 101 ; 1
    *
    * */

    /*
    * Solution explained at https://devsolus.com/2022/04/26/minimum-number-of-swaps-required-to-make-a-binary-string-palindrome/
    * */

    public int minSwapsRequired(String s)
    {
        char[] charArray = s.toCharArray();
        int length = s.length();
        int count=0;

        for(int i=0;i<charArray.length/2;i++)
        {
            if(charArray[i] != charArray[length-i-1]) count++;
        }
        if(count%2==1 && length%2==0) return -1;
        return (count+1)/2;
    }
}
