package review.interview.flowTraders;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class distinctCharactersCount
{

    /*
     * Create the Filter and Mapper classes here.
     */

    static class Filter
    {
        public static Predicate<String> nameStartingWithPrefix(String name)
        {
            return p -> p.startsWith(name);
        }
    }

    class Mapper
    {
        public static Function<String,String> getDistinctCharactersCount()
        {
            return p -> '"' + p + "\" has " + Long.toString(p.chars().distinct().count()) + " distinct characters.";
        }
    }

    class CharactersCount {
        private final String name;
        private final Integer distinctCharacterCount;

        public CharactersCount(String name, Integer distinctCharacterCount) {
            this.name = name;
            this.distinctCharacterCount = distinctCharacterCount;
        }

        @Override
        public String toString() {
            return "\"" + this.name + "\" has " + this.distinctCharacterCount + " distinct characters.";
        }
    }

    public class Solution {
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            List<String> names = Arrays.asList(
                    "aaryanna",
                    "aayanna",
                    "airianna",
                    "alassandra",
                    "allanna",
                    "allannah",
                    "allessandra",
                    "allianna",
                    "allyanna",
                    "anastaisa",
                    "anastashia",
                    "anastasia",
                    "annabella",
                    "annabelle",
                    "annebelle"
            );

            names.stream()
                    .filter(Filter.nameStartingWithPrefix(scanner.nextLine()))
                    .map(Mapper.getDistinctCharactersCount())
                    .forEachOrdered(System.out::println);
        }
    }
}
