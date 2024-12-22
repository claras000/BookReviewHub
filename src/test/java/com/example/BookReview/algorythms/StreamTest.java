package com.example.BookReview.algorythms;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class StreamTest {

    @Autowired
    private MyStream myStream;

    @Test
    void streamTheList() {
        //arrange
        List<String> myList = Arrays.asList("1", "2", "3");
        List<Integer> expectedList = Arrays.asList(3, 4);

        //act
        List<Integer> integerList = myStream.streamList(myList);

        //assert

        Assertions.assertThat(integerList).isEqualTo(expectedList);

    }

    @Test
    void streamListFlatTest() {
        //arrange
        List<String> words = Arrays.asList("V", "inc", "ent");
        List<Character> expectedList = Arrays.asList('V', 'i', 'n', 'c', 'e', 'n', 't');

        //act
        myStream.streamListFlat(words);

        //assert
        Assertions.assertThat(words).isNotNull();
        Assertions.assertThat(words.equals(expectedList));

    }
}