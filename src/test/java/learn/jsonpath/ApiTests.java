package learn.jsonpath;

import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import models.Product;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert.*;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiTests {
    @Test
    public void getCategories() {
        String endpoint = "http://localhost:80/api_testing/category/read.php";
        var response = given().when().get(endpoint).then().log().headers();
        response.assertThat().header("Content-Type", "application/json; charset=UTF-8");
//        System.out.println(jsonPath.getString("records[0].name"));
//        // 可关闭的http client客户端
//        CloseableHttpClient client = HttpClients.createDefault();
//
//        // 构造http get请求对象
//        HttpGet httpGet = new HttpGet(endpoint);
//
//        // 响应对象
//        CloseableHttpResponse response;
//
//        try {
//            response = client.execute(httpGet);
//
//            // 获取响应结果
//            HttpEntity entity = response.getEntity();
//
//            // 工具类，对 HttpEntity 操作的工具类
//            String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
//            System.out.println(JsonPath.parse(result).read("$.records[0].name").toString());
//
//            // 确保流关闭
//            EntityUtils.consume(entity);
//        } catch (ClientProtocolException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response =
                given().
                        param("id", 2).
                when().
                        get(endpoint).
                then().log().body();

        // assert status
        // response.assertThat().statusCode(200);
        // assert field
        // response.assertThat().body("id", Matchers.equalTo("2"));
        // assert field
        // response.assertThat().body("name", Matchers.equalTo("Cross-Back Training Tank"));
    }

    @Test
    public void createProduct() {
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        String body = "{\"name\": \"Water Bottle\", \"description\": \"Blue water bottle. Hods 64 ounces\", \"price\": 12, \"category_id\": 3}";
        var response = given().contentType(ContentType.JSON).body(new File("./src/main/resources/create_product.json")).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct() {
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body = "{\"id\": 19, \"name\": \"Water Bottle\", \"description\": \"Blue water bottle. Hods 64 ounces\", \"price\": 15, \"category_id\": 3}";
        var response = given().body(body).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = "{\"id\": 19}";
        var response = given().
                formParam("id", 19).when().delete(endpoint).then();
        response.log().body();
    }

    @Test
    public void createSerializedProduct() {
        // serialize object to body parameter
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product(
                "Water Bottle",
                "Blue water bottle. Holds 64 ounces",
                12,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void getDeserializedProduct() {
        // deserialize body to object
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        Product actualProduct = given().queryParam("id", "2").when().get(endpoint).
                as(Product.class);
        assertThat(actualProduct.getId(), is(2));
        System.out.println(actualProduct.getName());
        System.out.println(actualProduct.getId());
    }
}
