package ru.kuzmin;

import api.ProductService;
import dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest {
    static ProductService productService;
    Product product;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }
    @BeforeEach
    void setUp() {
        product = new Product()
                .withId(1)
                .withTitle("Milk")
                .withCategoryTitle("Food");
    }

    @SneakyThrows
    @Test
    void PutProductTest() {
        Response<Product> response = productService.modifyProduct(product.withPrice(90)).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.body().getPrice(), CoreMatchers.is(90));
    }
}
