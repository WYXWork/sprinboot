package com.wolf.springboot.web.sys;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wolf.springboot.domain.sys.Module;
import com.wolf.springboot.domain.sys.User;
import com.wolf.springboot.entity.Message;
import com.wolf.springboot.entity.Search;
import com.wolf.springboot.entity.domain.Criteria;
import com.wolf.springboot.entity.domain.LayPage;
import com.wolf.springboot.entity.domain.Restrictions;
import com.wolf.springboot.enums.DispatcherSuffixCategory;
import com.wolf.springboot.service.sys.ModuleService;
import com.wolf.springboot.web.BaseController;

/**
 * 
 * <p>
 * Title: ModuleAdminController
 * </p>
 * <p>
 * Description:系统模块
 * </p>
 * 
 * @author wyx
 * @date 2019年4月15日
 */
@Controller
@RequestMapping("/sys/Module/")
public class ModuleAdminController extends BaseController {

	public Logger logger = LogManager.getLogger(getClass());

	private static final String MODULE = "Module";

	private static final String REQUEST_MARK = "sys";

	private static final String FLODER = "sys/module/";

	private static final String VIEW_MODULE = "moduleView";

	@Autowired
	private ModuleService moduleServ;

	@RequestMapping("index")
	public String index(ModelMap modelMap) {
		initPageButton(modelMap);
		modelMap.put("REQUEST_MODEL", MODULE);
		modelMap.put("REQUEST_MARK", REQUEST_MARK);
		return FLODER + "index" + DispatcherSuffixCategory.themeyleaf.getSuffix();
	}

	@RequestMapping("json/Nav/{level}")
	@ResponseBody
	public Object jsonNav(@PathVariable Integer level) {
		List<Module> models = moduleServ.findAllDeletedFalse(level);
		return models;
	}

	@RequestMapping("json/Grid")
	@ResponseBody
	public Object jsonGrid(Search search) {
		LayPage<Module> layPage = new LayPage<>();
		layPage.setPage(search.getPage() - 1);
		layPage.setPageSize(search.getLimit());
		Criteria<Module> criteria = new Criteria<>();
		criteria.add(Restrictions.eq("deleted", false, true));
		layPage = moduleServ.findPage(criteria, Sort.by(Direction.DESC, "id"), layPage);
		layPage.setPage(layPage.getPage() + 1);
		return layPage;
	}

	@RequestMapping("html/Add")
	public String htmlAdd(ModelMap modelMap) {
		modelMap.put(VIEW_MODULE, new Module());
		modelMap.put("REQUEST_MODEL", MODULE);
		modelMap.put("REQUEST_MARK", REQUEST_MARK);
		return FLODER + "form" + DispatcherSuffixCategory.themeyleaf.getSuffix();
	}

	@RequestMapping("json/Add")
	@ResponseBody
	public Object jsonAdd(Module model) {
		Message msg = getMsg();
		if (verify(model, msg)) {
			User cUser = getCurUser();
			model.setModifyTime(new Date());
			model.setMenderId(cUser.getId());
			try {
				moduleServ.save(model);
				msg.successMsg("新增成功！");
			} catch (Exception e) {
				msg.errorMsg("新增失败！");
				logger.error(e.getStackTrace());
				e.printStackTrace();
			}
		}
		return msg;
	}

	private boolean verify(Module model, Message msg) {
		return true;
	}

}
