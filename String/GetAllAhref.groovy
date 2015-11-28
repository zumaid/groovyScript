/**
 * Created by xuping on 15-9-30.
 *  目的：通过html代码获取代码中所有a链接的href属性，也就是获取所有的url
 */
@Grab(group='org.jsoup', module='jsoup', version='1.8.1')
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
String astring="<a href='/catalog/1'>Catalog</a><a href='/catalog/2'>Catalog</a>"
Document doc =Jsoup.parse(astring);
Elements es=doc.select("a")
for(Element e:es){
    System.out.println(e.attr("href"))
}
