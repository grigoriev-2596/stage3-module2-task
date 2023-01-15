package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinesReader {
    public static List<String> readLines(String path, int bound) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);
        if (is == null) {
            throw new IllegalArgumentException("file not found: " + path);
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (int i = 0; i < bound; i++) {
                String line = br.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("File contains less than 20 lines!");
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return lines;
        }
        return lines;
    }

    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int year = random.nextInt(10) + LocalDateTime.now().getYear() - 10;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(23) + 1;
        int minutes = random.nextInt(59) + 1;
        return LocalDateTime.of(year, Month.of(month), day, hour, minutes);
    }
}
