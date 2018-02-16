package com.nttdata.testexecutiontracker.entity;

import java.util.ArrayList;
import java.util.List;

public class DatewiseReportChart {

	private Title title;
	private AxisY axisY;
	private List<Data> data = new ArrayList();

	
	public class Title{
		private String text;

		/**
		 * @param text
		 */
		public Title(String text) {
			super();
			this.text = text;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		
	}
	public class AxisY{
		private String text = "Tests Count";

		/**
		 * @param text
		 */
		public AxisY(String text) {
			super();
			this.text = text;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		
	}

	public class Data{
		private String type = "stackedColumn";
		private String legendText;
		private String showInLegend = "true";
		private List<DataPoints> dataPoints = new ArrayList();
		
		public Data()
		{
			
		}
		/**
		 * @param type
		 * @param legendText
		 * @param showInLegend
		 * @param dataPoints
		 */
		public Data(String type, String legendText, String showInLegend, List<DataPoints> dataPoints) {
			super();
			this.type = type;
			this.legendText = legendText;
			this.showInLegend = showInLegend;
			this.dataPoints = dataPoints;
		}
		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		/**
		 * @return the legendText
		 */
		public String getLegendText() {
			return legendText;
		}
		/**
		 * @param legendText the legendText to set
		 */
		public void setLegendText(String legendText) {
			this.legendText = legendText;
		}
		/**
		 * @return the showInLegend
		 */
		public String getShowInLegend() {
			return showInLegend;
		}
		/**
		 * @param showInLegend the showInLegend to set
		 */
		public void setShowInLegend(String showInLegend) {
			this.showInLegend = showInLegend;
		}
		/**
		 * @return the dataPoints
		 */
		public List<DataPoints> getDataPoints() {
			return dataPoints;
		}
		/**
		 * @param dataPoints the dataPoints to set
		 */
		public void setDataPoints(List<DataPoints> dataPoints) {
			this.dataPoints = dataPoints;
		}
	}
	public class DataPoints{
		private int y;
		private String label;
		/**
		 * @param y
		 * @param label
		 */
		public DataPoints(int y, String label) {
			super();
			this.y = y;
			this.label = label;
		}
		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}
		/**
		 * @param y the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}
		/**
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}
		/**
		 * @param label the label to set
		 */
		public void setLabel(String label) {
			this.label = label;
		}
	}
	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(Title title) {
		this.title = title;
	}
	/**
	 * @return the axisY
	 */
	public AxisY getAxisY() {
		return axisY;
	}
	/**
	 * @param axisY the axisY to set
	 */
	public void setAxisY(AxisY axisY) {
		this.axisY = axisY;
	}
	/**
	 * @return the data
	 */
	public List<Data> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<Data> data) {
		this.data = data;
	}

	
}

