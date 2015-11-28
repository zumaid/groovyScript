/**
 * Created by xuping on 15-9-26.
 */
@Grab(group = 'org.jsoup', module = 'jsoup', version = '1.8.1')
import com.gmongo.GMongo
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements

class hqew {
    static String url = "http://www.hqew.com/tech/sheji/1407024.html";
    static main(String[] args) {
        def mongo = new GMongo()
        def db = mongo.getDB("news")
        while (findNext()) {
           try{
               db.hqew << (getMessage(url))
           }catch (e){
               println e
           }




        }
    }

    static def findNext() {
        Document doc = Jsoup.connect(url).get();
        Elements next = doc.select(".g-td-u");
//        next.each{
//            println(next.text())
//        }
        if (next.size() == 2) {

            url = next.get(1).attr("abs:href")
            println("找到下一页 ${url}")
            return true;
        } else {
            println("没找到下一页 ${url}")
            sleep(1000*3)
            findNext();
        }
    }

    static def getList(url) {
        Document doc = Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select("h4 a");
        return newsHeadlines;
    }

    static def getMessage(url) {
        Document doc = Jsoup.connect(url).get();

        def msg = [:]
        def c = doc.select(".content");
        c.select("img").each {
            it.attr("src", it.attr("abs:src"))
        }

        Elements newsHeadlines = doc.select("h4");
        msg.content = c.html();
        msg.title = newsHeadlines.first().text();
        msg.url = url
        return msg
    }


}