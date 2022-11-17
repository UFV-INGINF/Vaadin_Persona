package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.backend.Persona;

import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

        HorizontalLayout horizontal1= new HorizontalLayout();

        TextField textField2 = new TextField("Tu nombre");
        textField2.addThemeName("bordered");
        // Use TextField for standard text input
        TextField textField = new TextField("Your name");
        textField.addThemeName("bordered");

        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> Notification.show(service.greet(textField.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Button click listeners can be defined as lambda expressions
        Button button2 = new Button("Hola ",
                e -> Notification.show(service.greet(textField2.getValue())));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button2.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("left-align");
//
//        Tab details = new Tab("Detalles");
//        Tab payment = new Tab("Pago");
//        Tab shipping = new Tab("Envío");
//
//        Tabs tabs = new Tabs(details, payment, shipping);
//        tabs.addSelectedChangeListener(
//                event -> System.out.println(event.getSelectedTab()));
//


//        Defining grid for Persona
        Grid<Persona> grid = new Grid<>(Persona.class, false);
        grid.addColumn(Persona::getNombre).setHeader("First name");
        grid.addColumn(Persona::getApellido).setHeader("Last name");
        grid.addColumn(Persona::getTelefono).setHeader("Phone");
        grid.addColumn(Persona::getDireccion).setHeader("Address");

        List<Persona> people = service.getPersonaList();
        System.out.println(people);
        grid.setItems(people);


        horizontal1.add(textField2, button2);
        add(horizontal1, textField, button, grid );
    }


}
