package com.ruoyi.crm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.crm.domain.Customer;
import com.ruoyi.crm.service.ICustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 *
 * @author ruoyi
 * @date 2020-06-28
 */
@Controller
@RequestMapping("/crm/customer")
public class CustomerController extends BaseController {
    private String prefix = "crm/customer";

    @Autowired
    private ICustomerService customerService;

    @RequiresPermissions("crm:customer:view")
    @GetMapping()
    public String customer() {
        return prefix + "/customer";
    }

    /**
     * 查询客户信息列表
     */
    @RequiresPermissions("crm:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Customer customer) {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @RequiresPermissions("crm:customer:export")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer) {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 新增客户信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息
     */
    @RequiresPermissions("crm:customer:add")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Customer customer) {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改客户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Customer customer = customerService.selectCustomerById(id);
        mmap.put("customer", customer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息
     */
    @RequiresPermissions("crm:customer:edit")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Customer customer) {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除客户信息
     */
    @RequiresPermissions("crm:customer:remove")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }
}
