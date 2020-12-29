package geekbrains;

import geekbrains.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class JsonProductTest {

    @Autowired
    private JacksonTester<Product> jacksonProd;

    @Test
    public void jsonSerializationProductTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Bread");
        product.setPrice(new BigDecimal("15.13"));
        assertThat(this.jacksonProd.write(product)).hasJsonPathNumberValue("$.id");
        assertThat(this.jacksonProd.write(product)).extractingJsonPathStringValue("$.title").isEqualTo("Bread");
        assertThat(this.jacksonProd.write(product)).hasJsonPathNumberValue("$.price");
    }

    @Test
    public void jsonDeserializationProductTest() throws Exception {
        String content = "{\"id\": 1,\"title\":\"Cheese\",\"price\": \"320.00\"}";
        Product realProduct = new Product();
        realProduct.setId(2L);
        realProduct.setTitle("Cheese");
        realProduct.setPrice(new BigDecimal("320.00"));
        assertThat(this.jacksonProd.parseObject(content).getId()).isEqualTo(1L);
        assertThat(this.jacksonProd.parseObject(content).getTitle()).isEqualTo("Cheese");
        assertThat(this.jacksonProd.parseObject(content).getPrice()).isEqualTo("320.00");
    }
}
