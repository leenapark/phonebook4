package com.javaex.controller;

import java.util.List;

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
@RequestMapping(value="/phone")
public class PhoneController {

	// 메소드 단위로 기능을 정의하는 spring
	
	// 필드
	@Autowired
	private PhoneDao phoneDao;
	
	// 생성자
	// 메소드 g/s
	
	/******** 메소드 일반*********/
	// 메소드 마다 기능 1개씩 --> 기능 마다 url 부여

	// Handler mapping
		// 리스트
		@RequestMapping(value="/list", method= {RequestMethod.POST, RequestMethod.GET})
		public String list(Model model) {	// aaddAttribute 사용 시
			// 확인용
			System.out.println("list");
			
			//dao 를 통해 리스트를 가져온다
			List<PersonVo> personList = phoneDao.getList();
			
			System.out.println(personList.toString());
			
			//model --> data 를 보내는 방법 --> 담아놓으면 된다
			model.addAttribute("pList", personList);
			
			
			return "/WEB-INF/views/list.jsp";
		}
	
		// 등록폼
		@RequestMapping(value="/writeForm", method= {RequestMethod.POST, RequestMethod.GET})
		public String writeForm() {
			System.out.println("writeForm");
			
			return "writeForm";
		}
		
		// http://localhost:8088/phonebook3/phone/write?name=김종국&hp=010-9999-9999&company=02-9999-9999
		// 등록
		@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
		public String write(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
			System.out.println("write");
			
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo.toString());
			
			phoneDao.phoneInsert(personVo);
			
			return "redirect:/phone/list";
		}
		// 수정폼 --> modifyForm
		@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
		public String modifyForm(@RequestParam("personId") int id, Model model) {
			System.out.println("modifyForm");
			// 파라미터 값 확인하기
			System.out.println(id);
						
			PersonVo personVo = phoneDao.getPerson(id);
			System.out.println(personVo.toString());
			
			model.addAttribute("personVo", personVo);
			
			return "/WEB-INF/views/updateForm.jsp";
		}
		
		// 수정 --> modify
		
		@RequestMapping(value="/modify2", method={RequestMethod.GET, RequestMethod.POST})
		public String modify2(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company, @RequestParam("personId") int id) {
			System.out.println("modify");
			
			// 파라미터 값 확인
			System.out.println(name + ", " + hp + ", " + company + ", " + id);
			
			PersonVo personVo = new PersonVo(id, name, hp, company);
			phoneDao.getUpdate(personVo);
						
			return "redirect:/phone/list";
		}
		// 수정 비교
		// 수정 --> @ModelAttribute 
		@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
		public String modify(@ModelAttribute PersonVo personVo) {
			System.out.println("modify");
			
			// 파라미터 값 확인
			
			/*
			 * 파라미터에 담아와서 Vo에 넣는 작업을 @ModelAttribute 에서 해줌
			 * System.out.println(name + ", " + hp + ", " + company + ", " + id);
			 * PersonVo personVo = new PersonVo(id, name, hp, company);
			 */
			
			System.out.println(personVo.toString());
			phoneDao.getUpdate(personVo);
			
			return "redirect:/phone/list";
		}
		// 삭제 --> delete --> @RequestMapping 약식 표현
		@RequestMapping("/delete2")
		public String delete2(@RequestParam("personId") int id) {
			System.out.println("delete");
			System.out.println(id);
			
			phoneDao.getDelete(id);
			
			return "redirect:/phone/list";
		}
		
		// 삭제 비교
		// 삭제 --> delete --> 
		@RequestMapping(value="/delete/{personId}", method={RequestMethod.GET, RequestMethod.POST})
		public String delete(@PathVariable("personId") int id) {
			System.out.println("delete");
			System.out.println(id);
						
			phoneDao.getDelete(id);
			
			return "redirect:/phone/list";
		}
		
}
