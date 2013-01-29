package com.cliftonsnyder.chart;

import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Chart {

	protected int height, width;
	// note: the assumption is that for each dataset: datasets, dataset[0] is
	// the x-data and all dataset[i] where i > 0 are y-data
	protected List<float[]> dataset;
	// TODO add a way to plot as either stacked bar *or* line
	protected String[] labels;

	// TODO add a second Y axis
	protected float minX, minY, maxX, maxY;
	protected float xScale, yScale;
	
	// TODO pick "prettier" colors
	protected String[] colors = { "red", "blue", "green", "silver" }; 

	public Chart(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setMaxY(float maxY){
		this.maxY = maxY;
		// recompute our yScale
		yScale = ((float)height)/(maxY - (minY < 0 ? minY : 0));
	}

	public void setDataSet(List<float[]> dataset) {
		this.dataset = dataset;

		// sort by x-data
		Collections.sort(dataset, new DataSetComparator());

		// minX and maxX are easy, since we've already sorted the dataset
		minX = dataset.get(0)[0];
		maxX = dataset.get(dataset.size() - 1)[0];

		// find our minY and maxY values
		minY = Float.MAX_VALUE;
		maxY = Float.MIN_VALUE;
		for (float[] f : dataset) {
			float y = 0;
			for (int i = 1; i < f.length; i++) {
				y += f[i];
			}
			if (y < minY)
				minY = y;
			if (y > maxY)
				maxY = y;
		}
		
		// generate scales based on number of points, min/max y values
		xScale = ((float)width)/((float)dataset.size());
		yScale = ((float)height)/(maxY - (minY < 0 ? minY : 0));
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public abstract void plot(OutputStream out);
}

class DataSetComparator implements Comparator<float[]> {

	@Override
	public int compare(float[] f1, float[] f2) {
		if (f1[0] < f2[0])
			return -1;
		if (f1[0] > f2[0])
			return 1;
		return 0;
	}

}
