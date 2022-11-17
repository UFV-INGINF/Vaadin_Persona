package org.vaadin.example;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.vaadin.backend.Persona;
import org.vaadin.backend.ReadFile;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }

    public ArrayList<Persona> getPersonaList() {
        return ReadFile.readFile();
    }

}
