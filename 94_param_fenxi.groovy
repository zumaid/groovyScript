/**
 * Created by xuping on 15-9-27.
 */
/**
 * Created by xuping on 15-9-26.
 */
import com.gmongo.GMongo
// Instantiate a com.gmongo.GMongo object instead of com.fenxi_shuxing_93.Mongo
// The same constructors and methods are available here
def mongo = new GMongo()

// Get a db reference in the old fashion way
def db = mongo.getDB("ic")

//分析 94供应商的所有的属性信息,相同属

// 值对应的产品信息
def al=db.product_94_catalog_param.find(系列:'A16')
al.each{
    println( it )
}
