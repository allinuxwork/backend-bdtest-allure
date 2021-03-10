package backend_bdtest_allure;

import static backend_bdtest_allure.base.enums.CategoryType.DOOB;
import static backend_bdtest_allure.base.enums.CategoryType.ELECTRONIC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import backend_bdtest_allure.db.dao.CategoriesMapper;
import backend_bdtest_allure.db.model.Categories;
import backend_bdtest_allure.db.model.CategoriesExample;
import backend_bdtest_allure.util.DbUtils;

public class CategoryTests {
	private long categoriesCount;
	static CategoriesMapper categoriesMapper;

	@BeforeAll
	static void beforeAll() throws MalformedURLException {
		categoriesMapper = DbUtils.getCategoriesMapper();
	}

	@DisplayName("Получение колличество категорий")
	@Test
	void countCategoriesNumberTest() {
		categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
		assertThat(categoriesCount).isNotEqualTo(0);
	}

	@DisplayName("Получение название категорий")
	@Test
	void getCategoriesNameTest() {
		List<Categories> categoriesName = categoriesMapper.selectByExample(new CategoriesExample());
		categoriesName.forEach(item -> assertThat(item.getTitle(), true));
	}

	@DisplayName("Проверка имени категории")
	@Test
	void getCategoryByIdTest() {
		Categories nameCat = categoriesMapper.selectByPrimaryKey(2);
		assertThat(nameCat.getTitle()).isEqualTo(ELECTRONIC.getTitle());
	}

	@DisplayName("Проверка имени не существующей категории")
	@Test
	void getCategoryByIdNegativeTest() {
		Categories nameCat = categoriesMapper.selectByPrimaryKey(3);
		assertThat(nameCat.getTitle()).isNotEqualTo(DOOB.getTitle());
	}
}
