/*
 * Copyright 2012-2018 the original author or authors.
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
package org.springframework.samples.petclinic.birthday;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * @@@SOLID@@@
 */
@Controller
class BirthdayController {

    private final VetRepository vets;
    private final OwnerRepository owners;

    public BirthdayController(
        VetRepository clinicService,
        OwnerRepository owners
    ) {
        this.vets = clinicService;
        this.owners = owners;
    }

    @GetMapping("/birthdays.html")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        List<Person> persons = new ArrayList<>();
        persons.addAll(this.vets.findAll());
        persons.addAll(this.owners.findAll());

        List<PersonBirthday> personBirthdays = persons.stream()
            .map(this::toPersonBirthday)
            .collect(Collectors.toList());

        model.put("persons", personBirthdays);
        model.put("dayNames", getDayNames());
        return "birthdays/birthdays";
    }

    /**
     * @@@EFFECTIVE@@@ item 9
     * @@@EFFECTIVE@@@ item 24
     */
    private Object getDayNames() {
        BufferedReader in = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://example.com");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            Document doc = Jsoup.connect("http://www.kalbi.pl").get();
            Elements nameDayElem = doc.select("div.calCard-name-day");
            Elements a = nameDayElem.select("a");
            return a.text();
        } catch (IOException e) {
            return emptyList();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private PersonBirthday toPersonBirthday(Person p) {
        if (p instanceof Vet) {
            return new PersonBirthday(p.getFirstName() + " " + p.getLastName(), ((Vet) p).getBirthday());
        } else if (p instanceof Owner) {
            return new PersonBirthday(p.getFirstName() + " " + p.getLastName(), ((Owner) p).getBirthday());
        } else {
            return null;
        }
    }

}
