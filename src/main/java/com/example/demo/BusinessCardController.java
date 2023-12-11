package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.BusinessCardModel;
import com.example.demo.servise.PersonalInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BusinessCardController {

	private Logger logger = LoggerFactory.getLogger(BusinessCardController.class);

	@Autowired
	private PersonalInfoService service;

//	一覧画面への遷移（初期画面）
	@GetMapping("/")
	public ModelAndView listAll() {
		logger.info("listAll開始");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		List<BusinessCardModel> list = service.selectAll();
		mav.addObject("businessCards", list);
		return mav;
	}

//	詳細画面への遷移
	@GetMapping("/detail")
	public ModelAndView show(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		BusinessCardModel data = service.findById(id);
		mav.setViewName("show");
		mav.addObject("businessCards", data);
		return mav;
	}

//	登録画面への遷移
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		BusinessCardModel model = new BusinessCardModel();
		mav.setViewName("new");
		mav.addObject("businessCards", model);
		return mav;
	}

//	編集画面への遷移
	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		BusinessCardModel data = service.findById(id);
		mav.setViewName("new");
		mav.addObject("businessCards", data);
		return mav;
	}

//	更新処理
	@PostMapping("/create")
	@ResponseBody
	public ModelAndView save(@ModelAttribute("businessCards") @Validated BusinessCardModel model,
			BindingResult result) {

		model.setCreateDate(LocalDate.now());
		ModelAndView mav = new ModelAndView();

		if (result.hasErrors()) {
			result.reject("global", "正しい情報を入力してください。");
			mav.setViewName("new");
			return mav;
		}
		
		 // サービスクラスに保存処理を移動
	    service.saveBusinessCard(model);
	    mav.setViewName("redirect:/");
	    return mav;
	}

//	削除処理
	@PostMapping("/delete")
	public ModelAndView delete(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		BusinessCardModel data = service.findById(id);
		service.deleteByPrimaryKey(id);
		mav.addObject("businessCards", data);
		mav.setViewName("redirect:/");
		return mav;
	}

//	検索処理
	@GetMapping("/findByNameLike")
	public ModelAndView search(@RequestParam String keyword) {
		ModelAndView mav = new ModelAndView();
		List<BusinessCardModel> list = service.mySelectByExample(keyword);
		mav.setViewName("list");
		mav.addObject("businessCards", list);
		return mav;
	}

}
