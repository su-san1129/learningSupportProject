package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewReportController {
	

	@RequestMapping("/view_daily_report")
	public String viewDailyReport() {
		return "admin/admin_view_daily_report";
	}

	@RequestMapping("/view_weekly_report")
	public String weeklyReport() {
		return "admin/admin_view_weekly_report";
	}

	
	

}
