package com.fhdw.loeppe.views.main;

import com.fhdw.loeppe.service.TestService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestView extends VerticalLayout {

    @Autowired    public TestView(TestService testService){
        Button button = new Button("test");
        button.addClickListener(ev -> testService.test());

        add(button);
    }
}
