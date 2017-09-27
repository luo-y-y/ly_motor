package com.cat.sy.service.impl;

/// ***********************import begin***********************
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;
import com.cat.common.lang.RUuid;
import com.cat.sy.bean.SyRole;
import com.cat.sy.dao.SyRoleDao;
import com.cat.sy.service.SyRoleService;

/// ***********************import end*************************
@Service("syRoleService")
public class SyRoleServiceImpl implements SyRoleService {

	public Logger _logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SyRoleDao syRoleDao;

	// / ***********************define begin***********************
	// / ***********************define end*************************
	@Override
	public Integer save(SyRole syRole) {
		if (null == syRole) {
			_logger.info("save {0} is empty  syRole");
			return null;
		}
		syRole.setUuid(RUuid.makeUuid());
		syRole.setCreateTime(RDate.getCurrentTime());
		Map<String, Object> map = syRoleDao.save(syRole);
		Integer id = Integer.parseInt(map.get("id").toString());
		return id;
	}

	@Override
	public boolean delete(Integer id) {
		if (null == id) {
			_logger.info("delete  id is empty ");
			return false;
		}
		SyRole entity = load(id);
		if (null == entity) {
			_logger.info("deleterecord not exist.");
			return false;
		}
		Integer result = syRoleDao.delete(id);
		
		return result > 0;
	}

	@Override
	public boolean deleteByIds(String ids) {
		if (RString.isBlank(ids)) {
			_logger.info("deleteByIds ids is empty");
			return false;
		}
		String[] array = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String id : array) {
			list.add(Integer.parseInt(id));
		}
		Integer result = syRoleDao.deleteByIds(list);
		return result > 0;
	}

	@Override
	public boolean update(SyRole syRole) {
		if (null == syRole) {
			_logger.info("update SyRole is empty");
			return false;
		}
		Integer id = syRole.getId();
		if (null == id) {
			_logger.info("update SyRole.id is empty");
			return false;
		}
		Integer result = syRoleDao.update(syRole);
		
		return result > 0;
	}

	@Override
	public  RResult update(SyRole oldEntity, SyRole newEntity) {
		if (null == newEntity) {
			_logger.info("update param is empty");
			return new RResult(RResult.MSG_FAIL, RResult.paramNull, "newEntity");
		}
		
		Integer result = syRoleDao.update(newEntity);
		if(result > 0){
			return new RResult(RResult.MSG_SUCCESS);
		}else{
			return new RResult(RResult.MSG_FAIL);
		}
	}

	@Override
	public SyRole load(Integer id) {
		if (null == id) {
			_logger.info("load id is empty");
			return null;
		}
		SyRole syRole = new SyRole();
		syRole.setId(id);
		return syRoleDao.load(syRole);
	}

	@Override
	public SyRole load(SyRole syRole) {
		if (null == syRole) {
			_logger.info("load  syRole is empty");
			return null;
		}
		return syRoleDao.load(syRole);
	}

	@Override
	public Integer loadCount(SyRole SyRole) {
		return syRoleDao.loadCount(SyRole);
	}

	@Override
	public boolean isExist(SyRole SyRole) {
		Integer count = loadCount(SyRole);
		if (null == count) {
			return false;
		}
		return count.intValue() > 0;
	}

	@Override
	public List<SyRole> findList(SyRole syRole, Integer skip, Integer max, Map<String, Object> params) {
		return syRoleDao.findList(syRole, skip, max, params);
	}

	@Override
	public PageControlInfo findPageList(SyRole syRole, Integer skip, Integer max, Map<String, Object> params) {
		return syRoleDao.findPageList(syRole, skip, max, params);
	}

	@Override
	public PageControlInfo findPageHsList(SyRole syRole, Integer skip, Integer max, Map<String, Object> params) {
		return syRoleDao.findPageHsList(syRole, skip, max, params);
	}


	// / ***********************method begin***********************
	@Override
	public SyRole loadByCode(String code) {
		SyRole syRole = new SyRole();
		syRole.setCode(code);
		return syRoleDao.load(syRole);
	}
	// / ***********************method end*************************

}
