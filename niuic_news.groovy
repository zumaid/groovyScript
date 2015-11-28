/**
 * Created by xuping on 15-9-26.
 */
@Grab(group = 'org.jsoup', module = 'jsoup', version = '1.8.1')
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by xuping on 15-9-26.
 *
 */
import groovy.sql.Sql
import com.gmongo.GMongo

def sql = Sql.newInstance("jdbc:mysql://121.42.45.148:3306/ic360?useUnicode=true&characterEncoding=UTF-8",
        "zuaa", "zuaa@428", "com.mysql.jdbc.Driver")

def file = new File("/media/xuping/eabc7d32-b25d-423c-8d5a-43fcfa28ce82/log/hqew_niuic.log")
100000.times {
    nu = 1501000;
    println nu;
    def m = getMessage("http://www.hqew.com/tech/fangan/1501000.html");
    try {
        if (m != null) {
            println sql.execute(ssql, m);
            println("sleep 1000*3")
            file << "http://www.hqew.com/tech/fangan/${nu + it}.html\n"
        } else {
            println "null message ?";
        }
    } catch (e) {
        file << "http://www.hqew.com/tech/fangan/${nu + it}.html--error\n"
    }
}


def getList(url) {
    Document doc = Jsoup.connect(url).get();
    Elements newsHeadlines = doc.select("h4 a");
    return newsHeadlines;
}

def getMessage(url) {
    try {
        Document doc = Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select("h4");
        def msg = []
        def c = doc.select(".content");
        c.select("img").each {

            it.attr("src", it.attr("abs:src"))
        }
        msg << c.html();
        msg << newsHeadlines.first().text()
        return msg
    } catch (e) {
        println "${e}";
        return null;
    }
}

def getSsql() {
    def ssql = """
INSERT
\t\tINTO `wp_posts` (`ID`, `post_author`,
\t\t`post_date`, `post_date_gmt`,
\t\t`post_content`,
\t\t`post_title`,
\t\t`post_excerpt`, `post_status`,
\t\t`comment_status`,
\t\t`ping_status`,
\t\t`post_password`, `post_name`,
\t\t`to_ping`, `pinged`, `post_modified`,
\t\t`post_modified_gmt`,
\t\t`post_content_filtered`, `post_parent`, `guid`,
\t\t`menu_order`,
\t\t`post_type`, `post_mime_type`, `comment_count`)
\t\tVALUES
\t\t(NULL, '2',
\t\tnow(), now(), ?, ?,
\t\t'', 'publish',
\t\t'open',
\t\t'open', '', '',
\t\t'', '', now(), now(), '', '0', '',
\t\t'0', 'post',
\t\t'', '0');"""
    return ssql;
}




