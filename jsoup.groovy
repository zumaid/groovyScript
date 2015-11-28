@Grab(group='org.jsoup', module='jsoup', version='1.8.1')
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

Document doc = Jsoup.connect("http://www.baidu.com/").get();
Elements newsHeadlines = doc.select("a");


println newsHeadlines
