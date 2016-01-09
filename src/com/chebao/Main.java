package com.chebao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 引入gson库,解析json文件
 */

public class Main {

    public static void main(String[] args) {
//  test.json
//        {
//            "cat":"it",
//                "languages":[
//            {"id":1,"ide":"Eclipse","name":"Java"},
//            {"id":2,"ide":"Xcode","name":"Swift"},
//            {"id":3,"ide":"Visul","name":"C#"}
//            ],
//            "pop":true
//        }

        try {




            //json解析器
            //作用:解析字符串和输入流
            JsonParser parser = new JsonParser();

            //json对象
            //可指定文件或者网络
            //test.json 是本地文件,可指定路径
            JsonObject object = (JsonObject) parser.parse(new FileReader("test.json"));


            //首先输出根元素的值
            //cat: 是json文件的键Key,"cat":"it",
            // 1.get(返回的类型是json原件,"cat"的值是"it",是字符串类型,所以需要用getAsString)
            // 2.get(返回的类型是json原件,"pop"的值是true是boolean类型,所以需要用getAsBoolean())
            //
            System.out.println("cat="+object.get("cat").getAsString());
            System.out.println("pop="+object.get("pop").getAsBoolean());


            //解析json数组 languages[]
            // 3.get(返回的类型是json原件,"languages"的值是数组,所以需要用getAsJsonArray())
            JsonArray array = object.get("languages").getAsJsonArray();

            //输出数组内的三个对象
            for (int i = 0; i < array.size(); i++) {
                System.out.println("-------分割线-------");

                //languages内部还有3个对象,
                //i,依次获取三个对象
                //4.get(返回的类型是json原件,但是三个值都是json对象类型,所以需要用getAsJsonObject())
                JsonObject subObject = array.get(i).getAsJsonObject();

                //输出对象的属性
                //5.get(返回的类型是json原件,"id"的值是int,所以需要用getAsInt())
                System.out.println("id="+ subObject.get("id").getAsInt());
                System.out.println("ide="+ subObject.get("ide").getAsString());
                System.out.println("name="+ subObject.get("name").getAsString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
