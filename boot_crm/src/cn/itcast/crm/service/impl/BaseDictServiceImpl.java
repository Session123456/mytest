package cn.itcast.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.crm.mapper.BaseDictMapper;
import cn.itcast.crm.pojo.BaseDict;
import cn.itcast.crm.service.BaseDictService;

@Service
public class BaseDictServiceImpl implements BaseDictService{

	@Autowired
	private BaseDictMapper baseDictMapper;
	
	@Override
	public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode) {
		// TODO Auto-generated method stub
		return baseDictMapper.queryBaseDictByDictTypeCode(dictTypeCode);
	}
	
}
