package com.tulun.src1.Test;



public class CustomerList {
	private Customer[] customers;// 用来保存客户对象的数组
	private int total = 0;// 记录已保存客户对象的数量
	
	public CustomerList(int totalCustomer) {//构造器
		customers = new Customer[totalCustomer];//造一个新数组
		
	}
	public boolean addCustomer(Customer customer) {
		//将指定的用户添加到数组
		if(total >= customers.length){
			return false;
		}
		customers[total] = customer;
		total++;
		return true;
	}
	public boolean replaceCustomer(int index, Customer cust){
	//用参数customer替换数组中由index指定的对象
		if(index<0 && index >= total){
			return false;
		}
		customers[index]=cust;
		return true;
		
	}
	public boolean deleteCustomer(int index){
		//删除指定位置的客户
		if(index<0 && index >= 0){
			return false;
		}
		for(int i = index; i< index-1; i++){
			customers[i]=customers[i+1];
		}
		//最后一个元素需要置空
		customers[total-1]=null;
		total--;
		return true;
		
	}
	public Customer[] getAllCustomers() {
		//返回数组中记录的所有客户对象
		Customer[] cuts = new Customer[total];
		for(int i=0;i<total;i++){
			customers[i]=cuts[i];
		}
		return cuts;
	}
	public Customer getCustomer(int index){
		//获取指定索引位置的客户
		if(index<0 && index>=total){
			return null;
		}
		return customers[index];
		
	}
	public int getTotal(){
		//获取客户数量
		return total;
	}

}
