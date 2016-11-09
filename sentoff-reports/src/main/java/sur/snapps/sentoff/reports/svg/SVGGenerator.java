package sur.snapps.sentoff.reports.svg;

import static org.springframework.util.ResourceUtils.getFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Component
public class SVGGenerator {

	public String generateSVG() throws Exception {
	    DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
                
		ScriptEngine js = new ScriptEngineManager().getEngineByName("ECMAScript");
		Bindings bindings = js.getBindings(ScriptContext.ENGINE_SCOPE);
		bindings.put("systemOut", System.out);
		
		File d3 = getFile("classpath:d3.js");
		Reader reader = new FileReader(d3);
		js.eval(reader);
		
		File jsDoc = getFile("classpath:document.js");
		Reader readerDoc = new FileReader(jsDoc);
		js.eval(readerDoc);
		
		File chartA = getFile("classpath:chartA.js");
		Reader readerA = new FileReader(chartA);
		
		String svgString = (String) js.eval(readerA);
//		Writer writer = new FileWriter("target/test.svg");
//		writer.append(svgString);
//		writer.close();
        System.out.println(svgString);
        

        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
        Document document = f.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, new StringReader(svgString));
        
        JPEGTranscoder t = new JPEGTranscoder();
        t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
        
        TranscoderInput input = new TranscoderInput(document);
        OutputStream outStream = new FileOutputStream("src/main/resources/out.jpg");
        TranscoderOutput output = new TranscoderOutput(outStream);
        
        t.transcode(input, output);
        
        outStream.flush();
        outStream.close();
        
        return svgString;
	}
	
    public Document createDocument() {

        // Create a new document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document document =
          impl.createDocument(svgNS, "svg", null);
        Element root = document.getDocumentElement();
        root.setAttributeNS(null, "width", "450");
        root.setAttributeNS(null, "height", "500");

        // Add some content to the document.
        Element e;
        e = document.createElementNS(svgNS, "rect");
        e.setAttributeNS(null, "x", "10");
        e.setAttributeNS(null, "y", "10");
        e.setAttributeNS(null, "width", "200");
        e.setAttributeNS(null, "height", "300");
        e.setAttributeNS(null, "style", "fill:red;stroke:black;stroke-width:4");
        root.appendChild(e);

        e = document.createElementNS(svgNS, "circle");
        e.setAttributeNS(null, "cx", "225");
        e.setAttributeNS(null, "cy", "250");
        e.setAttributeNS(null, "r", "100");
        e.setAttributeNS(null, "style", "fill:green;fill-opacity:.5");
        root.appendChild(e);

        return document;
    }
}
