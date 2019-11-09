package live.liujun.testMybatis;

import live.liujun.dao.UserMapper;
import live.liujun.entity.Student;
import live.liujun.mybatis.SqlSession;
import org.junit.Test;

import java.util.List;

public class test {
    @Test
    public void test(){
        SqlSession sqlSession= new SqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Student> allStudent = mapper.findAllStudent();
        System.out.println(allStudent);

    }

}
