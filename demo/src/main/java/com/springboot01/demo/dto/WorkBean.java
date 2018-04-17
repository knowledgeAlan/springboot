package com.springboot01.demo.dto;

/**
 * create by zzm on 2018/4/16
 **/
public class WorkBean {
    /**
     * 工作人名称
     */
    private String userName;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 工作年限
     */
    private int workAge;
    /**
     * 薪资待遇
     */
    private double salary;

    /**
     * 无参构造
     */
    public WorkBean() {
        super();
    }

    /**
     * 姓名
     * @param userName
     */
    public WorkBean(String userName) {
        this.userName = userName;
    }

    /**
     * 姓名和公司名称的构造
     * @param userName
     * @param companyName
     */
    public WorkBean(String userName, String companyName) {
        this(userName);
        this.companyName = companyName;
    }

    /**
     * 工作
     */
    public void workAt(boolean flag) {
        System.out.println(this.userName+"在"+(flag?"上午":"下午")+"上午工作的时候一直在打瞌睡!");
    }

    /**
     * 工作
     * @param flag
     * @param message
     */
    public void workAt(boolean flag, String message) {
        System.out.println(this.userName+"在"+(flag?"上午":"下午")+message);
    }

    /**
     * 工作
     */
    public void workAt() {
        System.out.println(this.userName+"工作了好久了");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.userName + "在" + this.companyName + "工作了" + this.workAge + "年，现在的薪水是" + this.salary;
    }

}
