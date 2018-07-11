package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.model.Human;

import java.time.LocalDate;

public interface OwnerLike extends Human {
    LocalDate getBirthday();
}
