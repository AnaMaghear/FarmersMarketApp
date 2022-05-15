package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyUsernameOrPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAndPasswordDoNotMatchException;
import org.loose.fis.sre.model.Consumer;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;


public class UserService {

    private static ObjectRepository<User> userRepository;
    private static ObjectRepository<Farmer> farmerRepository;
    private static ObjectRepository<Consumer> consumerRepository;
    private static ObjectRepository<Product> productRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("FarmersMarket.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        farmerRepository = database.getRepository(Farmer.class);
        consumerRepository = database.getRepository(Consumer.class);
        productRepository = database.getRepository(Product.class);
    }

    public static ObjectRepository<Consumer> getConsumerRepository() {
        return consumerRepository;
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static ObjectRepository<Farmer> getFarmerRepository() {
        return farmerRepository;
    }

    public static ObjectRepository<Product> getProductRepository() {
        return productRepository;
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void logInUser(String username, String password) throws EmptyUsernameOrPasswordException, UsernameAndPasswordDoNotMatchException {
        checkLogin(username, password);
    }

    private static void checkLogin(String username, String password) throws EmptyUsernameOrPasswordException, UsernameAndPasswordDoNotMatchException {
        if (username.isEmpty() || password.isEmpty())
            throw new EmptyUsernameOrPasswordException();
        else {
            for (User user : userRepository.find()) {
                if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()))
                    return;
            }

            throw new UsernameAndPasswordDoNotMatchException();
        }
    }

    public static String getRoleByUsername(String username) throws UsernameAndPasswordDoNotMatchException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                return user.getRole();
        }

        throw new UsernameAndPasswordDoNotMatchException();
    }
}
