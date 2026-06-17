package genericUtilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

/**
 * This class contains java specific methods
 */
public class JavaUtility {

	/**
	 * This method will generate a random number strictly below 9999999
	 * for every run
	 * @return random int in range [0, 9999998]
	 */
	public int getRandomNum() {

		Random r = new Random();
		int num = r.nextInt(9999999);
		return num;
	}

	/**
	 * This method will generate the current system date/time as a String
	 * @return current timestamp
	 */
	public String getSystemDate() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy");
		return now.format(formatter);
	}

	/**
	 * This method will return the system date formatted as
	 * dd-MMM-yyyy-HH-mm-ss (safe across locales/JVMs, unlike splitting
	 * Date.toString())
	 * @return formatted date string
	 */
	public String getSystemDateInFormat() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy-HH-mm-ss");
		return now.format(formatter);
	}

	/**
	 * This method will return a random 10-digit Indian-style mobile number
	 * starting with 9, in the range 9000000000-9999999999
	 * @return random mobile number as String
	 */
	public String getRandomMobileNum() {

		long mobileNumber = ThreadLocalRandom.current().nextLong(9000000000L, 9999999999L + 1);
		return String.valueOf(mobileNumber);
	}

	/**
	 * This method will return a single random digit, 0-9 inclusive
	 * @return random int in range [0, 9]
	 */
	public int getSingleRandomNumber() {

		Random r = new Random();
		int num = r.nextInt(10);
		return num;
	}

	/**
	 * This method will return a large random number, safe on Java 8+
	 * (does not rely on Random.nextLong(bound), which requires Java 17+)
	 * @return random long in range [0, 999999999999999999]
	 */
	public long getHighestRandomNumber() {

		long bound = 999999999999999999L;
		long num = (long) (ThreadLocalRandom.current().nextDouble() * bound);
		return num;
	}

	/**
	 * This method will return a random full name with only alphabetic
	 * characters and spaces
	 * @return random fake full name
	 */
	public String getRandomName() {

		Faker faker = new Faker();
		String fakeName = faker.name().fullName().replaceAll("[^a-zA-Z ]", "");
		return fakeName;
	}

	/**
	 * This method will return a random single first name, lowercase,
	 * guaranteed to be longer than 3 characters
	 * @return random first name
	 */
	public String getRandomSingleName() {

		Faker faker = new Faker();
		String firstName;

		do {
			firstName = faker.name()
					.firstName()
					.split("\\s+")[0]
					.toLowerCase();
		} while (firstName.length() <= 3);

		return firstName;
	}

	/**
	 * This method will return today's day of month as an int
	 * @return day of month, e.g. 17
	 */
	public int getTodaysDate() {

		int todayDate = LocalDate.now().getDayOfMonth();
		return todayDate;
	}

	/**
	 * This method will return today's day of month as a String
	 * @return day of month as String, e.g. "17"
	 */
	public String getTodayExactDate() {

		String day = String.valueOf(LocalDate.now().getDayOfMonth());
		return day;
	}

	/**
	 * This method will return the upcoming 30-minute appointment slot
	 * from the current time, formatted as a range
	 * e.g. if now is 15:48, returns "04:00 PM - 04:30 PM"
	 * @return time slot range as String
	 */
	public String getTodaysDateInFormat() {

		LocalTime now = LocalTime.now();
		int minute = now.getMinute() < 30 ? 0 : 30;
		LocalTime slotStart = now.withMinute(minute).withSecond(0).withNano(0);
		LocalTime nextSlotStart = slotStart.plusMinutes(30);
		LocalTime nextSlotEnd = nextSlotStart.plusMinutes(30);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		String timeSlot = nextSlotStart.format(formatter) + " - " + nextSlotEnd.format(formatter);

		return timeSlot;
	}

	/**
	 * This method will return a 30-minute appointment slot, one slot
	 * further ahead than getTodaysDateInFormat()
	 * e.g. if now is 15:48, returns "04:30 PM - 05:00 PM"
	 * @return time slot range as String
	 */
	public String increaseTimeByPlusThirtyMin() {

		LocalTime now = LocalTime.now();
		int minute = now.getMinute() < 30 ? 0 : 30;
		LocalTime slotStart = now.withMinute(minute).withSecond(0).withNano(0);
		LocalTime nextSlotStart = slotStart.plusMinutes(60);
		LocalTime nextSlotEnd = nextSlotStart.plusMinutes(30);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		String timeSlot = nextSlotStart.format(formatter) + " - " + nextSlotEnd.format(formatter);

		return timeSlot;
	}
}
