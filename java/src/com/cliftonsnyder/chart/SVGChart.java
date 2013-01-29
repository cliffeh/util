package com.cliftonsnyder.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SVGChart extends Chart {

	public static final String SVG_11_DTD = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">";

	public SVGChart(int width, int height) {
		super(width, height);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO determine what to use as input with args!
		InputStream in = System.in;

		Options options = new Options();

		options.addOption("gw", "width", true,
				"the width of the graph to be created [default: 640]");
		options.addOption("gh", "height", true,
				"the height of the graph to be created [default: 480]");
		options.addOption("h", "help", false, "print a brief help message");
		options.addOption("i", "infile", true,
				"the input file to use [default: stdin]");
		options.addOption("o", "outfile", true,
				"the output file to use [default: stdout]");
		options.addOption("s", "step", true,
				"the time period (in seconds) to use [default: 300]");

		CommandLine cli = null;
		CommandLineParser parser = new GnuParser();
		try {
			cli = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("error: " + e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("svgchart", options);
			System.exit(1);
		}

		if (cli.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("svgchart", options, true);
			System.exit(0);
		}

		// if (args.length > 1) {
		// System.err.println(USAGE);
		// System.exit(1);
		// } else if (args.length == 1) {
		// try {
		// in = new FileInputStream(args[0]);
		// } catch (FileNotFoundException e) {
		// System.err.println("error: file '" + args[0] + "' not found");
		// System.exit(1);
		// }
		// } else if (args.length == 0) {
		// in = System.in;
		// }

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
		String NL = "\n"; // TODO replace with ""?

		css += "#canvas {}" + NL;
		css += ".bg { stroke: black; fill: white; stroke-width: 1; }" + NL;
		for (int i = 0; i < colors.length; i++) {
			css += ".ds" + i + " { stroke: " + colors[i] + "; fill: "
					+ colors[i] + "; }" + NL;
		}

		return css;
	}

	private String generateJS() {// TODO make this better! lulz
		String js = "";
		String NL = "\n"; // TODO replace with ""?

		js += "var mo = null;" + NL;
		js += "var moClass = null;" + NL;
		js += "function announce(evt){" + NL;
		js += "if(mo) mo.setAttributeNS(null, 'class', moClass);" + NL;
		js += "mo = evt.target;" + NL;
		js += "moClass = mo.getAttributeNS(null, 'class');" + NL;
		js += "mo.setAttributeNS(null, 'class', 'ds0');" + NL;
		js += "document.getElementById('ann').appendChild(document.createTextNode('yourText'))";
		js += "}" + NL;

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

			// text box for announce()
			writer.writeEmptyElement("rect");
			writer.writeAttribute("x", "" + 1);
			writer.writeAttribute("y", "" + 1);
			writer.writeAttribute("width", "" + 50);
			writer.writeAttribute("height", "" + 50);
			// TODO put this in the CSS
			writer.writeAttribute("style", "fill:white");

			// text element 'ann' for announce()
			writer.writeEmptyElement("text");
			writer.writeAttribute("id", "ann");
			writer.writeAttribute("x", "" + 5);
			writer.writeAttribute("y", "" + 15);

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
