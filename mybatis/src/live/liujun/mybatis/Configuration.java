package live.liujun.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;

public class Configuration {
    private DruidDataSource druidDataSource;
    HashMap<String , Mapper> map = new HashMap<>();

    public Configuration() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(Configuration.class.getResourceAsStream("/sqlMapConfig.xml"));
        Element driver = (Element) doc.selectSingleNode("//property[@name='driver']");
        String driverStr = driver.attributeValue("value");

        Element url = (Element) doc.selectSingleNode("//property[@name='url']");
        String urlStr = url.attributeValue("value");

        Element username = (Element) doc.selectSingleNode("//property[@name='username']");
        String usernameStr = username.attributeValue("value");

        Element password = (Element) doc.selectSingleNode("//property[@name='password']");
        String passwordStr = password.attributeValue("value");

        druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverStr);
        druidDataSource.setUrl(urlStr);
        druidDataSource.setUsername(usernameStr);
        druidDataSource.setPassword(passwordStr);

        Element resource = (Element) doc.selectSingleNode("//mapper[@resource]");
        String resourceStr = resource.attributeValue("resource");

        SAXReader readUserMapper = new SAXReader();
        Document docUserMapper = readUserMapper.read(Configuration.class.getResourceAsStream("/" + resourceStr));
        Element rootElement = docUserMapper.getRootElement();
        String naemsapceStr = rootElement.attributeValue("namespace");

        List<Element> elements = rootElement.elements();
        elements.forEach(element -> {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String sql = element.getTextTrim();

            String key = naemsapceStr + "." +id;
            Mapper mapper = new Mapper(resultType, sql);
            map.put(key,mapper);
        });


    }


    public DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }

    public HashMap<String, Mapper> getMap() {
        return map;
    }

}
