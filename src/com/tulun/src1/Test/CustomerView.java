package com.tulun.src1.Test;


public class CustomerView {
	public CustomerList customerList = new CustomerList(10);
	public CustomerView(){
		
	}
	
	public void enterMainMenu(){
		boolean isFlag = true;
		while(isFlag){
			System.out.println("----------客户信息管理软件----------");
			System.out.println("            1 添加客户");
			System.out.println("            2 修改客户");
			System.out.println("            3 删除客户");
			System.out.println("            4 客户列表");
			System.out.println("            5 退       出");
			System.out.println();
			System.out.print("       请选择（1-5）：");
			char meun = CMUtility.readMenuSelection();
			switch(meun){
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.print("确认是否退出（Y/N）:");
				char isExit = CMUtility.readConfirmSelection();
				if(isExit=='Y'){
					isFlag = false;
					
				}
			}
			
		}
		
	}
	private void addNewCustomer(){//添加客户信息
		
	}
	private void modifyCustomer(){//修改客户信息
		
	}
	private void deleteCustomer(){//删除客户信息
		
	}
	private void listAllCustomers(){//遍历客户列表
		System.out.println("----------客户列表----------");
		int total = customerList.getTotal();
		if(total == 0){
			System.out.println("没有客户记录");
		}else{
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t邮箱");
			Customer[] cuts = customerList.getAllCustomers();
			for(int i=0 ; i<total;i++){
				Customer cust=cuts[i];
				System.out.println((i+1)+"\t"+cust.getName()+"\t"+cust.getGender()+
						"\t"+cust.getAge()+"\t"+cust.getPhone()+"\t"+cust.getEmail());
			}
		}	
		System.out.println("----------客户列表完成----------");
	}
	public static void main(String[] args){
		Customer customer = new Customer("杨林", '男', 22, "18312341234", "@qq.com");

		CustomerView view = new CustomerView();
		view.customerList.addCustomer(customer);
		view.enterMainMenu();
		
	}	
}
