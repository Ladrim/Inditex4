package org.alfonso.domain.price;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateInRangeTest
{
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Date parseDate(String date)
    {
        try
        {
            return df.parse(date);
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }

    @Test
    public void WhenADateIsGreaterThanEndDate()
    {
        Date startDate = parseDate("01-01-1999 10:00:00");
        Date endDate = parseDate("01-01-2010 10:00:00");
        Date actualDate = parseDate("01-01-2021 10:00:00");

        DateInRange underTest = new DateInRange(startDate,endDate);

        Assertions.assertThat(underTest.isInRange(actualDate)).isFalse();
    }
    @Test
    public void WhenADateIsMinorThanStartDate()
    {
        Date startDate = parseDate("01-01-1999 10:00:00");
        Date endDate = parseDate("01-01-2010 10:00:00");
        Date actualDate = parseDate("01-01-1988 10:00:00");

        DateInRange underTest = new DateInRange(startDate,endDate);

        Assertions.assertThat(underTest.isInRange(actualDate)).isFalse();
    }
    @Test
    public void WhenADateIsBetweenStartDateAndEndDate()
    {
        Date startDate = parseDate("01-01-1999 10:00:00");
        Date endDate = parseDate("01-01-2010 10:00:00");
        Date actualDate = parseDate("01-01-2003 10:00:00");

        DateInRange underTest = new DateInRange(startDate,endDate);

        Assertions.assertThat(underTest.isInRange(actualDate)).isTrue();
    }
}