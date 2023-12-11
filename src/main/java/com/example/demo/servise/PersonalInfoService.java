package com.example.demo.servise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Companytable;
import com.example.demo.entity.Personalinfotable;
import com.example.demo.mapper.CompanytableMapper;
import com.example.demo.mapper.PersonalinfotableMapper;
import com.example.demo.model.BusinessCardModel;

@Service
public class PersonalInfoService {

	@Autowired
	PersonalinfotableMapper personalInfo;
	@Autowired
	CompanytableMapper companytable;
	@Autowired
	CompanyService companyService;

	public List<BusinessCardModel> selectAll() {

		List<Personalinfotable> tables = personalInfo.selectAll();
		List<BusinessCardModel> result = new ArrayList<>();

		for (Personalinfotable table : tables) {
			BusinessCardModel model = new BusinessCardModel();
			model.setBusinessCardId(table.getBusinesscardid());
			model.setCreateDate(table.getCreatedate());
			model.setName(table.getName());
			model.setCompany(companyService.findById(table.getCompanyid()).getCompany());
			model.setDepartment(table.getDepartment());
			model.setPost(table.getPost());
			model.setAddress(table.getAddress());
			model.setTel(table.getTel());
			model.setMail(table.getMail());
			model.setProjectTitle(table.getProjecttitle());
			model.setRemarks(table.getRemarks());
			result.add(model);
		}

		return result;
	}

	public BusinessCardModel findById(int id) {
		Personalinfotable table = personalInfo.findById(id);
		
		if (table == null) {
	        return null; // もしデータが見つからなければnullを返すか、適切なデフォルト値を設定するなどして対応
	    }
		
		BusinessCardModel model = new BusinessCardModel();
		
		model.setBusinessCardId(table.getBusinesscardid());
		model.setCreateDate(table.getCreatedate());
		model.setName(table.getName());
		model.setCompany(companyService.findById(table.getCompanyid()).getCompany());
		model.setDepartment(table.getDepartment());
		model.setPost(table.getPost());
		model.setAddress(table.getAddress());
		model.setTel(table.getTel());
		model.setMail(table.getMail());
		model.setProjectTitle(table.getProjecttitle());
		model.setRemarks(table.getRemarks());
				
		return model;
	}

	public int insertSelective(Personalinfotable table) {
		return personalInfo.insertSelective(table);
	}

	public int updateByPrimaryKey(Personalinfotable table) {
		return personalInfo.updateByPrimaryKey(table);
	}

	public int deleteByPrimaryKey(@RequestParam int id) {
		return personalInfo.deleteByPrimaryKey(id);
	}

	public List<BusinessCardModel> mySelectByExample(@RequestParam String keyword) {
		List<Personalinfotable> tables = personalInfo.mySelectByExample(keyword);
		List<BusinessCardModel> result = new ArrayList<>();
		
		for (Personalinfotable table : tables) {
			  BusinessCardModel model = new BusinessCardModel();
				model.setBusinessCardId(table.getBusinesscardid());
				model.setCreateDate(table.getCreatedate());
				model.setName(table.getName());
				model.setCompany(companyService.findById(table.getCompanyid()).getCompany());
				model.setDepartment(table.getDepartment());
				model.setPost(table.getPost());
				model.setAddress(table.getAddress());
				model.setTel(table.getTel());
				model.setMail(table.getMail());
				model.setProjectTitle(table.getProjecttitle());
				model.setRemarks(table.getRemarks());			
				result.add(model);				
		}

		return result;
	}

	public Personalinfotable saveBusinessCard(BusinessCardModel model) {
		Personalinfotable personalInfo = new Personalinfotable();
		Companytable companyteburu = new Companytable(); 

		// 会社テーブルに会社データが存在するかの確認
		Companytable RegisteredCompany = null;
		List<Companytable> companys = companyService.selectAll();
		for (Companytable company : companys) {
			if (model.getCompany().equals(company.getCompany())) {
				RegisteredCompany = company;
			}
		}
		Integer companyId = 0; // 取得したIDを保存する変数

		/*
		 * 同じidがテーブル内に存在しなければ追加処理。同じ値がある場合は更新処理
		 */
		if (model.getBusinessCardId() == null) {  // 新規登録処理
			// 会社が存在すればIDを取得。存在しなければ先に会社を登録する判定処理
			if (RegisteredCompany != null) {				
				Companytable newCompany = companyService.findById(RegisteredCompany.getCompanyid());
				companyId = newCompany.getCompanyid();
			} else {	
				companyteburu.setCompany(model.getCompany());
				companyService.insertSelective(companyteburu);
				companyId = companyteburu.getCompanyid();
			}
			// 各フィールドに値を設定
			personalInfo.setBusinesscardid(model.getBusinessCardId());
			personalInfo.setCreatedate(LocalDate.now());
			personalInfo.setName(model.getName());
			personalInfo.setCompanyid(companyId);
			personalInfo.setDepartment(model.getDepartment());
			personalInfo.setPost(model.getPost());
			personalInfo.setAddress(model.getAddress());
			personalInfo.setTel(model.getTel());
			personalInfo.setMail(model.getMail());
			personalInfo.setProjecttitle(model.getProjectTitle());
			personalInfo.setRemarks(model.getRemarks());

			insertSelective(personalInfo);
			return personalInfo;

			// 更新処理
		} else {
			// 会社が存在すればIDを取得。存在しなければ先に会社を登録する判定処理
			if (RegisteredCompany != null) {
				Companytable newCompany = companyService.findById(RegisteredCompany.getCompanyid());
				companyId = newCompany.getCompanyid();
			} else {		
				companyteburu.setCompany(model.getCompany());
				companyService.insertSelective(companyteburu);
				Companytable newdata = companyService.findById(companyteburu.getCompanyid());
				companyId = newdata.getCompanyid();
			}

			// 個人情報テーブルに書く情報を格納（さっき取得したIDもつかって一つずつちまちま）
			// 各フィールドに値を設定
			personalInfo.setBusinesscardid(model.getBusinessCardId());
			personalInfo.setCreatedate(LocalDate.now());
			personalInfo.setName(model.getName());
			personalInfo.setCompanyid(companyId);
			personalInfo.setDepartment(model.getDepartment());
			personalInfo.setPost(model.getPost());
			personalInfo.setAddress(model.getAddress());
			personalInfo.setTel(model.getTel());
			personalInfo.setMail(model.getMail());
			personalInfo.setProjecttitle(model.getProjectTitle());
			personalInfo.setRemarks(model.getRemarks());
			
			updateByPrimaryKey(personalInfo);
			companyService.updateByPrimaryKey(companyteburu);
			
			return personalInfo;
			
		}

	}
}
