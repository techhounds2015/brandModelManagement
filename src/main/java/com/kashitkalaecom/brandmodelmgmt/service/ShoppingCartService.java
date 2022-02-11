package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.models.ShoppingCart;
import com.kashitkalaecom.brandmodelmgmt.repository.ProductRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.ShoppingCartRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.UserRepository;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart saveProducts(ShoppingCart shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = productRepository.getById(shoppingCartDTO.getProduct().getId());
        shoppingCart.setProduct(product);
       // shoppingCart.setUser(userRepository.findOne(1L));
        shoppingCart.setStatus(shoppingCartDTO.getStatus());
        shoppingCart.setDate(new Date());
        shoppingCart.setStock(shoppingCartDTO.getStock());
       // shoppingCart.setAmount(product.getSellingPrice() * shoppingCartDTO.getStock());

        return shoppingCartRepository.save(shoppingCart);
    }


    public List<ShoppingCart> findAll() {
//        return shoppingCartRepository.findAll();
        return shoppingCartRepository.findByStatus("NOT_PURCHASED");
    }

    public ShoppingCart updateProduct(ShoppingCart shoppingCartDTO, Long id) {
        ShoppingCart updateItem = shoppingCartRepository.getById(Long.valueOf(shoppingCartDTO.getProduct().getId()));
        updateItem.setStock(shoppingCartDTO.getStock());
       // updateItem.setAmount(updateItem.getProduct().getUnitPrice() * shoppingCartDTO.getStock());
        return shoppingCartRepository.save(updateItem);
    }

    public void deleteProduct(Long id) {
       // shoppingCartRepository.delete(id);
    }

    public void clearShoppingCart(Object object) {
       // shoppingCartRepository.delete(findAll());
    }


    public List<ShoppingCart> findByPurchased() {
        return shoppingCartRepository.findByStatus("PURCHASED");
    }


    public void purchaseProducts(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(id);
        shoppingCart.setStatus("PURCHASED");
        shoppingCartRepository.save(shoppingCart);
    }
}