 package genericUtilities;

import java.util.Date;
import java.util.Random;
import java.time.LocalDate;
import com.github.javafaker.Faker;

/**
 * This class contains java specific methods
 * @param args
 */
public class JavaUtility {
	    /**
	     * This method will generate random number for every run
	     * @return
	     */
		public int getRandomNum()
		{
			Random r = new Random();
			int Num = r.nextInt(9999999);
			
			if(Num > 9999990)
			{
				Num++;
			}
			return Num;
			
		}
		
		
		/**
		 * This method will generate System date
		 * @return
		 */
		public String getSystemDate()
		{
		    Date d=new Date();
			return d.toString();
		}
		
		/**
		 * This Method Will Return System Date In Format
		 * @return
		 */
		public String getSystemDateInFormat()
		{
			Date d=new Date();
			String dArr []=d.toString().split(" ");
			String day=dArr[2];
			String month=dArr[1];
			String year=dArr[5];
			String time=dArr[3].replaceAll(":","-");
			String date=day+"-"+month+"-"+year+"-"+time;
			return date;
		}
		
		/**
		 * This Method Will Return Random Mobile Number
		 * @return
		 */
		public String getRandomMobileNum() 
		{
			Random random = new Random();
			long mobileNumberr = 9000000000L+(long)(random.nextDouble() * 1000000000L);
			String mobileNumber = String.valueOf(mobileNumberr);
			return mobileNumber;
		}
		
		/**
		 * This Method Will Return Single Random Number
		 * @return
		 */
		public int getSingleRandomnumber()
		{
			Random r = new Random();
			int num = r.nextInt(9);
			return num;
		}
		
		/**
		 * This method will return Highest Random Number
		 * @return
		 */
		public Long getHighestRandomNumber()
		{
			Random r = new Random();
			Long Num = r.nextLong(999999999999999999l);
			return Num;
		}
		
		
		public String getRandomName()
		{
			Faker faker = new Faker();
			String fakeName = faker.name().fullName().replaceAll("[^a-zA-Z ]", "");
			return fakeName;
		}
		
		public String getRandomSingleName()
		{
			Faker faker = new Faker();
			String firstName = faker.name()
			                        .firstName()
			                        .split("\\s+")[0]
			                        .toLowerCase();

			return firstName;
		}
		
		
		public int getTodaysDate() 
		{
			int todayDate = LocalDate.now().getDayOfMonth();
	        return todayDate;
		}
		
		

	

}
