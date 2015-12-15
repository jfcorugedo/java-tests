package es.juan.date;

import java.time.Month;
import java.time.YearMonth;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.data.auditing.CurrentDateTimeProvider;

public class LocalDateTimeTest {

	@Test
	public void test(){
		YearMonth date = YearMonth.of(2015, Month.APRIL);
		YearMonth date2 = YearMonth.of(2015, Month.APRIL);
		
		System.out.println(date);
		System.out.println(date2);
		
		System.out.println(DateTime.now());
		System.out.println(CurrentDateTimeProvider.INSTANCE.getNow());
	}
}
