
package Presentation.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaswingdev.chart.ModelPieChart;
import javaswingdev.chart.PieChart;

public class AccueilPanel_2 extends JPanel {

	private PieChart PFE;
	private PieChart PFA;
	private PieChart Doctorat;
	private PieChart Total;

	/**
	 * Create the frame.
	 */
	public AccueilPanel_2() {
		initComponents();
		this.setBounds(100, 100, 1280, 720);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		GridLayout gridLayout = new GridLayout(0, 2);
		this.setLayout(gridLayout);

		this.add(PFE);
		this.add(PFA);
		this.add(Doctorat);
		this.add(Total);

	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		PFE = new PieChart();
		PFE.setChartType(PieChart.PeiChartType.DONUT_CHART);
		PFE.addData(new ModelPieChart("PFE", 25, new Color(44, 62, 80)));
		PFE.addData(new ModelPieChart("non terminee", 75, new Color(255, 255, 255)));

		PFA = new PieChart();
		PFA.setChartType(PieChart.PeiChartType.DONUT_CHART);
		PFA.addData(new ModelPieChart("PFA", 50, new Color(44, 62, 80)));
		PFA.addData(new ModelPieChart("non terminee", 50, new Color(255, 255, 255)));

		Doctorat = new PieChart();
		Doctorat.setChartType(PieChart.PeiChartType.DONUT_CHART);
		Doctorat.addData(new ModelPieChart("Doctorat", 75, new Color(44, 62, 80)));
		Doctorat.addData(new ModelPieChart("non terminee", 25, new Color(255, 255, 255)));

		Total = new PieChart();
		Total.setChartType(PieChart.PeiChartType.DONUT_CHART);
		Total.addData(new ModelPieChart("PFE", 50, new Color(44, 62, 80)));
		Total.addData(new ModelPieChart("PFA", 50, new Color(255, 255, 255)));

		PFE.setFont(new Font("sansserif", 1, 12));
		PFA.setFont(new Font("sansserif", 1, 12));
		Doctorat.setFont(new Font("sansserif", 1, 12));
		Total.setFont(new Font("sansserif", 1, 12));

	}

}
