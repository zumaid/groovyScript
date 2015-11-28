/**
 * Created by xuping on 15-10-1.
 */
/**
 * Created by xuping on 15-9-26.
 */
@Grab(group = 'org.jsoup', module = 'jsoup', version = '1.8.1')
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by xuping on 15-9-26.
 * 脚本的目的：
 * 通过属性寻找类似的产品
 */
import groovy.sql.Sql
import com.gmongo.GMongo
def mongo = new GMongo()

// Get a db reference in the old fashion way
def db = mongo.getDB("ic")
def sql = Sql.newInstance("jdbc:mysql://rdsypbaeq55wzj2hugt9.mysql.rds.aliyuncs.com:3306/search?useUnicode=true&characterEncoding=UTF-8",
        "zuaa", "aaaaaaaa", "com.mysql.jdbc.Driver")
def ssql=""" INSERT  into pn ( pn, mfs, info, catalog, pkg, lead, embgo, rohs, pic, pdf, param)
VALUES (?,?,?,?,?,?,?,?,?,?,? )"""

def seed=db.product_94.find(["pid":107428544])

println "============================================"

def al=db.product_94.find()
al.each{
    println(it)
    def c=[];
    c<<it.pn
    c<<it.mfs;
    c<<it.info;
    c<<it.catalog;
    c<<it.pkg;
    c<<it.lead;
    c<<it.embgo;
    c<<it.rohs;
    c<<it.pic;
    c<<it.pdf;
    c<<it.param.toString();
    sql.execute(ssql, c)
 }



//INSERT  into product (  pid ) (select id FROM  pn where id not in (select pid from product));
//update  product set supplier_id=2;
