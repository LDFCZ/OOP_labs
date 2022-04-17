package ru.nsu.ccfit.lopatkin.lab4.GUI.controller;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;

public class FactoryViewController {
    private final FactoryController factoryController;

    public FactoryViewController(FactoryController factoryController) {
        this.factoryController = factoryController;
    }

    public void changeBuildTaskDelay(int new_val) {
        factoryController.getWorkerThreadPool().setDelay(new_val);
    }

    public void changeSupplyCarBodyDelay(int new_val) {
        factoryController.getCarBodySupplierThreadPool().setDelay(new_val);
    }

    public void changeSupplyEnginDelay(int new_val) {
        factoryController.getEngineSupplierThreadPool().setDelay(new_val);
    }

    public void changeSupplyAccessoriesDelay(int new_val) {
        factoryController.getAccessoriesSupplierThreadPool().setDelay(new_val);
    }

    public void changeSellCarDelay(int new_val) {
        factoryController.getDealerThreadPool().setDelay(new_val);
    }

    public void addSellCarTask() {factoryController.addSellTask();}
}
