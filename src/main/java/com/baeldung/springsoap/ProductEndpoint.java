package com.baeldung.springsoap;

import com.baeldung.springsoap.gen.AddProductRequest;
import com.baeldung.springsoap.gen.AddProductResponse;
import com.baeldung.springsoap.gen.DeleteProductRequest;
import com.baeldung.springsoap.gen.DeleteProductResponse;
import com.baeldung.springsoap.gen.GetProductListResponse;
import com.baeldung.springsoap.gen.GetProductRequest;
import com.baeldung.springsoap.gen.GetProductResponse;
import com.baeldung.springsoap.gen.Product;
import com.baeldung.springsoap.gen.UpdateProductRequest;
import com.baeldung.springsoap.gen.UpdateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";

    private ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductListRequest")
    @ResponsePayload
    public GetProductListResponse getProductList() {
        GetProductListResponse response = new GetProductListResponse();
        response.setProducts(productRepository.getAllProducts());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productRepository.findProductById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
    @ResponsePayload
    public AddProductResponse addProduct(@RequestPayload AddProductRequest request) {
        AddProductResponse response = new AddProductResponse();

        Product product = new Product();
        product.setName(request.getName());
        product.setExtension(request.getExtension());
        product.setPrice(request.getPrice());
        product.setSellNumber(request.getSellNumber());

        response.setProduct(productRepository.save(product));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request) {
        UpdateProductResponse response = new UpdateProductResponse();

        Product product = new Product();
        product.setName(request.getName());
        product.setExtension(request.getExtension());
        product.setPrice(request.getPrice());
        product.setSellNumber(request.getSellNumber());

        response.setProduct(productRepository.update(request.getId(), product));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProduct(@RequestPayload DeleteProductRequest request) {
        DeleteProductResponse response = new DeleteProductResponse();
        response.setDeleted(productRepository.delete(request.getId()));
        return response;
    }
}
