package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> stringsFile1 = new ArrayList<>();
        Files.lines(Paths.get("Text1.txt"), StandardCharsets.UTF_8).forEach(str -> stringsFile1.add(str));
        Set<String> wordsFromFile1 = stringsFile1.stream()
                .filter(string -> !string.isEmpty())
                .map(string -> string.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", " ").replaceAll("\\s+", " ").trim())
                .flatMap(string -> Arrays.stream(string.split(" ")).map(s -> s.toLowerCase()))
                .sorted()
                .collect(Collectors.toSet());
        List<String> stringsFile2 = new ArrayList<>();
        Files.lines(Paths.get("Text2"), StandardCharsets.UTF_8).forEach(str -> stringsFile2.add(str));
        Set<String> wordsFromFile2 = stringsFile2.stream()
                .filter(string -> !string.isEmpty())
                .map(string -> string.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", " ").replaceAll("\\s+", " ").trim())
                .flatMap(string -> Arrays.stream(string.split(" ")).map(s -> s.toLowerCase()))
                .sorted()
                .collect(Collectors.toSet());
        Set<String> identicalWords = wordsFromFile1;
        identicalWords.retainAll(wordsFromFile2);
        identicalWords.stream().forEach(i -> System.out.println(i));
    }
}
