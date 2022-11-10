package com.blekione.testutils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TestUtil {

    public static Clock getFixedClock() {
        return Clock.fixed(Instant.parse("2022-11-08T20:00:00.00Z"), ZoneId.of("UTC"));
    }

}
