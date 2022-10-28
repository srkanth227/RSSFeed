package com.samco.rssfeed.Service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.samco.rssfeed.Model.RssFeedResponse;

@Service
public class RssFeedService {
	
	private String server="https://cio.economictimes.indiatimes.com/rss/topstories";
	
	private String getXmlElementValue(Document doc, String expression) {
		XPath xPath;
		String value = null;
		try {
			xPath = XPathFactory.newInstance().newXPath();
			Node node = (Node) ((NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET)).item(0);
			if (node.getTextContent() != null && !(node.getTextContent().equals(""))) {
				value = node.getTextContent();
			}
		} catch (Exception e) {
			System.out.println("Parse Err");
		}
		return value;
	}

	public List<RssFeedResponse> getRssFeed(){
		List<RssFeedResponse> rssResponse = new ArrayList<>();
		String responseString;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<>("");
			ResponseEntity<String> responseStringValue = restTemplate.exchange(server, HttpMethod.POST, entity,
					String.class);
			responseString = responseStringValue.getBody().replace("&", "&amp;");
			
			
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.parse(new InputSource(new StringReader(responseString)));
				doc.getDocumentElement().normalize();
				String title = getXmlElementValue(doc, "//title");
				String link = getXmlElementValue(doc, "//link");
				String description = getXmlElementValue(doc, "//description");
				
		} catch (Exception e) {
			System.out.println("Error in Webservice call" + e.getMessage());
		}
		return rssResponse;
	}
}
