package com.javatechie.redis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javatechie.redis.entity.Product;
import com.javatechie.redis.respository.ProductDao;


@SpringBootTest(classes = SpringDataRedisExampleApplication.class)
class test1 {
	
	 @Autowired
	    private ProductDao productDao;

	    @BeforeEach
	    void setup() {
	        List<Product> products = productDao.findAll();
	        for (Product product : products) {
	            productDao.deleteProduct(product.getId());
	        }
	    }

	    @Test
	    void testSaveProduct() {
	        Product product = new Product(1, "Laptop", 10, 1500L);
	        Product savedProduct = productDao.save(product);

	        assertNotNull(savedProduct);
	        assertEquals("Laptop", savedProduct.getName());
	        assertEquals(1500L, savedProduct.getPrice());
	    }

	    @Test
	    void testFindAllProducts() {
	        productDao.save(new Product(1, "Laptop", 10, 1500L));
	        productDao.save(new Product(2, "Smartphone", 5, 800L));

	        List<Product> products = productDao.findAll();
	        assertNotNull(products);
	        assertEquals(2, products.size());
	    }

	    @Test
	    void testFindProductById() {
	        productDao.save(new Product(1, "Laptop", 10, 1500L));
	        Product retrievedProduct = productDao.findProductById(1);

	        assertNotNull(retrievedProduct);
	        assertEquals("Laptop", retrievedProduct.getName());
	        assertEquals(1500L, retrievedProduct.getPrice());
	    }

	    @Test
	    void testDeleteProduct() {
	        productDao.save(new Product(1, "Laptop", 10, 1500L));
	        String response = productDao.deleteProduct(1);

	        assertEquals("produit supprimer !!", response);
	        Product deletedProduct = productDao.findProductById(1);
	        assertNull(deletedProduct);
	    }

	    @Test
	    void testUpdateProduct() {
	        Product product = new Product(1, "Laptop", 10, 1500L);
	        productDao.save(product);

	        Product updatedProduct = new Product(1, "Gaming Laptop", 8, 2000L);
	        Product result = productDao.updateProduct(1, updatedProduct);

	        assertNotNull(result);
	        assertEquals("Gaming Laptop", result.getName());
	        assertEquals(2000L, result.getPrice());
	    }

	    @Test
	    void testUpdateNonExistingProduct() {
	        Product updatedProduct = new Product(1, "Tablet", 15, 500L);
	        Product result = productDao.updateProduct(1, updatedProduct);

	        assertNull(result);
	    }
}
