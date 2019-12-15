package com.fy.billmanagement.controller;

import com.fy.billmanagement.entities.Provider;
import com.fy.billmanagement.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class providerController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderMapper providerMapper;

    /**
     * 查询供应商列表
     * @param model
     * @param providerName
     * @return
     */
    @GetMapping("/providers")
    public String providerList(Model model, @RequestParam(value = "providerName", required = false) String providerName){
        logger.info("providerName :"+providerName);
        Provider provider = new Provider();
        provider.setProviderName(providerName);
        List<Provider> providers = providerMapper.getProviders(provider);
        model.addAttribute("providers",providers);
        model.addAttribute("providerName",providerName); //作为搜索框的回显
        return "provider/list";
    }

    /**
     * 查询供应商详情
     * @param pid
     * @param model
     * @return
     */
    @GetMapping("/view/{pid}")
    public String providerView(@PathVariable("pid") Integer pid, Model model,
                               @RequestParam(value = "type", defaultValue = "view") String type){
        Provider provider = providerMapper.getProviderByPid(pid);
        model.addAttribute("provider", provider);
//        return "/provider/view";
        return "/provider/" + type;
    }

    /**
     * 修改供应商
     * @param provider
     * @return
     */
    @PostMapping(value = "/provider")
    public String updateProvider(Provider provider){
        logger.info("修改供应商信息...");
        provider.setProviderCode("p_666");
        providerMapper.updateProvider(provider);
        return "redirect:/provider";
    }

    /**
     * 进入添加页面
     * @return
     */
    @GetMapping("/provider")
    public String addProvider(){
        logger.info("进入添加供应商页面");
        return "provider/add";
    }

    /**
     * 添加供应商
     * @param provider
     * @return
     */
    @PostMapping("/addProvider")
    public String addEntity(Provider provider){
        logger.info("添加供应商");
        provider.setCreateDate(new Date());
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }

    /**
     * 删除供应商
     * @param pid
     * @return
     */
    @PostMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid){
        logger.info("删除供应商");
        providerMapper.deleteProviderByPid(pid);
        return "redirect:/providers";
    }

}
