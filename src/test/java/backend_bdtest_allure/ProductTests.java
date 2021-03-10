package backend_bdtest_allure;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import backend_bdtest_allure.db.dao.ProductsMapper;
import backend_bdtest_allure.db.model.Products;
import backend_bdtest_allure.db.model.ProductsExample;
import backend_bdtest_allure.util.DbUtils;
import lombok.SneakyThrows;

public class ProductTests {
	static ProductsMapper productsMapper;

	@SneakyThrows
	@BeforeAll
	static void beforeAll() {
		productsMapper = DbUtils.getProductsMapper();
	}

	@DisplayName("Изменение цены продукта с id 3646")
	@Test
	void changeProducPriceTest() {
		Products product = productsMapper.selectByPrimaryKey(3646L);
		product.setPrice(500);
		ProductsExample productsExample = new ProductsExample();
		productsExample.createCriteria().andIdEqualTo(3646L);
		productsMapper.updateByExample(product, productsExample);
		assertThat(productsMapper.selectByPrimaryKey(3646L).getPrice(), CoreMatchers.is(500));
	}

	@DisplayName("Получение продуктов из базы данных")
	@Test
	void getProductsTest() {
		List<Products> products = productsMapper.selectByExample(new ProductsExample());
		products.forEach(item -> assertThat(item.getTitle(), true));
	}
}
