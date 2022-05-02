package ru.nsu.ccfit.lopatkin.client.requestHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.client.controllers.LogInController;

@Component
public class LogInRequestHandler {

    @Autowired
    private LogInController logInController;



}
