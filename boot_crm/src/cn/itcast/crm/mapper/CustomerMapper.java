package cn.itcast.crm.mapper;

import java.util.List;

import cn.itcast.crm.pojo.Customer;
import cn.itcast.crm.pojo.QueryVo;

public interface CustomerMapper {
	

	/**
	 * 根据queryVo分页查询数据
	 * 
	 * @param queryVo
	 * @return
	 */
	List<Customer> queryCustomerByQueryVo(QueryVo queryVo);

	/**
	 * 根据queryVo查询数据条数
	 * 
	 * @param queryVo
	 * @return
	 */
	int queryCountByQueryVo(QueryVo queryVo);
	
	/**
	 * 根据选中的客户ID，查询该客户进行数据回显
	 * 
	 * @param customer
	 * @return
	 */
	Customer queryCustomerById(Long id);
	
	/**
	 * 根据修改后的数据，保存到数据库中
	 * 
	 * @param customer
	 */
	void updateCustomer(Customer customer);
	
	/**
	 * 根据ID删除客户
	 * @param id
	 */
	void deleteCustomer(Long id);

}
