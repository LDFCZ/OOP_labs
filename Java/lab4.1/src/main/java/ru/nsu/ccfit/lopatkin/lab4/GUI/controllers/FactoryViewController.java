package ru.nsu.ccfit.lopatkin.lab4.GUI.controllers;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryCreator;

public class FactoryViewController {
    private final FactoryCreator factoryCreator;

    public FactoryViewController(FactoryCreator factoryCreator) {
        this.factoryCreator = factoryCreator;
    }

    public void changeBuildTaskDelay(int new_val) {
        factoryCreator.getBuildCarTask().changeDelay(new_val);
    }

    public void changeSupplyCarBodyDelay(int new_val) {
        factoryCreator.getSupplyCarBodyTask().changeDelay(new_val);
    }

    public void changeSupplyEnginDelay(int new_val) {
        factoryCreator.getSupplyEngineTask().changeDelay(new_val);
    }

    public void changeSupplyAccessoriesDelay(int new_val) {
        factoryCreator.getSupplyAccessoriesTask().changeDelay(new_val);
    }

    public void changeSellCarDelay(int new_val) {
        factoryCreator.getSellCarTask().changeDelay(new_val);
    }
}
