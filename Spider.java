package pa2;
import java.util.*; 

public class Spider {
    int MAX_PAGES_TO_SEARCH = 10;
    Set<String> pagesVisited = new HashSet<String>();
    List<String> pagesToVisit = new LinkedList<String>();
    
    public String nextUrl() {
        String nextUrl;
        do {
        	nextUrl = pagesToVisit.remove(0);
        } while(pagesVisited.contains(nextUrl));
        pagesVisited.add(nextUrl);
        
        return nextUrl;
    }
    public void search(String url, String searchWord) {
    	while(pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
    		String currentUrl;
    		SpiderLeg leg = new SpiderLeg();
    		if (pagesToVisit.isEmpty()) {
    			currentUrl = url;
    			pagesVisited.add(url);
    		}
    		else {
    			currentUrl = nextUrl();
    		}
    		leg.crawl(currentUrl);
    		boolean success = leg.searchForWord(searchWord);
    		if (success) {
    			System.out.println("SUCCESS - Word " + searchWord + " found at " + currentUrl);
    			break;
    		}
    		pagesToVisit.addAll(leg.getLinks());
    	}
    	System.out.println("DONE - visited " + pagesVisited.size() + " web pages");
    }
}
