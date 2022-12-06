package interview;

import org.junit.jupiter.api.Test;
import review.interview.audible.experience.MinimumNumberOfMovesToMakePalindrome;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class minSwapPalindrome
{
    MinimumNumberOfMovesToMakePalindrome minSwaps = new MinimumNumberOfMovesToMakePalindrome();

    @Test
    public void test1()
    {
        String testString = "100010";
        assertEquals(1,minSwaps.minSwapsRequired(testString));
    }
}
