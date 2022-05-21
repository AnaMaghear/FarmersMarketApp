package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.model.User;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class FarmerServicesTest {
    public static final String ADMIN = "admin";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
//        UserService.closeDatabase();
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
//    @DisplayName("Farmer is successfully persisted to Database")
//    void testFarmerIsAddedToDatabase() throws EmptyFieldsException {
//        FarmerService.addFarmer(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, true);
//        assertThat(FarmerService.getAllFarmers()).isNotEmpty();
//        assertThat(FarmerService.getAllFarmers()).size().isEqualTo(1);
//        Farmer f = FarmerService.getAllFarmers().get(0);
//        assertThat(f).isNotNull();
//        assertThat(f.getUsername()).isEqualTo(ADMIN);
//        assertThat(f.getAddress()).isEqualTo(ADMIN);
//        assertThat(f.getFirstName()).isEqualTo(ADMIN);
//        assertThat(f.getLastName()).isEqualTo(ADMIN);
//        assertThat(f.getDescription()).isEqualTo(ADMIN);
//        assertThat(f.isAvailabilityStatus()).isEqualTo(true);
//    }
//
//    @Test
//    @DisplayName("Product is successfully persisted to Database")
//    void testProductIsAddedToFarmerAndToDatabase() throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
//        FarmerService.addFarmer(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, true);
//        assertThat(FarmerService.getAllFarmers()).isNotEmpty();
//        assertThat(FarmerService.getAllFarmers()).size().isEqualTo(1);
//        FarmerService.addProductToFarmer(ADMIN, ADMIN, ADMIN, "25", "2");
//        assertThat(ProductService.getAllProducts()).size().isEqualTo(1);
//        Farmer f = FarmerService.getAllFarmers().get(0);
//        Product p = ProductService.getAllProducts().get(0);
//        assertThat(f.getProducts().get(0)).isEqualTo(p);
//    }
//
//    @Test
//    @DisplayName("Farmer is successfully updated")
//    void testFarmerIsUpdated() throws EmptyFieldsException {
//        FarmerService.addFarmer(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, true);
//        FarmerService.updateFarmerByUsername(ADMIN, ADMIN + "aa", ADMIN, ADMIN, ADMIN, ADMIN, true);
//        assertThat(FarmerService.getAllFarmers().get(0).getFirstName()).isEqualTo(ADMIN + "aa");
//    }
}
