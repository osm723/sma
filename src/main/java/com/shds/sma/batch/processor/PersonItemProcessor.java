package com.shds.sma.batch.processor;

import com.shds.sma.batch.entity.Person;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    // 읽어온 Person 데이터를 처리하는 로직을 구현합니다.

    @Override
    public Person process(Person person) {
        String firstName = person.getFirstName().toUpperCase();
        String lastName = person.getLastName().toUpperCase();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }
}
