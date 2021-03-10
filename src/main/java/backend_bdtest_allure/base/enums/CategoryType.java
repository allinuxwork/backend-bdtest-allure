package backend_bdtest_allure.base.enums;

import lombok.Getter;

public enum CategoryType {
	FOOD(1, "Food"), ELECTRONIC(2, "Electronic"), DOOB(3, "Doob");

	@Getter
	private final Integer id;
	@Getter
	private final String title;

	CategoryType(int id, String title) {
		this.id = id;
		this.title = title;
	}
}
