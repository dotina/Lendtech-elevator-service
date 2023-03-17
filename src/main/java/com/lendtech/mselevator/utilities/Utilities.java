package com.lendtech.mselevator.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;

import static com.lendtech.mselevator.utilities.GlobalVariables.REQUEST_DATE_TIME_FORMAT;

@Component
public class Utilities {

    public static final String DATE_REGEX = "[^a-zA-Z0-9]";
    public static final double SCALE = Math.pow(10, 1);

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");



    public static String getFormattedTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(REQUEST_DATE_TIME_FORMAT);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return simpleDateFormat.format(timestamp);
    }

    public static String generateTrackingID() {
        return UUID.randomUUID().toString();
    }

    public static String parseToJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static String nullSafe(Object object, String defaultValue) {
        return Objects.isNull(object) ? defaultValue : object.toString();
    }


}