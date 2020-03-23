package com.spring.hibernate.main;

import com.spring.hibernate.dao.StudentDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = context.getBean(StudentDao.class);
        studentDao.performOperations();
        context.close();
    }
}
