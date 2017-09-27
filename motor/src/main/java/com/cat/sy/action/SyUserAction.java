package com.cat.sy.action;

/// ***********************import begin***********************
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.aspect.Anno;
import com.cat.common.bean.EOperation;
import com.cat.common.bean.Message;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.common.safe.RMd5;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyUser;
import com.cat.sy.service.SyRoleUserService;
import com.cat.sy.service.SyUserService;

/// ***********************import end*************************
@RequestMapping("sy/user")
@Controller
@Scope("prototype")
public class SyUserAction extends WebBaseAction {

	@Autowired
	private SyUserService syUserService;

    @Autowired
    private SyRoleUserService syRoleUserService;

  
	/**
	 * 请求访问路径
	 */
	private static final String requestPath = "sy/user/";

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
		return requestPath + "syUserList";
	}

	/**
	 * 新增页面
	 */
	@RequestMapping("goAdd")
	public String goAdd() {
		setAttributes();
		request.setAttribute("operate", EOperation.Add.value());
		return requestPath + "syUserForm";
	}

	/**
	 * 查看页面
	 */
	@RequestMapping("goView")
	public String goView() {
		setAttributes();
		request.setAttribute("operate", EOperation.View.value());
		return requestPath + "syUserForm";
	}

	/**
	 * 编辑页面
	 */
	@RequestMapping("goEdit")
	public String goEdit() {
		setAttributes();
		request.setAttribute("operate", EOperation.Update.value());
		return requestPath + "syUserForm";
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
				SyUser entity = new SyUser();
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
	private void save(SyUser entity) throws RequestException {
		Integer id = syUserService.save(entity);
		if (id > 0) {
			printWriterJson(new RResult(RResult.MSG_SUCCESS));
			return;
		}
		_logger.info(" save fail");
		printWriterJson(new RResult(RResult.MSG_FAIL));
	}

	/**
	 * 修改逻辑
	 * @throws RequestException 
	 */
	private void update(String id, SyUser syUser) throws RequestException {
		SyUser oldEntity = syUserService.load(Integer.parseInt(id));
		if (null == oldEntity) {
			_logger.info("update update fail record not exist, param[id=" + id + "]");
			printWriterJson(new RResult(RResult.MSG_FAIL, RResult.recordNotExist, id));
			return;
		}
		SyUser newEntity = new SyUser();
		RReflectUtils.getObjectForObject(newEntity, oldEntity);
		Map<String, Object> map = getRequestParameterMap();
		RReflectUtils.getObjectForMap(newEntity, map);

		if (!oldEntity.getPassword().equals(newEntity.getPassword())) {
			newEntity.setPassword(RMd5.encode(newEntity.getPassword()));// 修改了密码，进行加密处理
		}

		boolean info = syUserService.update(newEntity);
		if (info) {
			printWriterJson(new RResult(RResult.MSG_SUCCESS));
		} else {
			printWriterJson(new RResult(RResult.MSG_FAIL));
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("doDelete")
	public void doDelete() {
		try {
			String ids = getRequestParameter("ids");
			if (RString.isBlank(ids)) {
				printWriterJson(new RResult(RResult.MSG_FAIL, RResult.recordNotExist));
				_logger.info("doDelete  is empty ids");
				return;
			}
			String[] values = RString.split(ids, ",");
			boolean bool = false;
			if (values.length > 1) {
				bool = syUserService.deleteByIds(ids);
			} else {
				bool = syUserService.delete(Integer.parseInt(ids));
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
		_logger.info("load id=" + id);
		if (RString.isBlank(id)) {
			printWriterJson(new RResult(RResult.MSG_FAIL, RResult.recordNotExist));
			_logger.info("load id is empty ");
			return;
		}
		SyUser entity = syUserService.load(Integer.parseInt(id));
//		result = RJson.getJson(entity);
		getPrintWriter().write(RJson.getJsonStr(entity));
	}

	/**
	 * 列表查询
	 */
	@Anno("列表查询A")
	@RequestMapping("findPageList")
	public void findPageList() {
		try {
			Map<String, Object> map = getRequestParameterMap();
			SyUser entity = new SyUser();
			RReflectUtils.getObjectForMap(entity, map);
			PageControlInfo pageInfo = syUserService.findPageList(entity, getPage(), getEnd(), loadOsearch());
			printWriterJson(pageInfo);
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login() {
		String userName = getRequestParameter("userName");
		String password = getRequestParameter("password");
		String checkCode = getRequestParameter("checkCode");
		SyUser syUser = null;
		_logger.info("login in userName=" + userName + ",password=" + password);
		// 已经在系统页面内，不通过登录页面进入
		if (RString.isBlank(userName) || RString.isBlank(password) || RString.isBlank(checkCode)) {
			syUser = (SyUser) session.getAttribute("syUser");
			if (null == syUser) {
				return "redirect:/login.jsp";
			}
			return "sy/main/main";// 进入主页面
		}
		// 通过登录页面进入系统，登录校验
		String message = checkLogin(userName, password, checkCode);
		if ("".equals(message)) {
			syUser = getSyUser(userName, password);
			syUser.setIdNumber(null);
			syUser.setPassword(null);
			session.setAttribute("syUser", syUser);

			session.removeAttribute("randCode");
			session.removeAttribute("message");
			return "sy/main/main";
		}
		session.setAttribute("message", message);
		return "redirect:/login.jsp";
	}

	private SyUser getSyUser(String userName, String password) {
		SyUser syUser = new SyUser();
		syUser.setUserName(userName);
		syUser.setPassword(RMd5.encode(password));
		return syUserService.load(syUser);
	}

	/**
	 * 登录验证
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param checkCode
	 *            验证码
	 * @return
	 */
	public String checkLogin(String userName, String password, String checkCode) {
		String message = "";
		String randCode = (String) session.getAttribute("randCode");
		if (RString.isBlank(randCode)) {
			return message;
		}
		if (!checkCode.equals(randCode)) {
			message = Message.LOGIN_RANDCODE.toString();
			return message;
		}

		SyUser syUser = getSyUser(userName, password);
		if (null == syUser) {
			message = Message.LOGIN_FAILURE.toString();
			return message;
		}

		if (!"1".equalsIgnoreCase(syUser.getStatus())) {
			return Message.LOGIN_FAILURE.toString();
		}
		return message;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("syUser");
		session.removeAttribute("randCode");
		return "redirect:/login.jsp";
	}

	/**
	 * 重置用户密码
	 */
	@RequestMapping("resetPassword")
	public void resetPassword() {
		try {
			String id = getRequestParameter("id");
			if (RString.isBlank(id)) {
				printWriterJson(new RResult(RResult.MSG_FAIL, RResult.recordNotExist));
				_logger.info("resetPassword  is empty id");
				return;
			}
			
			boolean f = syUserService.resetPassword(Integer.parseInt(id));
			if (f) {
				printWriterJson(new RResult(RResult.MSG_SUCCESS));
			} else {
				printWriterJson(new RResult(RResult.MSG_FAIL));
			}
		} catch (Exception e) {
			_logger.error(e);
		}
	}

	
	/**
    * 修改用户密码
    */
   @RequestMapping("updatePassword")
   public void updatePassword() {
      try {
         SyUser sy = getSessionUser();
         if (null == sy) {
            return;
         }
         String password = getRequestParameter("password");
         String newPassword = getRequestParameter("newPassword");
         String new2Password = getRequestParameter("new2Password");
         
         if(RString.isBlank(password,newPassword,new2Password)){
            printWriterJson(new RResult(RResult.MSG_FAIL, RResult.paramNull));
            _logger.info("resetPassword  is empty id");
            return;
         }
         
         if(!newPassword.trim().equals(new2Password.trim())){
            printWriterJson(new RResult(RResult.MSG_FAIL, "两次输入密码不一致"));
            return;
         }
         
         Integer f = syUserService.updatePassword(sy.getId(),password,newPassword);
         if (f > 0) {
            printWriterJson(new RResult(RResult.MSG_SUCCESS));
         } else {
            printWriterJson(new RResult(RResult.MSG_FAIL,"旧密码不正确"));
         }
      } catch (Exception e) {
         _logger.error(e);
      }
   }
}
