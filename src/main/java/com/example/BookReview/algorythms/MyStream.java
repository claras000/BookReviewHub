package com.example.BookReview.algorythms;
import com.example.BookReview.services.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyStream {

    @Autowired
    ServiceBook serviceBook;

    List<String> myList = Arrays.asList("1","2","3");


    List<String> word = Arrays.asList("Hello", "World");


    public List<Integer> streamList(List<String> myList){
        List<Integer> streamList = myList.stream().map(s -> Integer.valueOf(s)+1)
                .filter(number -> number !=2)
                .collect(Collectors.toList());
        return  streamList;
    }

    public List<Character> streamListFlat(List<String> word){
      return word.stream().flatMap(w -> w.chars().mapToObj(v -> (char) v)).collect(Collectors.toList());
    }


    }

