package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ProductService {
    private static ObjectRepository<Product> productRepository = UserService.getProductRepository();
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();

    public static Product addProduct(String name, String description, String quantity, String pricePerUnit) throws EmptyFieldsException {
        checkIfFieldsAreEmpty(name, description, quantity, pricePerUnit);

        Product p = new Product(name, description, Double.parseDouble(quantity), Double.parseDouble(pricePerUnit));
        productRepository.insert(p);

        return p;
    }

    private static void checkIfFieldsAreEmpty(String name, String description, String quantity, String pricePerUnit) throws EmptyFieldsException {
        if (name.isEmpty() || description.isEmpty() || quantity.isEmpty() || pricePerUnit.isEmpty())
            throw new EmptyFieldsException();
    }

    public static void removeProduct(NitriteId id) {
        for (Product product : productRepository.find())
            if (product.getId() == id)
                productRepository.remove(product);
    }

    public static ArrayList<Product> filter(String search, String filterBy) {
        ArrayList<Product> shownProducts = new ArrayList<Product>();
        if (filterBy.equals("product")) {
            for (Product p : productRepository.find())
                if (p.getName().contains(search))
                    shownProducts.add(p);
        } else if (filterBy.equals("county")) {
            for (Farmer f : farmerRepository.find())
                if (f.getAddress().contains(search))
                    shownProducts.addAll(f.getProducts());
        }

        return shownProducts;
    }
}
