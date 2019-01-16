package pa2;
import java.util.*; 
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {
	String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	List<String> links = new LinkedList<String>();
	Document htmlDocument;
	
	public void crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            System.out.println("Received web page at " + url);

            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage) {
                links.add(link.absUrl("href"));
            }
		}
		catch(IOException ioe) {
			System.out.println("Error in out HTTP request " + ioe);
		}
	}
	public boolean searchForWord(String searchWord) {
		System.out.println("Searching for the word " + searchWord + "...");
		String bodyText = htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}
	public List<String> getLinks() {
		return links;
	}
}