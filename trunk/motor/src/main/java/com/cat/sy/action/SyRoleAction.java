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
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyRole;
import com.cat.sy.bean.SyUser;
import com.cat.sy.service.SyRoleService;

/// ***********************import end*************************
@RequestMapping("sy/role")
@Controller
@Scope("prototype")
public class SyRoleAction extends WebBaseAction {

	@Autowired
	private SyRoleService syRoleService;
	/**
	 * 请求访问路径
	 */
	private static final String requestPath = "sy/role/";

	// / ***********************define begin***********************
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
		return requestPath + "syRoleList";
	}

	/**
	 * 新增页面
	 */
	@RequestMapping("goAdd")
	public String goAdd() {
		setAttributes();
		request.setAttribute("operate", EOperation.Add.value());
		return requestPath + "syRoleForm";
	}

	/**
	 * 查看页面
	 */
	@RequestMapping("goView")
	public String goView() {
		setAttributes();
		request.setAttribute("operate", EOperation.View.value());
		return requestPath + "syRoleForm";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("goEdit")
	public String goEdit() {
		setAttributes();
		request.setAttribute("operate", EOperation.Update.value());
		return requestPath + "syRoleForm";
	}

	/**
	 * 历史列表页面
	 * 
	 * @return
	 */
	@RequestMapping("goViewHistory")
	public String goViewHistory() {
		setAttributes();
		return requestPath + "syRoleHsList";
	}

	/**
	 * 历史页面
	 * 
	 * @return
	 */
	@RequestMapping("goViewHistoryForm")
	public String goViewHistoryForm() {
		setAttributes();
		request.setAttribute("operate", EOperation.History.value());
		return requestPath + "syRoleForm";
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
				SyRole entity = new SyRole();
				RReflectUtils.getObjectForMap(entity, map);
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
	private void save(SyRole entity) throws RequestException {
		if (null != isExitsCode(entity.getCode())) {
			printWriterJson(new RResult(RResult.MSG_FAIL, "角色代码重复"));
			return;
		}
		Integer id = syRoleService.save(entity);
		if (id > 0) {
			printWriterJson(new RResult(RResult.MSG_SUCCESS));
			return;
		}
		_logger.info(" save fail");
		printWriterJson(new RResult(RResult.MSG_FAIL));
	}

	private SyRole isExitsCode(String code) {
		SyRole role = new SyRole();
		role.setCode(code);
		return syRoleService.load(role);
	}

	/**
	 * 修改逻辑
	 * 
	 * @throws RequestException
	 */
	private void update(String id, SyUser syUser) throws RequestException {
		SyRole oldEntity = syRoleService.load(Integer.parseInt(id));
		if (null == oldEntity) {
			_logger.info("update update fail record not exist, param[id=" + id
					+ "]");
			printWriterJson(new RResult(RResult.MSG_FAIL,
					RResult.recordNotExist, id));
			return;
		}
		SyRole newEntity = new SyRole();
		RReflectUtils.getObjectForObject(newEntity, oldEntity);
		Map<String, Object> map = getRequestParameterMap();
		RReflectUtils.getObjectForMap(newEntity, map);

		SyRole syRole = isExitsCode(newEntity.getCode());
		if (null != syRole
				&& !id.equalsIgnoreCase(RString.toString(syRole.getId()))) {
			// 根据角色代码获取到相同的，并且ID 不相同
			printWriterJson(new RResult(RResult.MSG_FAIL, "角色代码重复"));
			return;
		}

		RResult info = syRoleService.update(oldEntity, newEntity);
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
				printWriterJson(new RResult(RResult.MSG_FAIL,
						RResult.recordNotExist));
				_logger.info("doDelete  is empty ids");
				return;
			}
			String[] values = RString.split(ids, ",");
			boolean bool = false;
			if (values.length > 1) {
				bool = syRoleService.deleteByIds(ids);
			} else {
				bool = syRoleService.delete(Integer.parseInt(ids));
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
	 * 
	 * @throws RequestException
	 */
	@RequestMapping("load")
	public void load() throws RequestException {
		String id = getRequestParameter("id");
		_logger.info("load id=" + id);
		if (RString.isBlank(id)) {
			printWriterJson(new RResult(RResult.MSG_FAIL,
					RResult.recordNotExist));
			_logger.info("load id is empty ");
			return;
		}
		SyRole entity = syRoleService.load(Integer.parseInt(id));
//		result = RJson.getJson(entity);
		getPrintWriter().write(RJson.getJsonStr(entity));
	}

	/**
	 * 列表查询
	 */
	@RequestMapping("findList")
	public void findList() {
		try {
			Map<String, Object> map = getRequestParameterMap();
			SyRole entity = new SyRole();
			RReflectUtils.getObjectForMap(entity, map);
			Map<String, Object> search = loadOsearch();
			List<SyRole> list = syRoleService.findList(entity, getPage(),
					getEnd(), search);
			printWriterJson(list);
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	/**
	 * 分页查询
	 */
	@RequestMapping("findPageList")
	public void findPageList() {
		try {
			Map<String, Object> map = getRequestParameterMap();
			SyRole entity = new SyRole();
			RReflectUtils.getObjectForMap(entity, map);
			Map<String, Object> search = loadOsearch();
			String text = RString.toString(search.get("text"));
			if (RString.isNotBlank(text)) {
				entity.setLabel(text);
			}
			PageControlInfo pageInfo = syRoleService.findPageList(entity,
					getPage(), getEnd(), null);
			printWriterJson(pageInfo);
		} catch (Exception e) {
			_logger.error(e);
		}
	}

}
