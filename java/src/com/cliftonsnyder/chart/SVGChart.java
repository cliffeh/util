package com.cliftonsnyder.chart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SVGChart extends Chart {

	public static final String USAGE = "usage: java "
			+ SVGChart.class.getCanonicalName() + " [infile]";

	public static final String SVG_11_DTD = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">";

	public SVGChart(int width, int height) {
		super(width, height);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		InputStream in = null;

		// TODO better argument use
		if (args.length > 1) {
			System.err.println(USAGE);
			System.exit(1);
		} else if (args.length == 1) {
			try {
				in = new FileInputStream(args[0]);
			} catch (FileNotFoundException e) {
				System.err.println("error: file '" + args[0] + "' not found");
				System.exit(1);
			}
		} else if (args.length == 0) {
			in = System.in;
		}

		// TODO implement a load(InputStream) method in Chart
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		Chart chart = new SVGChart(640, 480);

		// add some data to this ma'
		int lineNumber = 0;
		List<float[]> dataset = new ArrayList<float[]>();
		try {
			String line = br.readLine();
			lineNumber++;

			// assumption: labels are on the first line of the input
			chart.setLabels(line.split("\\s+"));

			while ((line = br.readLine()) != null) {
				String[] pieces = line.split("\\s+");

				float[] data = new float[pieces.length];
				for (int i = 0; i < pieces.length; i++) {
					data[i] = Float.parseFloat(pieces[i]);
				}
				dataset.add(data);
			}
		} catch (IOException e) {
			System.err.println("error: IO error at line " + lineNumber);
		}
		chart.setDataSet(dataset);
		// HACKS!
		// chart.setMaxY(424984072);

		// plot it
		chart.plot(System.out);
	}

	private String generateCSS() {
		String css = "";

		css += "#canvas {}" + "\n";
		css += ".bg { stroke: black; fill: white; stroke-width: 1; }" + "\n";
		for (int i = 0; i < colors.length; i++) {
			css += ".ds" + i + " { stroke: " + colors[i] + "; fill: "
					+ colors[i] + "; }" + "\n";
		}

		return css;
	}

	private String generateJS() {
		String js = "";
		js += "var mo = null;" + "\n";
		js += "var moClass = null;" + "\n";
		js += "function announce(evt){" + "\n";
		js += "if(mo) mo.setAttributeNS(null, 'class', moClass);" + "\n";
		js += "mo = evt.target;" + "\n";
		js += "moClass = mo.getAttributeNS(null, 'class');" + "\n";
		js += "mo.setAttributeNS(null, 'class', 'ds0');" + "\n";
		js += "}" + "\n";
		return js;
	}

	public void plot(OutputStream out) {
		// PrintStream o = new PrintStream(out);

		// http://tutorials.jenkov.com/java-xml/stax-xmlstreamwriter.html
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;
		try {
			writer = xof.createXMLStreamWriter(out);
			// TODO insert newlines? (javax.xml.transform.Transformer?)
			writer.writeStartDocument();
			writer.writeDTD(SVG_11_DTD);

			// <svg>
			writer.writeStartElement("svg");
			writer.writeAttribute("width", "" + width);
			writer.writeAttribute("height", "" + height);
			writer.writeAttribute("xmlns", "http://www.w3.org/2000/svg");

			// <style>
			writer.writeStartElement("style");
			writer.writeAttribute("type", "text/css");
			writer.writeCData(generateCSS());
			writer.writeEndElement();

			// <script>
			writer.writeStartElement("script");
			writer.writeAttribute("type", "text/ecmascript");
			writer.writeCData(generateJS());
			writer.writeEndElement();

			// <g>
			writer.writeStartElement("g");
			writer.writeAttribute("name", "canvas");

			// background rect
			writer.writeEmptyElement("rect");
			writer.writeAttribute("class", "bg");
			writer.writeAttribute("x", "" + 1);
			writer.writeAttribute("y", "" + 1);
			writer.writeAttribute("width", "" + (width - 1));
			writer.writeAttribute("height", "" + (height - 1));

			float w = xScale;

			// plot the actual data
			for (int i = 0; i < dataset.size(); i++) {
				// TODO do something with d[0] (x data); for now assuming
				// uniform distribution
				float[] d = dataset.get(i);
				float yPos = (float) height;
				float xPos = xScale * i;
				for (int j = 1; j < d.length; j++) {
					float h = d[j] * yScale;
					// TODO use rect rather than line?
					// writer.writeEmptyElement("line");
					// writer.writeAttribute("class", "dsl" + j);
					// writer.writeAttribute("style", "stroke-width:" + w);
					// writer.writeAttribute("x1", "" + xPos);
					// writer.writeAttribute("x2", "" + xPos);
					// writer.writeAttribute("y1", "" + yPos);
					// writer.writeAttribute("y2", "" + (yPos - h));

					writer.writeEmptyElement("rect");
					writer.writeAttribute("class", "ds" + j);
					writer.writeAttribute("name", "p" + j + "-" + ((long) d[0]));
					writer.writeAttribute("x", "" + xPos);
					writer.writeAttribute("y", "" + (yPos - h));
					writer.writeAttribute("width", "" + w);
					writer.writeAttribute("height", "" + h);
					// TODO cut down size by binding this some other way?
					// writer.writeAttribute("onmouseover", "announce(evt)");

					yPos = yPos - h;
				}
			}

			writer.writeEndElement(); // </g>
			writer.writeEndElement(); // </svg>
			writer.writeEndDocument();

			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			// System.err.println("error: there was an error writing the chart");
			// System.exit(1);
			// TODO meaningful error message; suppress stack trace
			e.printStackTrace();
		}

		// TODO consider some better way of handling this stuff
		// o.println("<?xml version=\"1.0\" standalone=\"no\"?>");
		// o.println("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"");
		// o.println("\t\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
		// o.println("<svg width=\"" + width + "\" height=\"" + height
		// + "\" xmlns=\"http://www.w3.org/2000/svg\"");
		// o.println("\txmlns:xlink=\"http://www.w3.org/1999/xlink\" version=\"1.1\">");
		// o.println("<g title=\"canvas\">");

		// background rect
		// o.println("<rect style=\"fill:white;stroke:black\" x=\"1\" y=\"1\" width=\""
		// + (width - 1) + "\" height=\"" + (height - 2) + "\"/>");

		// float w = xScale;
		//
		// // plot it!
		// for (int i = 0; i < dataset.size(); i++) {
		// // TODO do something with d[0] (x data); for now assuming uniform
		// // distribution
		// float[] d = dataset.get(i);
		// float yPos = (float) height;
		// float xPos = xScale * i;
		// for (int j = 1; j < d.length; j++) {
		// float h = d[j] * yScale;
		// o.println("<line style=\"stroke:" + colors[j]
		// + ";stroke-width:" + w + "\"  x1=\"" + xPos
		// + "\" x2=\"" + xPos + "\" y1=\"" + yPos + "\" y2=\""
		// + (yPos - h) + "\"/>");
		// yPos = yPos - h;
		// }
		// }
		//
		// o.println("</g>");
		// o.println("</svg>");
	}
}
