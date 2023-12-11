package com.example.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Companytable;
import com.example.demo.mapper.CompanytableMapper;
import com.example.demo.model.BusinessCardModel;
import com.example.demo.servise.CompanyService;

@SpringBootTest
@ActiveProfiles("unit")
public class CompanyServiceTests {

	@Autowired
	CompanyService companyService;
	@Autowired
	CompanytableMapper companyMapper;

	/*
	 * selectAll() 全件検索
	 */
	@Test
	@DisplayName("初期画面の全件検索")
	void test01() {
		// 実行
		List<Companytable> result = companyService.selectAll();

		// 取得件数の検証
		assertEquals(2, result.size());
		// 取得した値の検証
		assertThat(result, hasItem(hasProperty("companyid", equalTo(1))));
		assertThat(result, hasItem(hasProperty("company", equalTo("株式会社ABC"))));
		assertThat(result, hasItem(hasProperty("companyid", equalTo(2))));
	}

	/*
	 * findById() idが一致するものだけに絞り込む検索 idが「0」のとき
	 */
	@Test
	@DisplayName("idが一致するものに絞り込む")
	void test02() {
		// 引数の定義
		int id = 0;
		BusinessCardModel model = new BusinessCardModel();
		model.setBusinessCardId(id);

		// 実行
		Companytable result = companyService.findById(id);

		// 検証_取得件数が0件
		assertNull(result);
	}

	/*
	 * findById() idが一致するものだけに絞り込む検索 idが「1」のとき
	 */
	@Test
	@DisplayName("idが一致するものに絞り込む")
	void test03() {
		// 引数の定義
		int id = 1;
		Companytable expectedTable = new Companytable();
		expectedTable.setCompanyid(id);
		expectedTable.setCompany("株式会社ABC");

		// 実行
		Companytable result = companyService.findById(id);

		// 検証_取得した値
		assertNotNull(result);

		assertEquals(id, result.getCompanyid());
		assertEquals("株式会社ABC", result.getCompany());
	}

	/*
	 * insertSelective() 追加処理
	 */
	@Test
	@DisplayName("データの追加")
	void test04() {
		// 引数の定義
		Companytable companytable = new Companytable();
		companytable.setCompanyid(3);
		companytable.setCompany("ABC Company");

		// 実行
		int result = companyService.insertSelective(companytable);

		// 検証
		assertEquals(1, result);
		assertEquals(3, companyMapper.findById(3).getCompanyid());
		assertEquals("ABC Company", companyMapper.findById(3).getCompany());
	}

	/*
	 * updateByPrimaryKey() 追加処理
	 */
	@Test
	@DisplayName("データの更新")
	void test05() {
		// 事前のデータの取得
		Companytable expectedTable = companyMapper.findById(3);
		String originalName = expectedTable.getCompany();
		expectedTable.setCompany("ABCソリューションズ");
		// 実行
		int result = companyService.updateByPrimaryKey(expectedTable);

		System.out.println("After Update: " + companyMapper.findById(3).getCompany());

		// 検証_処理回数
		assertEquals(1, result);
		// 検証_変更された値
		assertEquals(3, companyMapper.findById(3).getCompanyid());
		assertEquals("ABCソリューションズ", companyMapper.findById(3).getCompany());

		// 検証_更新前後の値が異なる
		Companytable updatedData = companyMapper.findById(3);
		assertNotEquals(originalName, updatedData.getCompany());
		// 検証_変更されていないレコードの確認
		assertEquals("株式会社ABC", companyMapper.findById(1).getCompany());
		assertEquals("株式会社あいうえお", companyMapper.findById(2).getCompany());
	}
}
