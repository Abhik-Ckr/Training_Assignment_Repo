package javaStreamAPI4thJune.FrequencyCharacter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class CharacterFrequency {

    public static void main(String[] args) {
        String input = "programming";

        Map<Character, Long> frequencyMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        frequencyMap.forEach((character, count) -> System.out.println(character + ": " + count));
    }
}
