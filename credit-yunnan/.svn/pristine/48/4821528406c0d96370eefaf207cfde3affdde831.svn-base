package com.ccb.portal.util;


import net.sf.json.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import javax.xml.bind.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @描述：xml转换
 *
 *
 */
public class XmlUtil {

    /**
     * 对象转xml
     *
     * @param c      对象class
     * @param object 对象
     * @return
     */
    public static String ObjectToXml(Class c, Object object) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //防止文件中文乱码
            m.marshal(object, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }


    /**
     * @param c   对象class
     * @param xml xml数据
     * @return
     */
    public static Object XmlToObject(Class c, String xml) {
        try {
            JAXBContext context2 = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context2.createUnmarshaller();
            // Object object = unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes("GBK")));
            Object object = unmarshaller.unmarshal(new StringReader(xml));
            return object;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
/**
 * 废弃不好用
 */

    public  static JSONObject xmlToJson(String xmlstr){
        byte[]xml=xmlstr.getBytes();
       JSONObject jsonObject=new JSONObject();
       InputStream is=new ByteArrayInputStream(xml);
        try {
            SAXBuilder saxBuilder=new SAXBuilder();
            Document doc=saxBuilder.build(is);
            Element root=doc.getRootElement();
            jsonObject.put(root.getName(),iterElement(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject iterElement(Element element){
        List node=element.getChildren();
        Element et=null;
        JSONObject jsonObject=new JSONObject();
        List list=null;
        for(int i=0;i<node.size();i++){
            list=new LinkedList();
            et=(Element) node.get(i);
            if(et.getTextTrim().equals("")){
                if(et.getChildren().size()==0){
                    continue;
                }
                if(jsonObject.containsKey(et.getName())){
                    list=(List)jsonObject.get(et.getName());
                }
                list.add(iterElement(et));
                jsonObject.put(et.getName(),list);
            }else{
                if(jsonObject.containsKey(et.getName())){
                    list=(List)jsonObject.get(et.getName());
                }
                list.add(et.getTextTrim());
                jsonObject.put(et.getName(),list);
            }
        }
        return jsonObject;
    }
    public static void main(String[] args) {
//        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><xmltest><id>1</id><rlNo>3</rlNo><rlNm>test</rlNm></xmltest>";
//        org.json.JSONObject jsonObjec1= XML.toJSONObject(xml);//xml转json
//        String jb= jsonObjec1.get("xmltest").toString();
//        JSONObject jsonObject=JSONObject.fromObject(jb);
//        System.out.println("xmltest----"+jsonObject.toString());
//        System.out.println("xmltojson["+jsonObjec1.toString()+"]");
//        Role role=(Role) XmlToObject(Role.class,xml);
//        JSONObject jsonObject2=JSONObject.fromObject(role);
//        System.out.println("beantojsonstr["+jsonObject2.toString()+"]");
//        System.out.println("xmltobean["+role+"]");
//        String benxml=ObjectToXml(Role.class,role);
//        System.out.println("beantoxml["+benxml+"]");
////        {"xmltest":{"id":1,"rlNm":"test","rlNo":3}}
    }

    public static Object XmlToObject(Class c, File xml) {
        try {
            JAXBContext context2 = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context2.createUnmarshaller();
            Object object = unmarshaller.unmarshal(xml);
            return object;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ReaderJson(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader bufReader = new BufferedReader(fileReader);

            LineNumberReader reader = new LineNumberReader(bufReader);

            String line;

            try {
                while ((line = reader.readLine()) != null) {

                    sb.append(line).append(System.getProperty("line.separator"));

                }
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            String content = sb.toString();

            System.out.println(content);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return sb.toString();
    }
}
