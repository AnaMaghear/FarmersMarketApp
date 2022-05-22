package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Consumer;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.model.User;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class ConsumerServiceTest {
    public static final String ADMIN = "admin";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".FarmersMarketTest";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
        System.out.println("After each");
    }

//    @Test
//    @DisplayName("Consumer is successfully persisted to Database")
//    void testConsumerIsAddedToDatabase() throws EmptyFieldsException {
//        ConsumerService.addConsumer(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
//        assertThat(ConsumerService.getAllConsumers()).isNotEmpty();
//        assertThat(ConsumerService.getAllConsumers()).size().isEqualTo(1);
//        Consumer c = ConsumerService.getAllConsumers().get(0);
//        assertThat(c).isNotNull();
//        assertThat(c.getUsername()).isEqualTo(ADMIN);
//        assertThat(c.getAddress()).isEqualTo(ADMIN);
//        assertThat(c.getFirstName()).isEqualTo(ADMIN);
//        assertThat(c.getLastName()).isEqualTo(ADMIN);
//        assertThat(c.getPhone()).isEqualTo(ADMIN);
//    }
//
//    @Test
//    @DisplayName("Order is successfully persisted to Database")
//    void testOrderIsAddedToConsumerAndToDatabase() throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException, UsernameAlreadyExistsException {
//        FarmerService.addFarmer(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, true);
//        FarmerService.addProductToFarmer(ADMIN, ADMIN, ADMIN, "25", "2");
//        ConsumerService.addConsumer(ADMIN + 'C', ADMIN, ADMIN, ADMIN, ADMIN);
//        Consumer c = ConsumerService.getAllConsumers().get(0);
//        Product p = ProductService.getAllProducts().get(0);
//        FarmerService.addOrderToFarmer(p, c, "10", "delivery" );
//        Order o = OrderService.getAllOrders().get(0);
//        assertThat(c.getPastOrders().get(0)).isEqualTo(o);
//    }
}
