package com.javatechie.redis;

import com.javatechie.redis.entity.Product;
import com.javatechie.redis.respository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@Controller
@RequestMapping("/products")
public class SpringDataRedisExampleApplication {

    @Autowired
    private ProductDao dao;

    // List all products
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = dao.findAll();
        model.addAttribute("products", products);
        return "product-list"; // Renders the product-list view
    }

    // Show form to add a new product
    @GetMapping("/product-form")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product(0, null, 0, 0));
        return "product-form"; // Renders the product-form view
    }

    // Save a new product
    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        dao.save(product);
        return "redirect:/products"; // Redirects back to the list
    }

    // Show form to edit an existing product
    @GetMapping("/product-edit/{id}")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = dao.findProductById(id);
        if (product == null) {
            // Handle case where product is not found
            return "redirect:/products?error=ProductNotFound";
        }
        model.addAttribute("product", product);
        return "product-edit"; // Renders the product-edit view
    }

    
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        dao.save(product);
        return "redirect:/products"; 
    }

    // Delete a product by ID
    @GetMapping("/delete/{id}")
    public String remove(@PathVariable int id) {
        dao.deleteProduct(id);
        return "redirect:/products"; 
    }

    
    @GetMapping("/{id}")
    public String findProduct(@PathVariable int id, Model model) {
        Product product = dao.findProductById(id);
        if (product == null) {
            // Handle case where product is not found
            return "redirect:/products?error=ProductNotFound";
        }
        model.addAttribute("product", product);
        return "product-details"; // Renders the product-details view
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisExampleApplication.class, args);
    }
}
