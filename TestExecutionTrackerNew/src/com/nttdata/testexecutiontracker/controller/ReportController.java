package com.nttdata.testexecutiontracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nttdata.testexecutiontracker.dao.DatewiseReportDao;
import com.nttdata.testexecutiontracker.dao.ReportDao;
import com.nttdata.testexecutiontracker.entity.DatewiseReportChart;
import com.nttdata.testexecutiontracker.entity.Report;
import com.nttdata.testexecutiontracker.entity.TestStatus;
import com.nttdata.testexecutiontracker.entity.DatewiseReportChart.DataPoints;
import com.nttdata.testexecutiontracker.util.Util;

@Controller
public class ReportController {
	ReportDao reportDao = new ReportDao();
	DatewiseReportDao datewiseReportDao = new DatewiseReportDao();
	String projectName = "";
	String cycleName = "ST_Cycle_3";
	String statusType1 ="level1TestStatus";
	String level1 = "level1Date";
	String statusType2 ="level2BVStatus";
	String level2 = "level2Date";
	
	
	@RequestMapping(value="reportHome", method=RequestMethod.GET, produces = "application/json")
	public String generateReport(ModelMap model, HttpServletRequest request)
	{
		Gson gson=new Gson();
		
		Report report = reportDao.getReportCard(projectName, cycleName);
		
		model.addAttribute(Util.REPORT, report);
		return "report";
	}
	
	@RequestMapping(value="dateWiseReport", method=RequestMethod.GET, produces = "application/json")
	public String generateDatewiseReport(ModelMap model, HttpServletRequest request)
	{
		Gson gson=new Gson();

		List<Map> list1 = null;
		List<Map> list2 = null;
		try{
			list1 = datewiseReportDao.getDatewiseReport(projectName, cycleName, statusType1, level1);
			list2 = datewiseReportDao.getDatewiseReport(projectName, cycleName, statusType2, level2);
		}catch(Exception e){
			e.printStackTrace();
		}		
		model.addAttribute(Util.REPORT+"1", list1);
		model.addAttribute(Util.REPORT+"2", list2);
		
		DatewiseReportChart datewiseReportChart1 = generateDatewiseChart(list1, "Level-1 Datewise details", true);
		DatewiseReportChart datewiseReportChart2 = generateDatewiseChart(list2, "Level-2 Datewise details", false);
		model.addAttribute("chart1", gson.toJson(datewiseReportChart1));
		model.addAttribute("chart2", gson.toJson(datewiseReportChart2));
		
		return "dateWiseReport";
	}

	public DatewiseReportChart generateDatewiseChart(List<Map> list, String titleText, boolean testLevel){
		DatewiseReportChart datewiseReportChart = new DatewiseReportChart ();

		DatewiseReportChart.Title tile = datewiseReportChart. new Title(titleText);
		
		for(Map<String, TestStatus> map: list)
			for(Map.Entry<String, TestStatus>entry: map.entrySet())
				System.out.println(entry.getKey()+"\t"+entry.getValue().getL1PassedTests() +"\t"+entry.getValue().getL1FailedTests()+"\t"+entry.getValue().getL1NotTestedTests()+"\t"+entry.getValue().getL1BlockedTests()+"\t"+entry.getValue().getL1InvalidTests());

		
		List<String> dateList = new ArrayList<>();
		List<Integer> passedTestsList = new ArrayList<>();
		List<Integer> failedTestsList = new ArrayList<>();
		List<Integer> notTestedTestsList = new ArrayList<>();
		
		
		for(Map<String, TestStatus> map: list)
		{
			for(Map.Entry<String, TestStatus>entry: map.entrySet())
			{
				dateList.add(entry.getKey());
				if(testLevel)
					passedTestsList.add(entry.getValue().getL1PassedTests());
				else
					passedTestsList.add(entry.getValue().getL2PassedTests());

				if(testLevel)
					failedTestsList.add(entry.getValue().getL1FailedTests());
				else
					failedTestsList.add(entry.getValue().getL2FailedTests());
				
				if(testLevel)
					notTestedTestsList.add(entry.getValue().getL1NotTestedTests());
				else
					notTestedTestsList.add(entry.getValue().getL2NotTestedTests());
			}
		}
		DatewiseReportChart.Data data = datewiseReportChart. new Data();
		List<DataPoints> dataPointsList = new ArrayList<DataPoints>();
		data.setLegendText("Passed");
		int i = 0 ;
		for(String date: dateList)
		{
			DatewiseReportChart.DataPoints dataPoints =  datewiseReportChart. new DataPoints(passedTestsList.get(i), date);
			dataPointsList.add(dataPoints);
			data.setDataPoints(dataPointsList);
		i++;
		}
		datewiseReportChart.getData().add(data);

		data = datewiseReportChart. new Data();
		dataPointsList = new ArrayList<DataPoints>();
		data.setLegendText("Failed");
		i = 0 ;
		for(String date: dateList){
			DatewiseReportChart.DataPoints dataPoints =  datewiseReportChart. new DataPoints(failedTestsList.get(i), date);
			dataPointsList.add(dataPoints);
			data.setDataPoints(dataPointsList);
		i++;
		}
		datewiseReportChart.getData().add(data);

		data = datewiseReportChart. new Data();
		dataPointsList = new ArrayList<DataPoints>();
		data.setLegendText("Not Tested");
		i = 0 ;
		for(String date: dateList){
			DatewiseReportChart.DataPoints dataPoints =  datewiseReportChart. new DataPoints(notTestedTestsList.get(i), date);
			dataPointsList.add(dataPoints);
			data.setDataPoints(dataPointsList);
		i++;
		}
		datewiseReportChart.getData().add(data);

		return datewiseReportChart;
	}
}