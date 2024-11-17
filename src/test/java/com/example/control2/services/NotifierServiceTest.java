package com.example.control2.services;

import com.example.control2.models.Notifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotifierServiceTest {

    private final NotifierService notifierService = new NotifierService();

    @Test
    void testGetNotificationHoursInterval() {
        // Caso "hour"
        Notifier hourlyNotifier = new Notifier();
        hourlyNotifier.setTimeunit("hour");
        hourlyNotifier.setAmount(2);
        assertEquals(2, notifierService.getNotificationHoursInterval(hourlyNotifier));

        // Caso "day"
        Notifier dailyNotifier = new Notifier();
        dailyNotifier.setTimeunit("day");
        dailyNotifier.setAmount(1);
        assertEquals(24, notifierService.getNotificationHoursInterval(dailyNotifier));

        // Caso "week"
        Notifier weeklyNotifier = new Notifier();
        weeklyNotifier.setTimeunit("week");
        weeklyNotifier.setAmount(1);
        assertEquals(24 * 7, notifierService.getNotificationHoursInterval(weeklyNotifier));

        // Caso "month"
        Notifier monthlyNotifier = new Notifier();
        monthlyNotifier.setTimeunit("month");
        monthlyNotifier.setAmount(1);
        assertEquals(24 * 7 * 30, notifierService.getNotificationHoursInterval(monthlyNotifier));

        // Caso no válido
        Notifier invalidNotifier = new Notifier();
        invalidNotifier.setTimeunit("year");
        invalidNotifier.setAmount(1);
        assertEquals(0, notifierService.getNotificationHoursInterval(invalidNotifier));
    }
}
