package com.example.demo.entity;

public class Companytable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column companytable.companyid
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    private Integer companyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column companytable.company
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    private String company;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column companytable.companyid
     *
     * @return the value of companytable.companyid
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column companytable.companyid
     *
     * @param companyid the value for companytable.companyid
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column companytable.company
     *
     * @return the value of companytable.company
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column companytable.company
     *
     * @param company the value for companytable.company
     *
     * @mbg.generated Thu Nov 30 11:47:25 JST 2023
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}