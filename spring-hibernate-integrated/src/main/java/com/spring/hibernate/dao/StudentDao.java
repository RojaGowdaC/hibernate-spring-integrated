package com.spring.hibernate.dao;

import com.spring.hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class StudentDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void exit() {
        sessionFactory.close();
    }

    private void create() {
        Student student = new Student();
        student.setName("Roja");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();
    }

    private List<Student> read() {
        Session session = this.sessionFactory.openSession();
        List<Student> studentList = session.createQuery("from student").list();
        session.close();
        return studentList;
    }

    private void update() {
        Student student = new Student();
        student.setName("Roja Gowda");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student oldStudent = (Student) session.load(Student.class, 1);
        oldStudent.setName(student.getName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + oldStudent);
    }

    private void delete() {
        Student student = new Student();
        student.setId(2);
        @SuppressWarnings("unchecked")
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);

        session.getTransaction().commit();
        session.close();
    }
    public void performOperations() {
        Scanner sc = new Scanner(System.in);
        create();
        while(true) {
            int option;
            System.out.println("Enter your choice : ");
            System.out.println("1. Create \n 2. read\n 3. Update\n 4. Delete");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    create();
                    break;
                case 2:
                    System.out.println(read());
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                default:
                    exit();
                    return;
            }
        }
    }
}
