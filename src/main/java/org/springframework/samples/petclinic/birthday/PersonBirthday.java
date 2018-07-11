package org.springframework.samples.petclinic.birthday;

import java.time.LocalDate;

/**
 * @@@EFFECTIVE@@@ ITEM 1
 * @@@EFFECTIVE@@@ ITEM 10 & 11 & 12
 * @@@EFFECTIVE@@@ ITEM 15
 * @@@EFFECTIVE@@@ ITEM 16
 * @@@EFFECTIVE@@@ ITEM 17
 */
public class PersonBirthday {

    public String name;
    public LocalDate birthday;

    public PersonBirthday(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public PersonBirthday() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
