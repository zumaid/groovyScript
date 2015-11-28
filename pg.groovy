/**
 * Created by xuping on 15-9-26.
 */
import com.alibaba.fastjson.JSON

/**
 * Created by xuping on 15-9-26.
 */
import groovy.sql.Sql
import com.gmongo.GMongo

def mongo = new GMongo()
def db = mongo.getDB("ic")
def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/pm",
        "zuaa", "aaaaaaaa", "org.postgresql.Driver")


100000.times { t ->
    println t;
    sql.eachRow("select * from pm_product where supplier_id =94 limit 10000 offset ${t * 10000}") { it ->
        try{
            db.product_94 << ["pn": it.pn, "id": it.id, "supplier_pn": it.supplier_pn, "mfs": it.mfs, "pkg": it.pkg, "catgalog": it.catalog, param: JSON.parseObject(it.param, HashMap.class)];

        }catch (e){

        }
    }
}
