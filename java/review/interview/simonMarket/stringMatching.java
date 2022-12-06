package review.interview.simonMarket;

public class stringMatching
{

    // Given Two Strings Needle and Hay where Needle can have an * in it
    // Return the index where the Needle appears in Hay
    // Return -1 if it does not appear in the Hay

    /*
    * Using the Boyer Moore Algorithm, we can have a solution that works for all scenarios
    * However, since the question has a restriction of lowercase alphabets and only one asterisk,
    * We can do a solution that is slightly less efficient but significantly easier to write.
    *
    * Boyer Moore Algorithm solution is also included here
    *
    * */

    public int stringMatchingPermutation(String hayStack, String needle)
    {
        String[] permutations = needlePermutation(needle);

        int maxIndex = -1;

        // We are looking for min index but since indexOf always gets us minimum anyways, we need a way to ensure
        // We are taking into consideration that it returns -1 when it is not found. Thus we want to run Math.max
        // Instead of Math.min

        for(int i=0;i<permutations.length;i++)
        {
            maxIndex = Math.max(maxIndex,hayStack.indexOf(permutations[i]));
        }
        return maxIndex;
    }

    // Since we know this has only lowercase AND there is only 1 asterisk at the needle

    public String[] needlePermutation(String needle)
    {
        // If no asterisk, we can just return the needle as is in a size 1 array
        if(!needle.contains("*")) return new String[]{needle};

        // Otherwise change * to all lowercase characters
        String[] returnArray = new String[26];

        for(int i=0;i<returnArray.length;i++)
        {
            char replaceCharacter = (char)('a'+i);
            returnArray[i] = needle.replace('*',replaceCharacter);
        }
        return returnArray;
    }

    public int stringMatchingBoyerMoore (String hayStack, String needle)
    {
        int hayStackSize = hayStack.length();
        int needleSize = needle.length();

        if(needleSize==0) return 0;
        if(needleSize>hayStackSize) return -1;

        int hayStackIndex = needleSize-1;  // haystack index
        int needleIndex = needleSize-1;  // needle index

        while(hayStackIndex < hayStackSize)
        {
            if(hayStack.charAt(hayStackIndex) == needle.charAt(needleIndex) || needle.charAt(needleIndex) == '*')
            {
                if(needleIndex==0) return hayStackIndex;
                hayStackIndex--;
                needleIndex--;
            }
            else
            {
                hayStackIndex += 1 + needleIndex - Math.min(needleIndex,1+firstSeen(hayStack.charAt(hayStackIndex),hayStack));
                needleIndex = needleSize-1;
            }
        }
        return -1;
    }

    public int firstSeen(Character character, String hay)
    {
        for(int i=0;i<hay.length();i++)
        {
            if(hay.charAt(i)==character || character=='*') return i;
        }
        return -1;
    }

}
