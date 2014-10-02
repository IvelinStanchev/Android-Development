import java.io.IOException;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;

import org.jsoup.Jsoup;


public class Main {

	public static void main(String[] args) {
		org.jsoup.nodes.Document doc = null;
		try {
			doc = Jsoup.connect("http://www.prokerala.com/astrology/horoscope/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		org.jsoup.select.Elements newsHeadlines = doc.select(".mainContent ul li a");
		
		//int length = newsHeadlines.toArray().length;
		
		int index = 0;
		for (org.jsoup.nodes.Element element : newsHeadlines) {
			if (index < 2) {
				index++;
			}
			else{
				try {
					//doc = Jsoup.connect("http://www.prokerala.com/astrology/horoscope/index.php?sign=scorpio").get();
					doc = Jsoup.connect("http://www.prokerala.com/astrology/horoscope/" + element.attr("href")).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				newsHeadlines = doc.select(".mainContent article p");
				
				char[] attributeNameStringAsCharArray = element.attr("href").toCharArray();
				boolean inCommand = false;
				String name = "not named";
				
				for (int i = 0; i < attributeNameStringAsCharArray.length; i++) {
					if (attributeNameStringAsCharArray[i] == '=') {
						i++;
						inCommand = true;
						name = "";
					}
					if (inCommand) {
						name += attributeNameStringAsCharArray[i];
					}
				}
				
				System.out.println(name.toUpperCase() + "   -   " + newsHeadlines.html());
				System.out.println("------------------------------------------------------------------------------------------------------");
			}
		}
	}

}
