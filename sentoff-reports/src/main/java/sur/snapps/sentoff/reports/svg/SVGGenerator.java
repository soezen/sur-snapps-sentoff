package sur.snapps.sentoff.reports.svg;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class SVGGenerator {

	public void generateSVG(List<Integer> values) throws SVGGraphics2DIOException, IOException {
		DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);
		
		SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
		svgGenerator.setPaint(Color.RED);
		svgGenerator.fill(new Rectangle(10, 10, 10, 10));
		svgGenerator.stream(new FileWriter("target/test.svg"));
	}
}
