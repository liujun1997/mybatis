package live.liujun.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class myInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Configuration configuration = new Configuration();
        DruidDataSource druidDataSource = configuration.getDruidDataSource();
        HashMap<String, Mapper> map = configuration.getMap();

        String interfaceName = method.getDeclaringClass().getName();
        String methodName = method.getName();

        String key = interfaceName + "." + methodName;

        Mapper mapper = map.get(key);
        String resultType = mapper.getResultType();
        String sql = mapper.getSql();
        DruidPooledConnection conn = druidDataSource.getConnection();
        PreparedStatement pre = conn.prepareStatement(sql);
        if (resultType==null){
            pre.executeUpdate();
            return  null;
        }
        ResultSet res = pre.executeQuery();

        Class<?> cls = Class.forName(resultType);
        ArrayList<Object> arrayList = new ArrayList<>();
        while (res.next()){
            Field[] declaredFields = cls.getDeclaredFields();
            Object o = cls.newInstance();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                declaredField.set(o,res.getObject(declaredField.getName()));
            }
            arrayList.add(o);
        }



        return  arrayList;
    }
}
