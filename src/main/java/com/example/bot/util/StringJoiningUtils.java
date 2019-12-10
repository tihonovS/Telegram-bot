package com.example.bot.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringJoiningUtils {
    public static String join(List<String> stringList){
       return stringList.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }
}
