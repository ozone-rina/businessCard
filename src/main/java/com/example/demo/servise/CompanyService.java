package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Companytable;
import com.example.demo.mapper.CompanytableMapper;

@Service
public class CompanyService {

	@Autowired
	CompanytableMapper company;

	public List<Companytable> selectAll() {
		return company.selectAll();
	}

	public Companytable findById(int id) {
		return company.findById(id);
	}

	public int insertSelective(Companytable string) {
		return company.insertSelective(string);
	}

	public int updateByPrimaryKey(Companytable table) {
		return company.updateByPrimaryKey(table);
	}

//	public int deleteByPrimaryKey(@RequestParam int id) {
//		return company.deleteByPrimaryKey(id);
//	}

//	public List<Companytable> mySelectByExample(String keyword) {
//		return company.mySelectByExample(keyword);
//	}

}
