package com.endava.dao;


import com.endava.model.Student;
import com.endava.model.StudentTest;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentDAOUTest {

    public static final String ID = "560ce7e34706097f8184c4dc";

    private StudentTest studentTest = Mockito.mock(StudentTest.class);

    public StudentDAO studentDAO;

    DB db = new Fongo("testing").getDB("databaseTest");
    Jongo jongo = new Jongo(db);
    MongoCollection mongoCollection = jongo.getCollection("students");

    @Before
    public void setup() {
        studentDAO = new StudentDAO();
    }

    @Test
    public void createOneTest() {

        mongoCollection.insert(new StudentTest("s100"));
        mongoCollection.insert(new StudentTest("s101"));

        mongoCollection.update(new ObjectId(ID)).with("{$set: {'name': '" + "" + "'}}");

        assertThat(mongoCollection.count()).isEqualTo(2);
        assertThat(mongoCollection.findOne("{name: #}", "s100").as(Student.class)).isNotNull();
        assertThat(mongoCollection.findOne("{name: #}", "s101").as(Student.class)).isNotNull();
    }

    @Test
    public void updateTest() {

        mongoCollection.insert(new StudentTest("s100"));
        mongoCollection.update("{name: 's100'}").with(new StudentTest("s110"));
        assertThat(mongoCollection.findOne("{name: #}", "s100").as(Student.class)).isNull();
    }

    @Test
    public void deleteTest() {

        mongoCollection.insert(new StudentTest("s100"));
        mongoCollection.remove("{name: 's100'}");
        assertThat(mongoCollection.findOne("{name: #}", "s100").as(Student.class)).isNull();
    }


}

