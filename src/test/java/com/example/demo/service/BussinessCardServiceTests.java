//package com.example.demo;
//
//import static org.hamcrest.Matchers.hasProperty;
//import static org.hamcrest.CoreMatchers.allOf;
//import static org.hamcrest.CoreMatchers.hasItem;
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.example.demo.entity.Personalinfotable;
//import com.example.demo.mapper.PersonalinfotableMapper;
//import com.example.demo.servise.BussinessCardService;
//
//@SpringBootTest
//@ActiveProfiles("unit")
//public class BussinessCardServiceTests {
//
//	@Autowired
//	BussinessCardService service;
//	@Autowired
//	PersonalinfotableMapper mapper;
//	@Mock
//	Personalinfotable table;
//
//	
//	/*
//	 * selectAll() 全件検索
//	 */
//	@Test
//	@DisplayName("初期画面の全件検索")
//	void test01() {
//		// 実行
//		List<Personalinfotable> result = service.selectAll();
//
//		// 取得件数の検証
//		assertEquals(2, result.size());
//		// 取得した値の検証
//		assertThat(result, hasItem(hasProperty("businesscardid", equalTo(1))));
//		assertThat(result, hasItem(hasProperty("name", equalTo("斎藤　太郎"))));
//		assertThat(result, hasItem(hasProperty("company", equalTo("株式会社ABC"))));
//		assertThat(result, hasItem(hasProperty("department", equalTo("営業部"))));
//		assertThat(result, hasItem(hasProperty("post", equalTo("部長"))));
//		assertThat(result, hasItem(hasProperty("address", equalTo("東京都新宿区123-456"))));
//		assertThat(result, hasItem(hasProperty("tel", equalTo("080-1111-9999"))));
//		assertThat(result, hasItem(hasProperty("mail", equalTo("saito.taro@gmail.com"))));
//		assertThat(result, hasItem(hasProperty("projecttitle", equalTo("商品開発について"))));
//		assertThat(result, hasItem(hasProperty("remarks", equalTo("特徴：メガネをかけている"))));
//		
//		assertThat(result, hasItem(hasProperty("businesscardid", equalTo(2))));
//	}
//	
//	
//	/*
//	 * findById() idが一致するものだけに絞り込む検索
//	 * idが「0」のとき
//	 */
//	@Test
//	@DisplayName("idが一致するものに絞り込む")
//	void test02() {
//		// 引数の定義
//		int id = 0;
//		Personalinfotable expectedTable = new Personalinfotable();
//		expectedTable.setBusinesscardid(id);
//
//		// 実行
//		Personalinfotable result = service.findById(id);
//
//		// 検証_取得件数が0件
//		assertEquals(null, result);
//	}
//	
//
//	/*
//	 * findById() idが一致するものだけに絞り込む検索
//	 * idが「1」のとき
//	 */
//	@Test
//	@DisplayName("idが一致するものに絞り込む")
//	void test03() {
//		// 引数の定義
//		int id = 1;
//		Personalinfotable expectedTable = new Personalinfotable();
//		expectedTable.setBusinesscardid(id);
//		expectedTable.setName("斎藤　太郎");
//
//		// 実行
//		Personalinfotable result = service.findById(id);
//		
//		//　検証_取得した値
//		assertNotNull(result);
//		
//		assertEquals(id, result.getBusinesscardid());
//		assertEquals("斎藤　太郎", result.getName());
//		assertEquals("株式会社ABC", result.getCompany());
//		assertEquals("営業部", result.getDepartment());
//		assertEquals("部長", result.getPost());
//		assertEquals("東京都新宿区123-456", result.getAddress());
//		assertEquals("080-1111-9999", result.getTel());
//		assertEquals("saito.taro@gmail.com", result.getMail());
//		assertEquals("商品開発について", result.getProjecttitle());
//		assertEquals("特徴：メガネをかけている", result.getRemarks());
//
//	}
//	
//	
//
//
//	/*
//	 * insertSelective() 追加処理
//	 */
//	@Test
//	@DisplayName("データの追加")
//	void test04() {
//		// 引数の定義
//		Personalinfotable expectedTable = new Personalinfotable();
//		expectedTable.setBusinesscardid(3);
//		expectedTable.setName("佐藤健太");
//		expectedTable.setCompany("ABC Company");
//		expectedTable.setDepartment("総務部");
//		expectedTable.setPost("アルバイト");
//		expectedTable.setAddress("東京都港区123-456");
//		expectedTable.setTel(null);
//		expectedTable.setMail("sato@gmail.com");
//		expectedTable.setProjecttitle("新しいプロジェクトタイトル");
//		expectedTable.setRemarks("マッシュヘアーの大学生");
//
//		// 実行
//		int result = service.insertSelective(expectedTable);
//
//		// 検証
//		assertEquals(1, result);
//		assertEquals(3, mapper.findById(3).getBusinesscardid());
//		assertEquals("佐藤健太", mapper.findById(3).getName());
//		assertEquals("ABC Company", mapper.findById(3).getCompany());
//		assertEquals("総務部", mapper.findById(3).getDepartment());
//		assertEquals("アルバイト", mapper.findById(3).getPost());
//		assertEquals("東京都港区123-456", mapper.findById(3).getAddress());
//		assertEquals(null, mapper.findById(3).getTel());
//		assertEquals("sato@gmail.com", mapper.findById(3).getMail());
//		assertEquals("新しいプロジェクトタイトル", mapper.findById(3).getProjecttitle());
//		assertEquals("マッシュヘアーの大学生", mapper.findById(3).getRemarks());
//	}
//
//	/*
//	 * updateByPrimaryKey() 追加処理
//	 */
//	@Test
//	@DisplayName("データの更新")
//	void test05() {
//		// 事前のデータの取得
//		Personalinfotable expectedTable = mapper.findById(3);
//		String originalName = expectedTable.getName();
//		expectedTable.setName("佐藤　ケンタ");
//
//		System.out.println("Before Update: " + mapper.findById(3).getName());
//
//		// 実行
//		int result = service.updateByPrimaryKey(expectedTable);
//
//		System.out.println("After Update: " + mapper.findById(3).getName());
//
//		// 検証_処理回数
//		assertEquals(1, result);
//		//　検証_変更された値
//		assertEquals(3, mapper.findById(3).getBusinesscardid());
//	    assertEquals("佐藤　ケンタ", mapper.findById(3).getName());
//	    assertEquals("ABC Company", mapper.findById(3).getCompany());
//	    assertEquals("総務部", mapper.findById(3).getDepartment());
//	    assertEquals("アルバイト", mapper.findById(3).getPost());
//	    assertEquals("東京都港区123-456", mapper.findById(3).getAddress());
//	    assertEquals(null, mapper.findById(3).getTel());
//	    assertEquals("sato@gmail.com", mapper.findById(3).getMail());
//	    assertEquals("新しいプロジェクトタイトル", mapper.findById(3).getProjecttitle());
//	    assertEquals("マッシュヘアーの大学生", mapper.findById(3).getRemarks());
//		//　検証_更新前後の値が異なる
//		Personalinfotable updatedData = mapper.findById(3);
//		assertNotEquals(originalName, updatedData.getName());
//		//　検証_変更されていないレコードの確認
//		assertEquals("斎藤　太郎", mapper.findById(1).getName());
//		assertEquals("田中　次郎", mapper.findById(2).getName());
//	}
//
//	/*
//	 * deleteByPrimaryKey() 追加処理
//	 */
//	@Test
//	@DisplayName("データの削除")
//	void test06() {
//		// 引数の定義
//		int id = 1;
//		Personalinfotable expectedTable = new Personalinfotable();
//		expectedTable.setBusinesscardid(id);
//
//		// 実行
//		int result = service.deleteByPrimaryKey(id);
//
//		// 検証_処理回数う
//		assertEquals(1, result);
//		// 検証_削除されているか
//		assertEquals(null, mapper.findById(1));
//		// 検証_登録されている件数
////		assertEquals(2, expectedTable);
//		assertEquals(2, mapper.selectAll().size()); 
//		// 検証_削除されていないレコードの確認
//		assertEquals(2, mapper.findById(2).getBusinesscardid());
//	    assertEquals("田中　次郎", mapper.findById(2).getName());
//	    assertEquals("株式会社あいうえお", mapper.findById(2).getCompany());
//	    assertEquals("経理部", mapper.findById(2).getDepartment());
//	    assertEquals("課長", mapper.findById(2).getPost());
//	    assertEquals("大阪府大阪888", mapper.findById(2).getAddress());
//	    assertEquals("070-5555-7777", mapper.findById(2).getTel());
//	    assertEquals("tanaka.jiro@gmail.com", mapper.findById(2).getMail());
//	    assertEquals("経費削減の相談", mapper.findById(2).getProjecttitle());
//	    assertEquals("", mapper.findById(2).getRemarks());
//	    
//	    assertEquals("佐藤　ケンタ", mapper.findById(3).getName());
//	}
//	
//	
//	/*
//	 * mySelectByExample() 部分一致するものを絞り込む
//	 * 取得件数が0件の場合
//	 */
//	@Test
//	@DisplayName("条件検索_取得件数が0件の場合")
//	void test07() {
//		// 検索キーワードを定義
//		String keyword = "大橋";
//
//		// 実行
//		List<Personalinfotable> result = service.mySelectByExample(keyword);
//
//		// 検証
//		assertEquals(0, result.size());
//		
//	}
//
//	/*
//	 * mySelectByExample() 部分一致するものを絞り込む
//	 * 取得件数が一件の場合
//	 */
//	@Test
//	@DisplayName("条件検索_1件取得")
//	void test08() {
//		// 検索キーワードを定義
//		String keyword = "田中";
//
//		// 実行
//		List<Personalinfotable> result = service.mySelectByExample(keyword);
//
//		// 検証_検索結果が1件
//		assertEquals(1, result.size());
//	    
//	 // 検証_リスト内のいづれかの要素が部分一致していることを確認
//	    for (Personalinfotable card : result) {
//            assertTrue(card.getName().contains(keyword) ||
//                       card.getCompany().contains(keyword) ||
//                       card.getDepartment().contains(keyword) ||
//                       card.getPost().contains(keyword) ||
//                       card.getAddress().contains(keyword) ||
//                       card.getTel().contains(keyword) ||
//                       card.getMail().contains(keyword) ||
//                       card.getProjecttitle().contains(keyword) ||
//                       card.getRemarks().contains(keyword));
//        }
//	    
//	    // 検証_入力値に「田中」を含むレコードを取得できている
//	    assertThat(result, hasItem(
//	        allOf(
//	            hasProperty("businesscardid", equalTo(2)),
//	            hasProperty("name", equalTo("田中　次郎")),
//	            hasProperty("company", equalTo("株式会社あいうえお")),
//	            hasProperty("department", equalTo("経理部")),
//	            hasProperty("post", equalTo("課長")),
//	            hasProperty("address", equalTo("大阪府大阪888")),
//	            hasProperty("tel", equalTo("070-5555-7777")),
//	            hasProperty("mail", equalTo("tanaka.jiro@gmail.com")),
//	            hasProperty("projecttitle", equalTo("経費削減の相談")),
//	            hasProperty("remarks", equalTo(""))
//	        )
//	    ));
//		
//	}
//	
//	
//	
//	/*
//	 * mySelectByExample() 部分一致するものを絞り込む
//	 * 取得件数が一件の場合
//	 */
//	@Test
//	@DisplayName("条件検索_複数件")
//	void test09() {
//		// 検索キーワードを定義
//		String keyword = "い";
//
//		// 実行
//		List<Personalinfotable> result = service.mySelectByExample(keyword);
//
//		// 検証_検索結果が2件
//		assertEquals(2, result.size());
//		
//	 // 検証_リスト内のいづれかの要素が部分一致していることを確認
//	    for (Personalinfotable card : result) {
//	        assertTrue(
//	            (card.getName() != null && card.getName().contains(keyword)) ||
//	            (card.getCompany() != null && card.getCompany().contains(keyword)) ||
//	            (card.getDepartment() != null && card.getDepartment().contains(keyword)) ||
//	            (card.getPost() != null && card.getPost().contains(keyword)) ||
//	            (card.getAddress() != null && card.getAddress().contains(keyword)) ||
////	            (card.getTel() != null && card.getTel().contains(keyword)) ||
//	            (card.getTel() != null && card.getTel().contains(keyword)) ||
//	            (card.getMail() != null && card.getMail().contains(keyword)) ||
//	            (card.getProjecttitle() != null && card.getProjecttitle().contains(keyword)) ||
//	            (card.getRemarks() != null && card.getRemarks().contains(keyword))
//	        );
//	    }
//	    
//	    // 検証_入力値に「田中」を含むレコードを取得できている
//	    assertThat(result, hasItem(
//	            allOf(
//	                hasProperty("businesscardid", equalTo(2)),
//	                hasProperty("name", equalTo("田中　次郎")),
//	                hasProperty("company", equalTo("株式会社あいうえお")),
//	                hasProperty("department", equalTo("経理部")),
//	                hasProperty("post", equalTo("課長")),
//	                hasProperty("address", equalTo("大阪府大阪888")),
//	                hasProperty("tel", equalTo("070-5555-7777")),
//	                hasProperty("mail", equalTo("tanaka.jiro@gmail.com")),
//	                hasProperty("projecttitle", equalTo("経費削減の相談")),
//	                hasProperty("remarks", equalTo(""))
//	            )
//	        ));
//
//	        assertThat(result, hasItem(
//	            allOf(
//	                hasProperty("businesscardid", equalTo(3)),
//	                hasProperty("name", equalTo("佐藤　ケンタ")),
//	                hasProperty("company", equalTo("ABC Company")),
//	                hasProperty("department", equalTo("総務部")),
//	                hasProperty("post", equalTo("アルバイト")),
//	                hasProperty("address", equalTo("東京都港区123-456")),
//	                hasProperty("tel", equalTo(null)),
//	                hasProperty("mail", equalTo("sato@gmail.com")),
//	                hasProperty("projecttitle", equalTo("新しいプロジェクトタイトル")),
//	                hasProperty("remarks", equalTo("マッシュヘアーの大学生"))
//	            )
//	        ));
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	/*
//	 * insertSelective() 追加処理
//	 * IDが1を追加
//	 */
////	@Test
////	@DisplayName("データの追加")
////	void test03() {
////		// 引数の定義
////		Personalinfotable expectedTable = new Personalinfotable();
////		expectedTable.setBusinesscardid();
////		expectedTable.setName("斎藤　太郎");
////		expectedTable.setCompany("株式会社ABC");
////		expectedTable.setDepartment("営業部");
////		expectedTable.setPost("部長");
////		expectedTable.setAddress("東京都新宿区123-456");
////		expectedTable.setTel("080-1111-9999");
////		expectedTable.setMail("saito.taro@gmail.com");
////		expectedTable.setProjecttitle("商品開発について");
////		expectedTable.setRemarks("特徴：メガネをかけている");
////
////		// 実行
////		int result = service.insertSelective(expectedTable);
////
////		// 検証
////		assertEquals(1, result);
////		assertEquals(3, mapper.findById(3).getBusinesscardid());
////	}
////
//}
//package com;


