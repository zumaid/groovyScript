/**
 * Created by xuping on 15-9-26.
 * 录入十万基本产品信息，测试模版
 */
import com.gmongo.GMongo
import groovy.sql.Sql
//def sql = Sql.newInstance("jdbc:mysql://rdsypbaeq55wzj2hugt9.mysql.rds.aliyuncs.com:3306/search?useUnicode=true&characterEncoding=UTF-8",
//        "zuaa", "aaaaaaaa", "com.mysql.jdbc.Driver")
def sql = Sql.newInstance("jdbc:mysql://news.weeego.cn:3306/ic360?useUnicode=true&characterEncoding=UTF-8",
        "zuaa", "zuaa@428", "com.mysql.jdbc.Driver")
//def sql = Sql.newInstance("jdbc:mysql://rdsypbaeq55wzj2hugt9.mysql.rds.aliyuncs.com:3306/search?useUnicode=true&characterEncoding=UTF-8",
//        "zuaa", "aaaaaaaa", "com.mysql.jdbc.Driver")
def mongo = new GMongo()
def db = mongo.getDB("ic");
def al = db.product_94.find().limit(10000).skip(10200)
//println(["a","aa"])
al.each {
    sql.execute(ssql, [getTableHtmol(it),getTitle(it)])
//    sleep(1000)
    println(".")
}

def getTitle(it ){
    def re="";
    int i=0;
    it.param.each {
        k, v ->
            if(i<5){
                re=re+""" ${v} """;
                i=i+1;
            }
    }
    return  it.pn+ re;
}
def getParamHtml(it ){
    def re="";
    it.param.each {
        k, v ->
            re=re+"""<tr>
<td>${k}</td>
<td>${v}</td>
</tr>""";
    }
    return  re;
}

def getTableHtmol(it){
    if(it?.param!=null){
        return """<table class="table table-bordered">
<tbody>
<tr>
<td>制造商编号</td>
<td colspan="2">${it.pn}</td>
</tr>
<tr>
<td>制造商</td>
<td colspan="2">${it.mfs}</td>
</tr><tr>
<td>分类信息</td>
<td colspan="2">${it.catgalog}</td>
</tr>
<tr>
<td rowspan="${it?.param?.size()+1}">属性</td>
<td>属性名称</td>
<td>属性值</td>
</tr>
${getParamHtml(it)}
</tbody>
</table>""".toString()
    }
}



def getSsql() {
    //icnews_
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




