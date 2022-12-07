package review.interview.simonMarket;

import java.util.Arrays;
import java.util.List;

public class preprocessDates
{

    /*
    * https://leetcode.com/problems/reformat-date/
    * */

    public static String preprocessDateHelper(String date)
    {

        // splitDate[0] == Day
        // splitDate[1] == Month
        // splitDate[2] == Year

        String[] splitDate = date.split(" ");

        String day = splitDate[0].replaceAll("\\D", ""); // \\D == non-digits

        String[] monthStringArray = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        List<String> monthIndices = Arrays.asList(monthStringArray);

        int month = monthIndices.indexOf(splitDate[1]);

        return splitDate[2] + "-" + String.format("%02d", month) + "-" + String.format("%02d", Integer.parseInt(day));

    }
}
