package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by Daniel.Diaconu on 17/07/14.
 */
public class DepartamentDao {

    EntityManager entityManager = new EntityManagerImpl() ;

       public Department findDepartmentById(Long id){
           return (Department)entityManager.findById(Department.class, id);
       }

       public List<Department> findAllDepartment(){
           return entityManager.findAll(Department.class);
       }

       public Department insertDepartment(Department department){
           return (Department) entityManager.insert(department);
       }

       public Department updateDepartment(Department department) throws NoSuchFieldException, IllegalAccessException {

           return entityManager.update(department);

       }

       public void deleteDepartment(Department department){
           entityManager.delete(department);
       }



}
