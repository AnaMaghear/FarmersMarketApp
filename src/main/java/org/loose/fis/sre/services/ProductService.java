package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class ProductService {
    private static ObjectRepository<Product> productRepository = UserService.getProductRepository();
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();

    public static Product addProduct(String name, String description, String quantity, String pricePerUnit) throws EmptyFieldsException, NotANumberException {
        checkIfFieldsAreEmpty(name, description, quantity, pricePerUnit);
        double quantityD;
        double priceD;

        try {
            quantityD = Double.parseDouble(quantity);
            priceD = Double.parseDouble(pricePerUnit);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }

        Product p = new Product(name, description, quantityD, priceD);
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

    public static Product getProductById(NitriteId id) throws Exception {
        for (Product p : productRepository.find()) {
            if (Objects.equals(p.getId(), id))
                return p;
        }

        throw new Exception("no product");
    }

    public static void updateProductById(NitriteId id, String name, String description, String quantity, String pricePerUnit) throws NotANumberException, EmptyFieldsException {
        checkIfFieldsAreEmpty(name, description, quantity, pricePerUnit);
        double quantityD;
        double priceD;

        try {
            quantityD = Double.parseDouble(quantity);
            priceD = Double.parseDouble(pricePerUnit);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }

        Product editedProduct = null;

        for (Product p : productRepository.find()) {
            if (Objects.equals(id, p.getId())) {
                p.setName(name);
                p.setDescription(description);
                p.setQuantity(quantityD);
                p.setPricePerUnit(priceD);

                productRepository.update(p);
                editedProduct = p;
            }
        }

        for (Farmer f : farmerRepository.find()) {
            if (f.getProducts() == null)
                continue;

            for (Product p : f.getProducts())
                if (Objects.equals(id, p.getId()))
                    f.updateProduct(id, editedProduct);

            farmerRepository.update(f);
        }
    }
}
