package live.liujun.dao;

import live.liujun.entity.Student;

import java.util.List;

public interface UserMapper {
    List<Student> findAllStudent();
}
