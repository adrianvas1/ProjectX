package com.endava.service;
import com.endava.dao.StudentDAO;
import com.endava.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceUTest {

    private static final String ID = "test";

    private static final String CREATED = "success";
    private static final String ADDRESS = "name";
    private static final String NAME = "name";
    private static final String FILENAME = "name";

    private StudentService studentService;

    @Mock
    private StudentDAO studentDAOMock;
    @Mock
    private Student studentMock;
    @Mock
    private List<Student> studentListMock;

    @Before
    public void setUp() {
        studentService = new StudentService(studentDAOMock);
    }

    @Test
    public void read() {
        when(studentDAOMock.read(ID)).thenReturn(NAME);
        //when(studentMock.getName()).thenReturn(NAME);
        assertEquals(NAME, studentService.read(ID));
    }

    @Test
    public void readAll() {
        when(studentDAOMock.readAll()).thenReturn(studentListMock);
        when(studentListMock.size()).thenReturn(1);
        assertEquals(1, studentService.readAll().size());
    }

    @Test
    public void create() {
        when(studentDAOMock.create(ID, ADDRESS, FILENAME)).thenReturn(CREATED);
        assertEquals(CREATED, studentService.create(ID, ADDRESS, FILENAME));
    }

    @Test
    public void delete() {
        when(studentDAOMock.delete(ID)).thenReturn(true);
        assertEquals(true, studentService.delete(ID));
    }

    @Test
    public void update() {
        when(studentDAOMock.update(ID, NAME)).thenReturn(true);
        assertEquals(true, studentService.update(ID, NAME));
    }

    @Test
    public void writeToFile() throws IOException {
        when(studentDAOMock.writeToFile(CREATED, ID, NAME)).thenReturn(true);
        assertEquals(true, studentService.writeToFile(CREATED, ID, NAME));
    }

}
