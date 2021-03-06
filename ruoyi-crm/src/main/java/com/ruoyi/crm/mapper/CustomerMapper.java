package com.ruoyi.crm.mapper;

import java.util.List;
import com.ruoyi.crm.domain.Customer;

/**
 * 客户信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-06-28
 */
public interface CustomerMapper {
    /**
     * 查询客户信息
     *
     * @param id 客户信息ID
     * @return 客户信息
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户信息列表
     *
     * @param customer 客户信息
     * @return 客户信息集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户信息
     *
     * @param customer 客户信息
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户信息
     *
     * @param customer 客户信息
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户信息
     *
     * @param id 客户信息ID
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerByIds(String[] ids);
}
