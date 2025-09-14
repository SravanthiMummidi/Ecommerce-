package com.spring.project.controller;

import com.spring.project.entity.Category;
import com.spring.project.entity.Product;
import com.spring.project.entity.UserDetail;
import com.spring.project.repository.UserRepository;
import com.spring.project.service.CategoryService;
import com.spring.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserDetail userDetail = userRepository.findByEmail(email);
        m.addAttribute("user",userDetail);
    }

    @GetMapping("/home")
    public String home(){
        return "admin/home";
    }

    @GetMapping("/profile")
    public String profile(){
        return "/admin/profile";
    }


    @GetMapping("/addCategory")
    public String addCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        return "redirect:/admin/viewProducts";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.delete(id);
        return "redirect:/admin/addCategory";
    }


    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam double price,
                             @RequestParam String description,
                             @RequestParam Long categoryId,
                             @RequestParam("image") MultipartFile file) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        Category category = categoryService.findById(categoryId).orElse(null);
        product.setCategory(category);

        // Save the image and get the image URL
        String imageUrl = saveImage(file);
        product.setImageUrl(imageUrl);

        productService.save(product);
        return "redirect:/admin/viewProducts";
    }

    @GetMapping("/viewProducts")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        Map<Category, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        model.addAttribute("productsByCategory", productsByCategory);
        return "admin/viewProducts";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long id) {
        productService.delete(id);
        return "redirect:/admin/viewProducts";
    }


    private String saveImage(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.write(path, file.getBytes());
            return "/images/" + fileName;  // URL path to access the image
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}