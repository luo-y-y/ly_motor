package com.cat.sy.action;

/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.common.bean.EOperation;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyRole;
import com.cat.sy.bean.SyRoleUser;
import com.cat.sy.bean.SyUser;
import com.cat.sy.service.SyRoleService;
import com.cat.sy.service.SyRoleUserService;
import com.cat.sy.service.SyUserService;

/// ***********************import end*************************
@RequestMapping("sy/RoleUser")
@Controller
@Scope("prototype")
public class SyRoleUserAction extends WebBaseAction {

	@Autowired
	private SyRoleUserService syRoleUserService;

	@Autowired
	private SyRoleService syRoleService;

	@Autowired
	private SyUserService syUserService;
	/**
	 * 请求访问路径
	 */
	private static final String requestPath = "sy/role/";

	// / ***********************define begin***********************
	@RequestMapping("goLov")
	public String goLov() {
		setAttributes();
		return requestPath + "syRoleUserLov";
	}

	// / ***********************define end*************************
	/**
	 * 介绍页面
	 */
	@RequestMapping("goIntroduce")
	public String goIntroduce() {
		setAttributes();
		return requestPath + "introduce";
	}

	/**
	 * 主页面
	 */
	@RequestMapping("goMain")
	public String goMain() {
		setAttributes();
		return requestPath + "syRoleUserList";
	}

	/**
	 * 新增页面
	 */
	@RequestMapping("goAdd")
	public String goAdd() {
		setAttributes();
		request.setAttribute("operate", EOperation.Add.value());
		return requestPath + "syRoleUserForm";
	}

	/**
	 * 查看页面
	 */
	@RequestMapping("goView")
	public String goView() {
		setAttributes();
		request.setAttribute("operate", EOperation.View.value());
		return requestPath + "syRoleUserForm";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("goEdit")
	public String goEdit() {
		setAttributes();
		request.setAttribute("operate", EOperation.Update.value());
		return requestPath + "syRoleUserForm";
	}

	/**
	 * 保存/更新
	 */
	@RequestMapping("doSave")
	public void doSave() {
		try {
			SyUser syUser = getSessionUser();
			String id = getRequestParameter("id");
			if (RString.isBlank(id) || id.equals("0")) {
				Map<String, Object> map = getRequestParameterMap();
				SyRoleUser entity = new SyRoleUser();
				RReflectUtils.getObjectForMap(entity, map);
				entity.setCreateUserId(syUser.getId());
				save(entity);
				return;
			}
			update(id, syUser);
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	/**
	 * 保存逻辑
	 * @throws RequestException 
	 */
	private void save(SyRoleUser entity) throws RequestException {
		SyRoleUser findEn = new SyRoleUser();
		findEn.setRoleId(entity.getRoleId());
		findEn.setUserId(entity.getUserId());
		List<SyRoleUser> findIsTrue = syRoleUserService.findList(findEn, null, null, null);
		if (RList.isNotBlank(findIsTrue)) {
			// 查询出来，用户和角色已经绑定
			printWriterJson(new RResult(RResult.MSG_FAIL, "该用户已绑定该角色。"));
			return;
		}
		Integer id = syRoleUserService.save(entity);
		if (id > 0) {
			printWriterJson(new RResult(RResult.MSG_SUCCESS));
			return;
		}
		_logger.info("save save fail");
		printWriterJson(new RResult(RResult.MSG_FAIL));
	}

	/**
	 * 修改逻辑
	 * @throws RequestException 
	 */
	private void update(String id, SyUser syUser) throws RequestException {
		SyRoleUser oldEntity = syRoleUserService.load(Integer.parseInt(id));
		if (null == oldEntity) {
			_logger.info("update update fail record not exist, param[id=" + id);
			printWriterJson(new RResult(RResult.MSG_FAIL, RResult.paramNull));
			return;
		}
		SyRoleUser newEntity = new SyRoleUser();
		RReflectUtils.getObjectForObject(newEntity, oldEntity);
		Map<String, Object> map = getRequestParameterMap();
		RReflectUtils.getObjectForMap(newEntity, map);

		RResult info = syRoleUserService.update(oldEntity, newEntity);
		printWriterJson(info);
	}

	/**
	 * 删除
	 */
	@RequestMapping("doDelete")
	public void doDelete() {
		try {
			String ids = getRequestParameter("ids");
			if (RString.isBlank(ids)) {
				printWriterJson(new RResult(RResult.MSG_FAIL, RResult.paramNull));
				_logger.info("doDeleteis empty ids");
				return;
			}
			String[] values = RString.split(ids, ",");
			boolean bool = false;
			if (values.length > 1) {
				bool = syRoleUserService.deleteByIds(ids);
			} else {
				bool = syRoleUserService.delete(Integer.parseInt(ids));
			}
			if (bool) {
				printWriterJson(new RResult(RResult.MSG_SUCCESS));
			} else {
				printWriterJson(new RResult(RResult.MSG_FAIL));
			}
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	/**
	 * 根据ID加载记录
	 * @throws RequestException 
	 */
	@RequestMapping("load")
	public void load() throws RequestException {
		String id = getRequestParameter("id");
		_logger.info("load  id=" + id);
		if (RString.isBlank(id)) {
			printWriterJson(new RResult(RResult.MSG_FAIL, RResult.paramNull));
			_logger.info("load  is empty id");
			return;
		}
		SyRoleUser entity = syRoleUserService.load(Integer.parseInt(id));
//		result = RJson.getJson(entity);
		getPrintWriter().write(RJson.getJsonStr(entity));
	}

	/**
	 * 列表查询
	 */
	@RequestMapping("findPageList")
	public void findPageList() {
		try {
			Map<String, Object> map = getRequestParameterMap();
			SyRoleUser entity = new SyRoleUser();
			RReflectUtils.getObjectForMap(entity, map);
			PageControlInfo pageInfo = syRoleUserService.findPageList(entity, getPage(), getEnd(), loadOsearch());
			formatPageInfo(pageInfo);
			printWriterJson(pageInfo);
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	// / ***********************method begin***********************
	@SuppressWarnings("unchecked")
	private void formatPageInfo(PageControlInfo pageInfo) {
		if (null == pageInfo) {
			return;
		}
		List<SyRoleUser> list = pageInfo.getSearchData();
		if (RList.isBlank(list)) {
			return;
		}

		for (SyRoleUser syRoleUser : list) {
			Integer userId = syRoleUser.getUserId();
			Integer roleId = syRoleUser.getRoleId();
			SyRole syRole = syRoleService.load(roleId);
			if (null != syRole) {
				syRoleUser.setRoleLabel(syRole.getLabel());
			}
			SyUser syUser = syUserService.load(userId);
			if (null != syUser) {
				syRoleUser.setUserName(syUser.getUserName());
			}
		}
	}
	// / ***********************method end*************************

}
