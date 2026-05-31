package com.hoodinahi.store.product;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hoodinahi.store.product.ProductRepository;

import jakarta.validation.ReportAsSingleViolation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
        @RequestParam (required = false, defaultValue = "", name = "categoryId") Byte categoryID
    ){
        List<Product> products;
        if(categoryID != null){
            products = productRepository.findByCategoryId(categoryID);
        }else{
            products = productRepository.findAllWithCategory();
        }
        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }
    
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
        @RequestBody ProductDto request,
        UriComponentsBuilder uriComponentsBuilder){

        var categoryId = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(categoryId == null){
            return ResponseEntity.badRequest().build();
        }
        var prodcut = productMapper.toEntity(request);
        prodcut.setCategory(categoryId);
        productRepository.save(prodcut);
        request.setId(prodcut.getId());

        var productDtoResponse = productMapper.toDto(prodcut);
        var uri = uriComponentsBuilder.path("/product/{id}").buildAndExpand(prodcut.getId()).toUri();

        return ResponseEntity.created(uri).body(productDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
        @PathVariable(name = "id") Long id,
        @RequestBody ProductDto request
    ){
        var categoryId = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(categoryId == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }

        productMapper.update(request, product);
        product.setCategory(categoryId);
        productRepository.save(product);
        request.setId(product.getId());

        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable(name = "id") Long id
    ){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
    
}
