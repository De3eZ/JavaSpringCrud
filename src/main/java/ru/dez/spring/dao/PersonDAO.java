package ru.dez.spring.dao;

import org.springframework.stereotype.Component;
import ru.dez.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"Bob"));
        people.add(new Person(++PEOPLE_COUNT,"May"));
    }

    public List<Person> index() {
        return people;
    }

    public Person getPersonById(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
