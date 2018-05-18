package com.stylefeng.guns.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.List;
import java.util.Map;

/**
 * @描述:LM系统的订单解析
 * @创建人: JaccePon
 * @创建日期: 2018年05月15日 5:43 PM
 */

public class JasonParseLmUtil {


    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }



    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson  = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }


    public static void  main (String [] args){
        String str="{\"order\":{\"isNewRecord\":true,\"delFlag\":\"0\",\"exportFileName\":\"\",\"backPageNo\":0,\"backPageSize\":0,\"backMethod\":\"\",\"sequence\":\"\",\"commodity\":{\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"\",\"code\":\"\"},\"customer\":{\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"\",\"mobile\":\"\"},\"address\":{\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"districtAddress\":\"\"},\"count\":0,\"orderSource\":\"\",\"guideBy\":{\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"orderFlag\":\"\",\"series\":{\"id\":\"\",\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0},\"classify\":{\"id\":\"\",\"isNewRecord\":true,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0},\"beginDate\":\"2018-05-15\",\"endDate\":\"2018-05-15\",\"deliverWaitingPeriod\":100},\"pageNo\":1,\"pageSize\":10,\"iTotalRecords\":4,\"iTotalDisplayRecords\":4,\"aaData\":[{\"id\":\"ab6339a5ab684b72be0f4e8591c0f490\",\"isNewRecord\":false,\"remarks\":\"同一客户2件，有货先发，请仔细验货哦，感谢!\",\"createBy\":{\"id\":\"52d227975d414e9d8cf4055b46355c3f\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"createDate\":\"2018-05-15 16:20:55\",\"updateBy\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"updateDate\":\"2018-05-15 16:55:29\",\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"sequence\":\"20180515355975\",\"terminal\":\"A\",\"imagePath\":\"/mnt/share/image/commodity/f0e8171d70b44b50822e06c977d41e4f.jpg\",\"imageUrl\":\"/image/commodity/f0e8171d70b44b50822e06c977d41e4f.jpg\",\"commodity\":{\"id\":\"9887e651b7ae4666aee2e34ae1038e42\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"时尚拼接镂空收腰修身蕾丝连衣裙\",\"code\":\"62502118SPD150428CCLM 2160/1080\",\"counterPrice\":2160.0},\"color\":{\"id\":\"48eb807d4f6c43dbaafae0ffc9b2054d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"紫色\"},\"size\":\"XL\",\"customer\":{\"id\":\"a94bf896cc7241b09a864fab60e855dc\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"高山玲\",\"mobile\":\"13348972111\",\"height\":\"\",\"weight\":\"\"},\"address\":{\"id\":\"b200630dbdfe47ef880ef7f83fedabf0\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"provinceId\":\"510000\",\"cityId\":\"510100\",\"districtId\":\"510122\",\"districtAddress\":\"四川省成都市双流县四川省成都市天府新区海昌路133号新鸿基悦城5栋1004号\"},\"count\":1,\"orderSource\":\"0\",\"wechatOrderId\":\"\",\"guideBy\":{\"id\":\"52d227975d414e9d8cf4055b46355c3f\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"庞吉希-恒华\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"discountedPrice\":1080.0,\"dealerPrice\":561.6,\"agentPriceLv1\":648.0,\"agentPriceLv2\":734.4,\"agentPriceLv3\":0.0,\"dealerDiscount\":0.52,\"agentDiscountLv1\":0.6,\"agentDiscountLv2\":0.68,\"agentDiscountLv3\":0.0,\"auditFlag\":\"13\",\"auditDate\":\"2018-05-15 16:55:29\",\"auditNode\":\"end\",\"processInstanceId\":\"8cad71944b1549e891c7fc218620b861\",\"service\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"客服专员-A\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"dealer\":{\"id\":\"88134899c5f649689951f9be91423a0d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"欧丽娥-陈道宇\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"deliverFlag\":\"21\",\"wastage\":0.0,\"series\":{\"id\":\"8126b7795c5546cdbf6095ffbd84c83e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"连衣裙\"},\"classify\":{\"id\":\"7903e0418c0b4be4b546d6e03ffa182b\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"女装\"},\"deliverWaitingPeriod\":100},{\"id\":\"005f193397104369823ddb44b93eedac\",\"isNewRecord\":false,\"remarks\":\"同一客户2件，有货先发。辛苦了，麻烦验货仔细哦!\",\"createBy\":{\"id\":\"52d227975d414e9d8cf4055b46355c3f\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"createDate\":\"2018-05-15 15:02:27\",\"updateBy\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"updateDate\":\"2018-05-15 16:55:29\",\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"sequence\":\"20180515355906\",\"terminal\":\"A\",\"imagePath\":\"/mnt/share/image/commodity/4c983a93cc254aabb07b846c28ae30b0.jpg\",\"imageUrl\":\"/image/commodity/4c983a93cc254aabb07b846c28ae30b0.jpg\",\"commodity\":{\"id\":\"2ee851bd72b14206a106380563334093\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"连衣裙\",\"code\":\"57854118SD60514XXJLMY 2610/1305\",\"counterPrice\":2610.0},\"color\":{\"id\":\"36d18a76630848298e4b07d4ffea5797\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"黄色\"},\"size\":\"XL\",\"customer\":{\"id\":\"a94bf896cc7241b09a864fab60e855dc\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"高山玲\",\"mobile\":\"13348972111\",\"height\":\"\",\"weight\":\"\"},\"address\":{\"id\":\"b200630dbdfe47ef880ef7f83fedabf0\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"provinceId\":\"510000\",\"cityId\":\"510100\",\"districtId\":\"510122\",\"districtAddress\":\"四川省成都市双流县四川省成都市天府新区海昌路133号新鸿基悦城5栋1004号\"},\"count\":1,\"orderSource\":\"0\",\"wechatOrderId\":\"\",\"guideBy\":{\"id\":\"52d227975d414e9d8cf4055b46355c3f\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"庞吉希-恒华\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"discountedPrice\":1305.0,\"dealerPrice\":678.6,\"agentPriceLv1\":783.0,\"agentPriceLv2\":887.4,\"agentPriceLv3\":0.0,\"dealerDiscount\":0.52,\"agentDiscountLv1\":0.6,\"agentDiscountLv2\":0.68,\"agentDiscountLv3\":0.0,\"auditFlag\":\"13\",\"auditDate\":\"2018-05-15 16:55:29\",\"auditNode\":\"end\",\"processInstanceId\":\"10db9c97e36b48dcbd24a176c510c111\",\"service\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"客服专员-A\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"dealer\":{\"id\":\"88134899c5f649689951f9be91423a0d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"欧丽娥-陈道宇\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"deliverFlag\":\"23\",\"wastage\":0.0,\"series\":{\"id\":\"8126b7795c5546cdbf6095ffbd84c83e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"连衣裙\"},\"classify\":{\"id\":\"7903e0418c0b4be4b546d6e03ffa182b\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"女装\"},\"deliverWaitingPeriod\":100},{\"id\":\"cdf8cc2e2be247c09d8795c3b60dc5f9\",\"isNewRecord\":false,\"remarks\":\"同一客户两件，麻烦一起发货，谢谢辛苦啦\",\"createBy\":{\"id\":\"8c8e4920a0c146229af75bda1867ecd9\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"createDate\":\"2018-05-15 10:21:24\",\"updateBy\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"updateDate\":\"2018-05-15 16:55:29\",\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"sequence\":\"20180515355775\",\"terminal\":\"A\",\"imagePath\":\"/mnt/share/image/commodity/c8478e003fb24fd796ba32a4d853e932.jpg\",\"imageUrl\":\"/image/commodity/c8478e003fb24fd796ba32a4d853e932.jpg\",\"commodity\":{\"id\":\"f96b3094b8db47f786e0135b52989c26\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"三件套\",\"code\":\"52656118SSU130502LBSLM 2970/1485\",\"counterPrice\":2970.0},\"color\":{\"id\":\"eedd6221990646d18ff169d83080c499\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"图片色\"},\"size\":\"L\",\"customer\":{\"id\":\"1dcce001750e47ec957d46afdeb3f2ba\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"杨霖\",\"mobile\":\"13678796598\"},\"address\":{\"id\":\"b27e1e4e05984d8fae31849aed3b0c79\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"provinceId\":\"530000\",\"cityId\":\"530100\",\"districtId\":\"530111\",\"districtAddress\":\"云南省昆明市官渡区世纪城迎春园一号楼二单元16d\"},\"count\":1,\"orderSource\":\"0\",\"guideBy\":{\"id\":\"8c8e4920a0c146229af75bda1867ecd9\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"庞吉希-庞燕虹\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"discountedPrice\":1485.0,\"dealerPrice\":772.2,\"agentPriceLv1\":891.0,\"agentPriceLv2\":1009.8,\"agentPriceLv3\":0.0,\"dealerDiscount\":0.52,\"agentDiscountLv1\":0.6,\"agentDiscountLv2\":0.68,\"agentDiscountLv3\":0.0,\"auditFlag\":\"13\",\"auditDate\":\"2018-05-15 16:55:29\",\"auditNode\":\"end\",\"processInstanceId\":\"696e352cc4984bcc8da16a2f38a12771\",\"service\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"客服专员-A\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"dealer\":{\"id\":\"88134899c5f649689951f9be91423a0d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"欧丽娥-陈道宇\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"deliverFlag\":\"21\",\"wastage\":0.0,\"series\":{\"id\":\"e54b14bcb1b645da8de2c60b315a5a66\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"套装\"},\"classify\":{\"id\":\"7903e0418c0b4be4b546d6e03ffa182b\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"女装\"},\"deliverWaitingPeriod\":100},{\"id\":\"22066d3f5e9c4e9c937f14e088096aa5\",\"isNewRecord\":false,\"remarks\":\"麻烦检查后发货，谢谢，辛苦啦\",\"createBy\":{\"id\":\"8c8e4920a0c146229af75bda1867ecd9\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"createDate\":\"2018-05-15 00:09:39\",\"updateBy\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"updateDate\":\"2018-05-15 16:55:30\",\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"sequence\":\"20180515355694\",\"terminal\":\"A\",\"imagePath\":\"/mnt/share/image/commodity/2e34064ea2144dd49f8c542bba61e8d2.jpg\",\"imageUrl\":\"/image/commodity/2e34064ea2144dd49f8c542bba61e8d2.jpg\",\"commodity\":{\"id\":\"9a3a9b00ebc64dd790596a2ea59fcb93\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"欧美显瘦雪纺绣花连衣裙\",\"code\":\"75203118SD0512SMLM 2340/1170\",\"counterPrice\":2340.0},\"color\":{\"id\":\"9d693e1535cc4855a7a8ecd40a970a2d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"黑色\"},\"size\":\"L\",\"customer\":{\"id\":\"1dcce001750e47ec957d46afdeb3f2ba\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"杨霖\",\"mobile\":\"13678796598\"},\"address\":{\"id\":\"b27e1e4e05984d8fae31849aed3b0c79\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"provinceId\":\"530000\",\"cityId\":\"530100\",\"districtId\":\"530111\",\"districtAddress\":\"云南省昆明市官渡区世纪城迎春园一号楼二单元16d\"},\"count\":1,\"orderSource\":\"0\",\"guideBy\":{\"id\":\"8c8e4920a0c146229af75bda1867ecd9\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"庞吉希-庞燕虹\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"discountedPrice\":1170.0,\"dealerPrice\":608.4,\"agentPriceLv1\":702.0,\"agentPriceLv2\":795.6,\"agentPriceLv3\":0.0,\"dealerDiscount\":0.52,\"agentDiscountLv1\":0.6,\"agentDiscountLv2\":0.68,\"agentDiscountLv3\":0.0,\"auditFlag\":\"13\",\"auditDate\":\"2018-05-15 16:55:30\",\"auditNode\":\"end\",\"processInstanceId\":\"1f0a723d0a7f47aaa72a15d52cdbb4ef\",\"service\":{\"id\":\"79525354cf094709bbaf51385c98f96e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"客服专员-A\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"dealer\":{\"id\":\"88134899c5f649689951f9be91423a0d\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"欧丽娥-陈道宇\",\"loginFlag\":\"1\",\"needPay\":\"0\",\"chargeType\":\"test\",\"testUnit\":\"D\",\"adservice\":false,\"admin\":false},\"deliverFlag\":\"23\",\"wastage\":0.0,\"series\":{\"id\":\"8126b7795c5546cdbf6095ffbd84c83e\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"连衣裙\"},\"classify\":{\"id\":\"7903e0418c0b4be4b546d6e03ffa182b\",\"isNewRecord\":false,\"delFlag\":\"0\",\"backPageNo\":0,\"backPageSize\":0,\"name\":\"女装\"},\"deliverWaitingPeriod\":100}],\"amount\":5040.0}";
        Map map = JasonParseLmUtil.stringToCollect(str);
        System.out.println("");
    }

}
