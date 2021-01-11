package com.baeldung.springsoap;

import com.baeldung.springsoap.gen.AddProductRequest;
import com.baeldung.springsoap.gen.AddProductResponse;
import com.baeldung.springsoap.gen.DeleteProductRequest;
import com.baeldung.springsoap.gen.DeleteProductResponse;
import com.baeldung.springsoap.gen.GetProductListRequest;
import com.baeldung.springsoap.gen.GetProductListResponse;
import com.baeldung.springsoap.gen.GetProductRequest;
import com.baeldung.springsoap.gen.GetProductResponse;
import com.baeldung.springsoap.gen.Product;
import com.baeldung.springsoap.gen.UpdateProductRequest;
import com.baeldung.springsoap.gen.UpdateProductResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(AddProductRequest.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(DeleteProductRequest.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(UpdateProductRequest.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetProductListResponse.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetProductListRequest.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(AddProductResponse.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(DeleteProductResponse.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(UpdateProductResponse.class));
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetProductResponse.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void getProductById() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetProductRequest request = new GetProductRequest();
        request.setId(1);

        Object actual =  ws.marshalSendAndReceive("http://localhost:8081/ws", request);
        assertThat(actual).isNotNull();
    }

    @Test
    public void getProductList() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetProductListRequest request = new GetProductListRequest();

        Object actual = ws.marshalSendAndReceive("http://localhost:8081/ws", request);
        assertThat(actual).isNotNull();
    }


    @Test
    public void addProduct() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        AddProductRequest request = new AddProductRequest();
        request.setName("123");
        request.setExtension("123");
        request.setPrice(123.0);
        request.setSellNumber(1);

        Object actual = ws.marshalSendAndReceive("http://localhost:8081/ws", request);
        assertThat(actual).isNotNull();
    }

    @Test
    public void deleteProduct() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        DeleteProductRequest request = new DeleteProductRequest();
        request.setId(1);

        Object actual = ws.marshalSendAndReceive("http://localhost:8081/ws", request);
        assertThat(actual).isNotNull();
    }

    @Test
    public void updateProduct() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        UpdateProductRequest request = new UpdateProductRequest();
        request.setId(1);
        request.setName("new name");
        request.setExtension("new extension");
        request.setPrice(123.0);
        request.setSellNumber(123);

        Object actual = ws.marshalSendAndReceive("http://localhost:8081/ws", request);
        assertThat(actual).isNotNull();
    }
}
