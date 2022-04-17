package ru.nsu.ccfit.lopatkin.lab4.tasks;


import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.constSpace.ConstSpace;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;
import ru.nsu.ccfit.lopatkin.lab4.service.ProductService;

@Slf4j
public class Supply<E extends Product & CarPart> implements Task {
    private static final String SUPPLIED = " supplied ";

    private final Storage<E> storage;
    private final ProductService<E> productService = new ProductService<>();



    private final Class<E> productType;

    public Supply(Storage<E> storage, Class<E> productType) {
        this.storage = storage;
        this.productType = productType;
    }

    @Override
    public void performWork(String threadName, int delay) throws InterruptedException {
        try {
            Thread.sleep(delay);
            E product = productType.getDeclaredConstructor().newInstance();
            productService.produceProduct(product);
            storage.put(product);
            log.info(threadName + SUPPLIED + product.getFullVin());
        } catch (InterruptedException e) {
            log.info(threadName + ConstSpace.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }

}
