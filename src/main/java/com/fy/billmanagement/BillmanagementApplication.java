package com.fy.billmanagement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.fy.billmanagement.mapper")
@SpringBootApplication
public class BillmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillmanagementApplication.class, args);
	}

}
