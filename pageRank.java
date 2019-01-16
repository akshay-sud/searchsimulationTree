package pa2;
import java.util.Random;

public class pageRank {
	String url;
	int frequencyRank;
	int existenceRank;
	int relevanceRank;
	int bribeRank;
	int rank;
	double time;
	double clicks;
	
	public pageRank(String url) {
		this.url = url;
		Random rand = new Random();
		
		frequencyRank = rand.nextInt(10);
		existenceRank = rand.nextInt(10);
		relevanceRank = rand.nextInt(10);
		bribeRank = rand.nextInt(10);
		time = 0.0 + (24.0) * rand.nextDouble();
	    clicks = 1.0 + (1000.0 - 1.0) * rand.nextDouble();
	}
	public void setURL(String entry) {
		url = entry;
	}
	public String getURL() {
		return url;
	}
	public int getFreqRank() {
		return frequencyRank;
	}
	public int getExRank() {
		return existenceRank;
	}
	public int getRelRank() {
		return relevanceRank;
	}
	public void setbribeRank(int brank) {
		bribeRank = brank;
	}
	public int getbribeRank() {
		return bribeRank;
	}
	// need to call generateRank on every web page before storing it in heap
	public int generateRank() {
		rank = frequencyRank + existenceRank + relevanceRank + bribeRank;
		return rank;
	}
	public void setRank(int newRank) {
		rank = newRank;
	}
}