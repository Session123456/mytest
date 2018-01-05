package cn.itcast.crm.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.crm.pojo.BaseDict;
import cn.itcast.crm.pojo.Customer;
import cn.itcast.crm.pojo.QueryVo;
import cn.itcast.crm.service.BaseDictService;
import cn.itcast.crm.service.CustomerService;
import cn.itcast.crm.utils.Page;
import sun.print.resources.serviceui;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Value("${CUSTOMER_FROM_TYPE}")
	private String CUSTOMER_FROM_TYPE_CODE;
	@Value("${CUSTOMER_INDUSTRY_TYPE}")
	private String CUSTOMER_INDUSTRY_TYPE_CODE;
	@Value("${CUSTOMER_LEVEL_TYPE}")
	private String CUSTOMER_LEVEL_TYPE_CODE;
	
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("list")
	public String list(QueryVo queryVo,Model model){
		
		// 解决get请求乱码问题
		if (StringUtils.isNotBlank(queryVo.getCustName())) {
			try {
				// 重新编码解决乱码问题
				queryVo.setCustName(new String(queryVo.getCustName().getBytes("ISO-8859-1"), "UTF-8"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 根据类别代码查询客户来源
		List<BaseDict> fromType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_FROM_TYPE_CODE);
		// 根据类别代码查询所属行业
		List<BaseDict> industryType = this.baseDictService
				.queryBaseDictByDictTypeCode(this.CUSTOMER_INDUSTRY_TYPE_CODE);
		// 根据类别代码查询客户级别
		List<BaseDict> levelType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_LEVEL_TYPE_CODE);
		// 把客户来源放到Model，传给前台页面
		model.addAttribute("fromType", fromType);
		// 把所属行业放到Model，传给前台页面
		model.addAttribute("industryType", industryType);
		// 把客户级别放到Model，传给前台页面
		model.addAttribute("levelType", levelType);
		
		// 调用service服务根据条件查询用户
		Page<Customer> page = this.customerService.queryCustomerByQueryVo(queryVo);

		// 把查询到的用户数据放到Model中，传递给前台页面
		model.addAttribute("page", page);

		// 回显查询条件
		// 客户名称名称
		model.addAttribute("custName", queryVo.getCustName());
		// 客户来源
		model.addAttribute("custSource", queryVo.getCustSource());
		// 所属行业
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		// 客户级别
		model.addAttribute("custLevel", queryVo.getCustLevel());
		return "customer";
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public Customer edit(Long id){
		
		Customer customer = this.customerService.queryCustomerById(id);
		return customer;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(Customer customer){
		
		this.customerService.updateCustomer(customer);
		return "客户信息修改成功";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public void delete(Long id){
		customerService.deleteCustomer(id);
	}
}
