package ru.nsu.ccfit.lopatkin.lab4.GUI.controller;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;

public class FactoryViewController {
    private final FactoryController factoryController;

    public FactoryViewController(FactoryController factoryController) {
        this.factoryController = factoryController;
    }

    public void changeBuildTaskDelay(int new_val) {
        factoryController.getBuildCarTask().changeDelay(new_val);
    }

    public void changeSupplyCarBodyDelay(int new_val) {
        factoryController.getSupplyCarBodyTask().changeDelay(new_val);
    }

    public void changeSupplyEnginDelay(int new_val) {
        factoryController.getSupplyEngineTask().changeDelay(new_val);
    }

    public void changeSupplyAccessoriesDelay(int new_val) {
        factoryController.getSupplyAccessoriesTask().changeDelay(new_val);
    }

    public void changeSellCarDelay(int new_val) {
        factoryController.getSellCarTask().changeDelay(new_val);
    }
}
