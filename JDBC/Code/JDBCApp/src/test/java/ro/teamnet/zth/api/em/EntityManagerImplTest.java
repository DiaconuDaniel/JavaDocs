package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class EntityManagerImplTest{
    EntityManagerImpl entityManager = new EntityManagerImpl();
    Department department = new Department();

    @Test
    public void aTestFindById(){
        department = entityManager.findById(Department.class, 10L);
        assertTrue(department.getId()==10L);
        assertTrue(department.getDepartmentName().equals("Administration"));
    }

    @Test
    public void testFindByIdNull() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Department entry1 = testClass.findById(Department.class, null);
        Department entry2 = testClass.findById(null, new Long(20));

        assertEquals(null, entry1);
        assertEquals(null, entry2);
    }

    @Test
    public void testInsert(){
        EntityManagerImpl testClass = new EntityManagerImpl();

        Department elem = new Department();

        elem.setDepartmentName("DepatmentTest");
        elem.setLocation(new Long(3200));

        Location elem1 = new Location();

        elem1.setCity("Test");
        elem1.setPostalCode("33324");
        elem1.setStateProvince("Province");
        elem1.setStreetAddress("Adress");

    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Long entry1 = testClass.getNextIdVal("departments", "department_id");
        Long entry2 = testClass.getNextIdVal("locations", "location_id");

        assertEquals(new Long(271), entry1);
        assertEquals(new Long(3201), entry2);
    }

    @Test
    public void testGetNextIdValNull() {
        EntityManagerImpl testClass = new EntityManagerImpl();

        Long entry1 = testClass.getNextIdVal(null, "department_id");
        Long entry2 = testClass.getNextIdVal("locations", null);

        assertEquals(null, entry1);
        assertEquals(null, entry2);
    }


    @Test
    public void dTestUpdate() throws NoSuchFieldException, IllegalAccessException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = new Department();
        department.setId(270L);
        department.setDepartmentName("Update");
        department.setLocation(1700L);
        department = entityManager.update(department);
        assertTrue(department.getDepartmentName().equals("Update"));
    }

    @Test
    public void eTestDelete(){
        department.setId(271L);
        entityManager.delete(department);
        assertTrue(entityManager.findById(Department.class, department.getId())==null);
    }

    @Test
    public void fTestFindByParams(){
        Map<String, Object> params = new HashMap<>();
        params.put("location_id",1700L);
        List<Department> departments = entityManager.findByParams(Department.class, params);
        assertEquals(departments.size(),21);
    }
}

