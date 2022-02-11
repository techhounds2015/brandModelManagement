package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kashitkalaecom.brandmodelmgmt.models.ShoppingCart;
import com.kashitkalaecom.brandmodelmgmt.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addProductItem")
    public ShoppingCart addProductItem(@RequestBody ShoppingCart shoppingCartDTO) {
        return shoppingCartService.saveProducts(shoppingCartDTO);
    }


    @GetMapping("/getAllProductItems")
    public List<ShoppingCart> getAll(){
        return shoppingCartService.findAll();
    }

    @PutMapping("/updateProductItem")
    public ShoppingCart updateProductItem(@RequestBody ShoppingCart shoppingCartDTO, @PathVariable("id") Long ids) {
        return shoppingCartService.updateProduct(shoppingCartDTO, ids);
    }

    @DeleteMapping("/updateProductItem/{id}")
    public void deleteProductItem(@PathVariable("id") Long ids) {
        shoppingCartService.deleteProduct(ids);
    }

    @DeleteMapping("/clearCart")
    public void ClearCart( Object object) {
        shoppingCartService.clearShoppingCart(object);
    }

}