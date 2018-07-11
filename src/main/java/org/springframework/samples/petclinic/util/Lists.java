package org.springframework.samples.petclinic.util;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @@@EFFECTIVE@@@ ITEM 26 & 30
 */
public class Lists {

    public static List<String> filter(List<String> list, Predicate<String> filter) {
        return list.stream()
            .filter(filter)
            .collect(toList());
    }

}
