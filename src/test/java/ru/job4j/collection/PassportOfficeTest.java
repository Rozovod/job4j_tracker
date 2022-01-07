package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void add() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void whenNotAdd() {
        Citizen citizenOne = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizenTwo = new Citizen("2f44a", "Stanislav Kurilenko");
        PassportOffice office = new PassportOffice();
        office.add(citizenOne);
        assertFalse(office.add(citizenTwo));
    }
}