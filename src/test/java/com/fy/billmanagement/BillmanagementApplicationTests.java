package com.fy.billmanagement;

import com.fy.billmanagement.entities.Bill;
import com.fy.billmanagement.mapper.BillMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillmanagementApplicationTests {

	@Autowired
	BillMapper billMapper;
	@Test
	public void contextLoads() {
//		Bill bill1 = new Bill();
//		bill1.setBillName("com");
//		List<Bill> bills = billMapper.getBill(bill1);
//		System.out.println(bills.toString());

	}

}
