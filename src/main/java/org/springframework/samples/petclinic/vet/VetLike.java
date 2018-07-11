package org.springframework.samples.petclinic.vet;

import org.springframework.samples.petclinic.model.Human;

import java.time.LocalDate;

public interface VetLike extends Human {
    LocalDate getBirthday();
}
