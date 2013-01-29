package com.cliftonsnyder.svg;

import java.io.OutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SVGCalendar {

	public static final String SVG_11_DTD = "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">";

	private float width, height, strokeWidth = 2, totalWidth, totalHeight,
			cellWidth, cellHeight;
	private int cols = 7, rows = 6;

	public SVGCalendar(float width, float height) {
		this.width = width;
		this.height = height;
		// 7:6 ratio
		cellWidth = width / cols;
		cellHeight = height / rows;
		totalWidth = width + (strokeWidth * 2 * cols + 3);
		totalHeight = height + (strokeWidth * 2 * rows + 3);
	}

	public String generateCSS() {
		String css = "";
		String NL = "\n"; // TODO replace with ""?

		css += ".calbg { fill: none; stroke: black; stroke-width: "
				+ strokeWidth + "; }" + NL;
		css += ".calcell { fill: none; stroke: black; stroke-width: "
				+ strokeWidth + "  ; }" + NL;

		return css;
	}

	public void render(OutputStream out) {
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = null;

		try {
			writer = xof.createXMLStreamWriter(out);

			// TODO insert newlines? (javax.xml.transform.Transformer?)
			writer.writeStartDocument();
			writer.writeDTD(SVG_11_DTD);

			// <svg>
			writer.writeStartElement("svg");
			// 7:6 ratio
			writer.writeAttribute("width", "" + totalWidth);
			writer.writeAttribute("height", "" + totalHeight);
			writer.writeAttribute("xmlns", "http://www.w3.org/2000/svg");

			// <style>
			writer.writeStartElement("style");
			writer.writeAttribute("type", "text/css");
			writer.writeCData(generateCSS());
			writer.writeEndElement(); // </style>

			writer.writeEmptyElement("rect");
			writer.writeAttribute("class", "calbg");
			writer.writeAttribute("x", "" + 1);
			writer.writeAttribute("y", "" + 1);
			writer.writeAttribute("width", "" + (totalWidth - 1));
			writer.writeAttribute("height", "" + (totalHeight - 1));

			// render a cols x rows calendar
			float x = strokeWidth * 2 + 1;
			for (int i = 0; i < 7; i++) {
				float y = strokeWidth * 2 + 1;
				for (int j = 0; j < 6; j++) {
					writer.writeEmptyElement("rect");
					writer.writeAttribute("class", "calcell");
					writer.writeAttribute("x", "" + x);
					writer.writeAttribute("y", "" + y);
					writer.writeAttribute("width", "" + cellWidth);
					writer.writeAttribute("height", "" + cellHeight);
					y += cellHeight + strokeWidth * 2;
				}
				x += cellWidth + strokeWidth * 2;
			}

			writer.writeEndElement(); // </svg>
			writer.writeEndDocument();

			writer.flush();
			writer.close();

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO parse args
		// 7:6 ratio for all squares
		SVGCalendar cal = new SVGCalendar(350, 300);
		cal.render(System.out);
	}
}