package review.interview.orum;

import org.junit.jupiter.api.Test;

import java.math.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProratingSubscriptions
{

    class Subscription {
        public Subscription() {}
        public Subscription(int id, int customerId, int monthlyPriceInCents) {
            this.id = id;
            this.customerId = customerId;
            this.monthlyPriceInCents = monthlyPriceInCents;
        }

        public int id;
        public int customerId;
        public int monthlyPriceInCents;
    }

    class User {
        public User() {}
        public User(int id, String name, LocalDate activatedOn, LocalDate deactivatedOn, int customerId) {
            this.id = id;
            this.name = name;
            this.activatedOn = activatedOn;
            this.deactivatedOn = deactivatedOn;
            this.customerId = customerId;
        }

        public int id;
        public String name;
        public LocalDate activatedOn;
        public LocalDate deactivatedOn;
        public int customerId;
    }

    class Challenge
    {
        /// Computes the monthly charge for a given subscription.
        ///
        /// @returns The total monthly bill for the customer in cents, rounded
        /// to the nearest cent. For example, a bill of $20.00 should return 2000.
        /// If there are no active users or the subscription is null, returns 0.
        ///
        /// @param month - Always present
        ///   Has the following structure:
        ///   "2022-04"  // April 2022 in YYYY-MM format
        ///
        /// @param subscription - May be null
        ///   If present, has the following structure (see Subscription class):
        ///   {
        ///     Id: 763,
        ///     CustomerId: 328,
        ///     MonthlyPriceInCents: 359  // price per active user per month
        ///   }
        ///
        /// @param users - May be empty, but not null
        ///   Has the following structure (see User class):
        ///   [
        ///     {
        ///       id: 1,
        ///       name: "Employee #1",
        ///       customerId: 1,
        ///
        ///       // when this user started
        ///       activatedOn: new Date("2021-11-04"),
        ///
        ///       // last day to bill for user
        ///       // should bill up to and including this date
        ///       // since user had some access on this date
        ///       deactivatedOn: new Date("2022-04-10")
        ///     },
        ///     {
        ///       id: 2,
        ///       name: "Employee #2",
        ///       customerId: 1,
        ///
        ///       // when this user started
        ///       activatedOn: new Date("2021-12-04"),
        ///
        ///       // hasn't been deactivated yet
        ///       deactivatedOn: null
        ///     },
        ///   ]
        public static int monthlyCharge(String month, Subscription subscription, User[] users)
        {
            int totalCharge=0;
            LocalDate inputMonth = stringtoDate(month);
            for(int i=0;i<users.length;i++)
            {
                totalCharge+=individualCharges(inputMonth, users[i], subscription);
            }
            return totalCharge;
        }

        /*******************
         * Helper functions *
         *******************/

        /**
         *
         * individualCharges will take care of single person charges
         * we will then just call this for each user to get solution
         *
         */

        private static int individualCharges(LocalDate inputMonth, User user, Subscription subscription)
        {
            // base cases
            // already deactivated, thus nothing to be returned
            // user hasn't started subscription yet for our input month

            if(subscription.customerId != user.customerId
            || (user.deactivatedOn != null && user.deactivatedOn.isBefore(inputMonth))
            || lastDayOfMonth(user.activatedOn).isAfter(inputMonth)) return 0;

            // cases where we want to charge full amount
            // activated on first day of this month or activation date is before input month

            if(firstDayOfMonth(user.activatedOn).equals(inputMonth)
                || lastDayOfMonth(user.activatedOn).isBefore(inputMonth))
                return subscription.monthlyPriceInCents;

            // charge prorated

            YearMonth inputYearMonth = YearMonth.of(inputMonth.getYear(),inputMonth.getMonthValue());
            int numberOfDaysInMonth = inputYearMonth.lengthOfMonth();

            int chargePerDay = subscription.monthlyPriceInCents / numberOfDaysInMonth;
            int daysActive;

            // Three cases
            // activation
            // deactivation
            // activation/deactivation in same month
            if(user.deactivatedOn != null && lastDayOfMonth(user.activatedOn).equals(lastDayOfMonth(user.deactivatedOn)))
            {
                daysActive = user.deactivatedOn.getDayOfMonth() - user.activatedOn.getDayOfMonth();
            }
            else if((user.deactivatedOn == null && firstDayOfMonth(user.activatedOn).equals(inputMonth))
                || (user.deactivatedOn != null && !lastDayOfMonth(user.activatedOn).equals(lastDayOfMonth(user.deactivatedOn))
                    && firstDayOfMonth(user.activatedOn).equals(inputMonth)))
            {
                daysActive = user.activatedOn.getDayOfMonth();
            }
            else
            {
                if(user.deactivatedOn == null) return 0;
                else daysActive = user.deactivatedOn.getDayOfMonth();
            }

            return chargePerDay * (daysActive);
        }

        private static LocalDate stringtoDate(String inputMonth)
        {
            int year = Integer.parseInt(inputMonth.split("-")[0]);
            int month = Integer.parseInt(inputMonth.split("-")[1]);

            return LocalDate.of(year,month,1);
        }

        /**
         Takes a LocalDate object and returns a LocalDate which is the first day
         of that month. For example:

         firstDayOfMonth(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 1)
         **/
        private static LocalDate firstDayOfMonth(LocalDate date) {
            return date.withDayOfMonth(1);
        }

        /**
         Takes a LocalDate object and returns a LocalDate which is the last day
         of that month. For example:

         lastDayOfMonth(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 31)
         **/
        private static LocalDate lastDayOfMonth(LocalDate date) {
            return date.withDayOfMonth(date.lengthOfMonth());
        }

        /**
         Takes a LocalDate object and returns a LocalDate which is the next day.
         For example:

         nextDay(LocalDate.of(2022, 3, 17)) // => LocalDate.of(2022, 3, 18)
         nextDay(LocalDate.of(2022, 3, 31)) // => LocalDate.of(2022, 4, 1)
         **/
        private static LocalDate nextDay(LocalDate date) {
            return date.plusDays(1);
        }
    }

    Subscription plan = new Subscription(1, 1, 5000);

    User[] users = {
            new User(1, "Employee #1", LocalDate.of(2019, 1, 1), null, 1),
            new User(2, "Employee #2", LocalDate.of(2019, 1, 1), null, 1)
    };

    @Test
    public void worksWhenNoUsersAreActive() {
        assertEquals(0, Challenge.monthlyCharge("2018-10", plan, users));
    }

    @Test
    public void worksWhenTheActiveUsersAreActiveTheEntireMonth() {
        float expectedUserCount = 2;
        assertEquals(expectedUserCount * 5000, Challenge.monthlyCharge("2020-12", plan, users), 1);
    }
}
