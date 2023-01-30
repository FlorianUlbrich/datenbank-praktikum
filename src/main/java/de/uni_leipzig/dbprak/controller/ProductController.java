package de.uni_leipzig.dbprak.controller;

import de.uni_leipzig.dbprak.entity.Product;
import de.uni_leipzig.dbprak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/get-product-by-asin")
    public String getProductByAsin(){
        return "get-product-by-asin";
    }

    @PostMapping("/get-product-by-asin")
    public String showUpdateForm() {
        return "get-products-like";
    }


    @RequestMapping(value = "/get-product-by-asin-request", method = RequestMethod.POST)
    public String getProductByAsin(
            @RequestParam(value = "searchedAsin") String asin,
            Model model
    ) {
        Product result = productService.getProductByAsin(asin);
        model.addAttribute("product", result);
        return "get-product-by-asin";
    }

    @GetMapping("/get-products-like")
    public String getProductsLike(Model model){
        model.addAttribute("activePage", "get-products-like");
        return "get-products-like";
    }

    @GetMapping("/get-top-products")
    public String getTopProducts(Model model){
        model.addAttribute("activePage", "get-top-products");
        return "get-top-products";
    }

    @GetMapping("/get-similar-cheaper-products")
    public String getSimilarCheaperProduct(Model model){
        model.addAttribute("activePage", "get-similar-cheaper-products");
        return "get-similar-cheaper-products";
    }

    @GetMapping("/get-trolls")
    public String getTrolls(Model model){
        model.addAttribute("activePage", "get-trolls");
        return "get-trolls";
    }

    @GetMapping("/get-offers")
    public String getOffers(Model model){
        model.addAttribute("activePage", "get-offers");
        return "get-offers";
    }

}
