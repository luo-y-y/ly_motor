package com.cat.sy.service.impl;

/// ***********************import begin***********************
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.cat.common.safe.RMd5;
import com.cat.sy.bean.SyCatalogRole;
import com.cat.sy.bean.SyRoleUser;
import com.cat.sy.bean.SyUser;
import com.cat.sy.dao.SyUserDao;
import com.cat.sy.service.SyCatalogRoleService;
import com.cat.sy.service.SyRoleUserService;
import com.cat.sy.service.SyUserService;

/// ***********************import end*************************
@Service("syUserService")
public class SyUserServiceImpl implements SyUserService {

	public Logger _logger = Logger.getLogger(this.getClass());

	@Autowired
	private SyUserDao syUserDao;

	// / ***********************define begin***********************
	@Autowired
	private SyRoleUserService syRoleUserService;

	@Autowired
	private SyCatalogRoleService syCatalogRoleService;

	// / ***********************define end*************************
	@Override
	public Integer save(SyUser syUser) {
		if (null == syUser) {
			_logger.info("save syUser is empty");
			return null;
		}
		syUser.setCreateTime(RDate.getCurrentTime());
		syUser.setPassword(RMd5.encode(syUser.getPassword()));
		Map<String, Object> map = syUserDao.save(syUser);
		return Integer.parseInt(map.get("id").toString());
	}

	@Override
	public boolean delete(Integer id) {
		if (null == id) {
			_logger.info("delete  is empty ");
			return false;
		}
		SyUser entity = load(id);
		if (null == entity) {
			_logger.info("delete record not exist.");
			return false;
		}
		Integer result = syUserDao.delete(id);
		if (result > 0) {
		}
		return result > 0;
	}

	@Override
	public boolean deleteByIds(String ids) {
		if (RString.isBlank(ids)) {
			_logger.info("deleteByIds ids is empty");
			return false;
		}
		String[] array = ids.split(",");
		List<Long> list = new ArrayList<Long>();
		for (String id : array) {
			list.add(Long.valueOf(id));
		}
		Integer result = syUserDao.deleteByIds(list);
		return result > 0;
	}

	@Override
	public boolean update(SyUser syUser) {
		if (null == syUser) {
			_logger.info("update syUser is empty");
			return false;
		}
		Integer result = syUserDao.update(syUser);
		if (result > 0) {
		}
		return result > 0;
	}

	@Override
	public SyUser load(Integer id) {
		if (null == id) {
			_logger.info("load  id is empty");
			return null;
		}
		SyUser syUser = new SyUser();
		syUser.setId(id);
		return syUserDao.load(syUser);
	}

	@Override
	public SyUser load(SyUser syUser) {
		if (null == syUser) {
			_logger.info("load  is empty");
			return null;
		}
		return syUserDao.load(syUser);
	}

	@Override
	public Integer loadCount(SyUser syUser) {
		return syUserDao.loadCount(syUser);
	}

	@Override
	public boolean isExist(SyUser syUser) {
		Integer count = loadCount(syUser);
		if (null == count) {
			return false;
		}
		return count.intValue() > 0;
	}

	@Override
	public List<SyUser> findList(SyUser syUser, Integer skip, Integer max, Map<String, Object> params) {
		return syUserDao.findList(syUser, skip, max, params);
	}

	@Override
	public PageControlInfo findPageList(SyUser syUser, Integer skip, Integer max, Map<String, Object> params) {
		return syUserDao.findPageList(syUser, skip, max, params);
	}

	// / ***********************method begin***********************
	@Override
	public List<Integer> findRolesByUserId(Integer userId) {
		if (null == userId) {
			_logger.info("findRolesByUserId userId  is empty");
			return null;
		}

		List<Integer> roleList = new ArrayList<Integer>();
		// 根据用户I的 获取-绑定及解除的角色,
		List<SyRoleUser> syRoleUserList = syRoleUserService.findUserRole(userId, null);
		if (RList.isNotBlank(syRoleUserList)) {
			// 判断List不为空，避免出现空指针
			for (int i = 0; i < syRoleUserList.size(); i++) {
				SyRoleUser syRoleUser = syRoleUserList.get(i);
				// 用户-绑定角色，根据用户的状态来绑定对应权限
				if (RString.equals("B", syRoleUser.getStatus())) {
					roleList.add(syRoleUser.getRoleId());
				}
			}
		}

		return roleList;
	}

	@Override
	public String getCatalogPowerByUserId(Integer userId) {
		List<Integer> roleIdList = findRolesByUserId(userId);
		// 用户目录权限
		String catalogPower = ",";
		if (RList.isBlank(roleIdList)) {
			return catalogPower;
		}
		for (int i = 0; i < roleIdList.size(); i++) {
			// 一个角色只有一个权限
			SyCatalogRole syCatalogRole = syCatalogRoleService.loadByRoleId(roleIdList.get(i));
			if (null != syCatalogRole) {
				catalogPower += syCatalogRole.getPowerNodeIds();
			}
		}
		return catalogPower + ",";
	}

	@Override
	public SyUser loadByloginPassport(String userName) {
		if (RString.isBlank(userName)) {
			_logger.info("loadByloginPassport  is empty ");
			return null;
		}
		SyUser entity = new SyUser();
		entity.setUserName(userName);
		return syUserDao.load(entity);
	}

	/**
	 * 关闭状态
	 */
	private static final int Close_Status = 0;

	/**
	 * 开启状态
	 */
	private static final int Open_Status = 1;

	public void set(int i) {
		if (i == Close_Status) {
			// 做处理
		} else if (i == Open_Status) {
			// 做处理
		}
	}

	@Override
	public boolean resetPassword(Integer userId) {
		if (null == userId || 0 == userId) {
			return false;
		}
		String p = RSystemConfig.getValue("reset_password");
		if (RString.isBlank(p)) {
			p = "123456";
		}
		return syUserDao.updateForResetPassword(userId, RMd5.encode(p))>0;
	}

   @Override
   public Integer updatePassword(Integer userId,
                                 String password,
                                 String newPassword){
      password = RMd5.encode(password);
      newPassword = RMd5.encode(newPassword);
      return syUserDao.updatePassword(userId, password, newPassword);
   }

	// / ***********************method end*************************

}
