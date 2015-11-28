/**
 * Created by xuping on 15-9-26.
 */
import com.gmongo.GMongo
// Instantiate a com.gmongo.GMongo object instead of com.fenxi_shuxing_93.Mongo
// The same constructors and methods are available here
def mongo = new GMongo()

// Get a db reference in the old fashion way
def db = mongo.getDB("ic")

//分析 93供应商的所有的属性信息。
//找出属性与分类的对应关系
//找出每个属性的所有的值
def al=db.product_93.find().limit(10)
al.each{
    def c=[catalog:it.catalog]
    c.pid=it.id
    it.param.each{
        k , v->
            println "${k}--${v}"
            c."${k}"=v
    }
    println( c )
    db.product_93_catalog_param<<c
}
