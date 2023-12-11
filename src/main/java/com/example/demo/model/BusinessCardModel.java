package com.example.demo.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BusinessCardModel {
	
		private Integer businessCardId;
	    private LocalDate createDate;
	    @NotBlank(message = "必須項目です。")
	    private String name;
	    private int companyid;
	    @NotBlank(message = "必須項目です。")
	    private String company;
	    private String department;
	    private String post;
	    private String address;
	    @Pattern(regexp = "(^0\\d{1,4}-\\d{1,4}-\\d{4})|^$", message = "※[-」を含めた形式で入力してください。")
	    private String tel;
	    @Email(message = "※正しいメールアドレスを入力してください。")
	    private String mail;
	    @NotBlank(message = "必須項目です。")
	    private String projectTitle;
	    private String remarks;
	    
		public Integer getBusinessCardId() {
			return businessCardId;
		}
		public void setBusinessCardId(Integer businessCardId) {
			this.businessCardId = businessCardId;
		}
		public LocalDate getCreateDate() {
			return createDate;
		}
		public void setCreateDate(LocalDate createDate) {
			this.createDate = createDate;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getPost() {
			return post;
		}
		public void setPost(String post) {
			this.post = post;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getProjectTitle() {
			return projectTitle;
		}
		public void setProjectTitle(String projectTitle) {
			this.projectTitle = projectTitle;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
		
		public int getCompanyid() {
			return companyid;
		}
		public void setCompanyid(int companyid) {
			this.companyid = companyid;
		}
	    
	    
}
