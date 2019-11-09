package live.liujun.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SqlSession {
    public <T> T getMapper(Class<T> cls){
        T o = (T)Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new myInvocationHandler());
        return  o;
    }
}
