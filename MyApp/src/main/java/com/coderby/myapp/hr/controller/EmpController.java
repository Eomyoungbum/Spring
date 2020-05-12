package com.coderby.myapp.hr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderby.myapp.hr.dao.IEmpService;
import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;

@Controller
public class EmpController {

	@Autowired
	IEmpService empService;

	@RequestMapping("/hr/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0")
	int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count",empService.getEmpCount());
		}else {
			model.addAttribute("count",empService.getEmpCount(deptId));
		}
		return "hr/count";
	}

	@RequestMapping(value="/hr/list")
	public String getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList",empList);
		return "hr/list";
	}

	@RequestMapping(value="/hr/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
		EmpDetailVO emp = (EmpDetailVO)empService.getEmpInfo(employeeId);
		model.addAttribute("emp",emp);
		return "hr/view";
	}

	@GetMapping(value="/hr/insert")
	public String insertEmp(Model model) {
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","insert");
		return "hr/insertform";
	}

	@PostMapping(value="/hr/insert")
	public String insertEmp(EmpVO emp, Model model) {
		empService.insertEmp(emp);
		return "redirect:/hr/index";
	}

	@GetMapping(value="/hr/update")
	public String updateEmp(int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message","update");
		return "hr/insertform";
	}

	@PostMapping(value="/hr/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}

	@GetMapping(value="/hr/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("count",empService.getUpdateCount(empId));
		return "hr/deleteform";
	}
	
	@PostMapping(value="/hr/delete")
	public String deleteEmp(Model model, int empId) {
		empService.deleteEmp(empId);
		return "redirect:/hr/index";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", ex);
		return "error/runtime";
	}
	
	@RequestMapping({"/hr/index","hr"})
	public String getMain() {
		return "hr/index";
	}
	
	@GetMapping(value="/hr/getMaxSalary")
	public String getMaxSalaryByDept(Model model) {
		model.addAttribute("empList",empService.getEmpByMaxSalary());
		return "hr/list";
	}
	
}
















