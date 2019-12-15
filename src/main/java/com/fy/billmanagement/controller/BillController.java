package com.fy.billmanagement.controller;

import com.fy.billmanagement.entities.Bill;
import com.fy.billmanagement.entities.BillProvider;
import com.fy.billmanagement.entities.Provider;
import com.fy.billmanagement.mapper.BillMapper;
import com.fy.billmanagement.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账单管理模块
 */
@Controller
public class BillController {

    @Autowired
    BillMapper billMapper;
    @Autowired
    ProviderMapper providerMapper;

    @RequestMapping("/bills")
    public String toList(Model model, BillProvider billProvider){
        //查询所有的供应商
        List<Provider> providers = providerMapper.getProviders(null);
        //查询账单信息
        List<Bill> billProviders = billMapper.getBillProvider(billProvider);
        model.addAttribute("billProviders",billProviders);
        model.addAttribute("providers", providers);
        //回显
        model.addAttribute("billName",billProvider.getBillName());
        model.addAttribute("pid",billProvider.getPid());
        model.addAttribute("pay",billProvider.getPay());
        return "bill/list";
    }

    /**
     * 进入添加页面
     * @return
     */
    @GetMapping("/bill")
    public String getBill(Model model){
        //查询所有的供应商
        List<Provider> providers = providerMapper.getProviders(null);
        model.addAttribute("providers",providers);
        return "bill/add";
    }

    /**
     * 添加账单
     * @param bill
     * @return
     */
    @PostMapping("/bill")
    public String addBill(Bill bill){
        billMapper.addBill(bill);
        return "redirect:/bills";
    }

    /**
     * 根据type值选择进入view页面还是update页面
     * @param id
     * @param type
     * @param model
     * @return
     */
    @GetMapping("/bill/{bid}")
    public String viewBill(@PathVariable("bid") Integer id,
                           @RequestParam(value = "type", defaultValue = "view")String type, Model model){
        BillProvider billProvider = billMapper.getBillProviderByBid(id);
        //如果进入更新页面,就先查询所有的供应商
        if("update".equals(type)){
            List<Provider> providers = providerMapper.getProviders(null);
            model.addAttribute("providers", providers);
        }
        model.addAttribute("billProvider", billProvider);
        return "bill/" + type;
    }

    /**
     * 修改账单
     * @param bill
     * @return
     */
    @PostMapping("/updateBill")
    public String updateBill(Bill bill){
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    /**
     * 删除账单
     * @param bid
     * @return
     */
    @PostMapping("/bill/{bid}")
    public String delateBill(@PathVariable("bid") Integer bid){
        billMapper.delateBill(bid);
        return "redirect:/bills";
    }
}
