package backend_bdtest_allure.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import backend_bdtest_allure.db.dao.CategoriesMapper;
import backend_bdtest_allure.db.dao.ProductsMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DbUtils {
	private static String resource = "mybatisConfig.xml";

	@SneakyThrows
	public static CategoriesMapper getCategoriesMapper() {
		return getSqlSession().getMapper(CategoriesMapper.class);
	}

	@SneakyThrows
	public static ProductsMapper getProductsMapper() {
		return getSqlSession().getMapper(ProductsMapper.class);
	}

	private static SqlSession getSqlSession() throws IOException {
		SqlSessionFactory sqlSessionFactory;
		InputStream is = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sqlSessionFactory.openSession(true);
		return session;
	}

}
