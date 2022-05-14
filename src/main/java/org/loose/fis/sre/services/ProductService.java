package org.loose.fis.sre.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ProductService {
    private static ObjectRepository<Product> productRepository = UserService.getProductRepository();
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();
    final static AtomicLong identifierGenerator = new AtomicLong(1);

    public static void addProduct(String name, String description, double quantity, double pricePerUnit) {
        productRepository.insert(new Product(identifierGenerator.incrementAndGet(), name, description, quantity, pricePerUnit));
    }

    public static void removeProduct(long id) {
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
