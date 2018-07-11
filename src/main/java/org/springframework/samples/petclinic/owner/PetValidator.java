/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import org.springframework.samples.petclinic.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such validation rule in Java.
 * </p>
 *
 * @@@SOLID@@@
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class PetValidator implements Validator {

    private static final String AGGRESSIVE_RACE = "aggressive_race";
    private static final String REQUIRED = "required";
    // @@@EFFECTIVE@@@ item 34
    // @@@EFFECTIVE@@@ item 35 – set up race aggresiveness in an order (not necessarily sensible)
    // @@@EFFECTIVE@@@ item 38 – imagine the existence of some "aggressive races controller/repo" etc
    public static final String CA_DE_BOU = "249";
    public static final String DOGO_CANARIO = "346";
    public static final String BULL_TERRIER = "011";
    public static final String DOGO_ARGENTINO = "292";
    public static final String TOSA = "260";
    public static final String ROTTWEILER = "147";
    public static final HashSet<String> AGGRESSIVE_RACES_IN_POLAND = new HashSet<>(Arrays.asList(
        BULL_TERRIER,
        CA_DE_BOU,
        DOGO_ARGENTINO,
        DOGO_CANARIO,
        TOSA,
        ROTTWEILER
    ));

    @Override
    public void validate(Object obj, Errors errors) {
        Pet pet = (Pet) obj;
        String name = pet.getName();
        // name validation
        if (!Strings.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (pet.isNew() && pet.getType() == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        if (isADog(pet) && isAnAgressiveRaceInPoland(pet)) {
            errors.rejectValue("raceCode", REQUIRED, REQUIRED);
        }

        // birth date validation
        if (pet.getBirthDate() == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED);
        }
    }

    private boolean isAnAgressiveRaceInPoland(Pet pet) {
        return AGGRESSIVE_RACES_IN_POLAND.contains(pet.getRaceCode());
    }

    private boolean isADog(Pet pet) {
        return pet.getType().getName().equals("dog");
    }

    /**
     * This Validator validates *just* Pet instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Pet.class.isAssignableFrom(clazz);
    }


}
