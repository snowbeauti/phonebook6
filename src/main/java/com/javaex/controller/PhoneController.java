package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	//필드
	@Autowired
	private PhoneDao pdao;
	//생성자
	//메소드gs

	/** 메소드 일반****메소드 마다 기능 1개씩 --> 기능마다 url 부여 **/

	//리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {

		System.out.println("list");

		// doa를 통해 리스트 를 가져온다.
		List<PersonVo> personList = pdao.getPersonList();
		System.out.println(personList.toString());

		// model -->data 를 보내는 방법 -->담아 놓으면 된다
		model.addAttribute("pList", personList);

		return "List";
	}

	//등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeFrom() {
		System.out.println("writeForm");

		return "WriteForm";
	}

	//등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {

		System.out.println("write");

		PersonVo pvo = new PersonVo(name, hp, company);
		pdao.personInsert(pvo);

		return "redirect:/phone/list";
	}

	//삭제 --> delete --> @PathVariable
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("id") int id) {
		System.out.println("delete");
		System.out.println(id);

		pdao.PersonDelete(id);

		return "redirect:/phone/list";
	}

	
	 //삭제 --> delete --> @RequestParam
	  
	 @RequestMapping("/delete2") 
	 public String delete2(@RequestParam("id") int id){ 
	 System.out.println("delete2"); 
	 System.out.println(id);
	  
	 pdao.PersonDelete(id);
	  
	 return "redirect:/phone/list"; 
	 }
	 

	//수정폼 --> modifyForm
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm");
		System.out.println(id);

		PersonVo pvo = pdao.getPerson(id);
		
		model.addAttribute("pvo", pvo);
		System.out.println(pvo.toString());

		return "modifyForm";
	}
	
	
	//수정폼2 --> modifyForm2
	@RequestMapping(value = "/modifyForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm2(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm2");
		System.out.println(id);

		Map<String, Object> pmap = pdao.getPerson2(id);
		model.addAttribute("pMap", pmap);
		
		
		return "modifyForm2";
	}
	
	
	//수정 -->modify
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo pvo) {
		System.out.println("modify");
		System.out.println(pvo);
		
		pdao.personUpdate(pvo);
		
		return "redirect:/phone/list"; 
	}
	
	//수정2 -->modify2
		@RequestMapping(value = "/modify2", method = {RequestMethod.GET, RequestMethod.POST})
		public String modify2(@RequestParam("person_id") int person_id,
							  @RequestParam("name")	String name,
							  @RequestParam("hp") String hp,
							  @RequestParam("company")String company)
		{
		
			System.out.println("modify2");
			System.out.println("modify2" + person_id + "," + name + "," + hp + "," + company);
			
			int count = pdao.personUpdate2(person_id, name, hp, company);

			
			return "redirect:/phone/list"; 
		}
		
		


}
