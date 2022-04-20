package ru.kuzmin;

import api.ProductService;
import dto.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import utils.RetrofitUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostProductTest {
    static ProductService productService;
    Product product;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle("Sausage")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 100));
    }

    @SneakyThrows
    @Test
    void postProductTest() {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
        assertThat(response.body().getTitle(), equalTo("Sausage"));
    }
}
