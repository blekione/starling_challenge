package com.blekione;

import java.time.Clock;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Producers {

    @Produces
    public DateTimeFormatter defaultDateTimeFormatter() {
        DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return defaultFormatter;
    }

    @Produces
    public Clock defaultClock() {
        return Clock.systemDefaultZone();
    }
}
