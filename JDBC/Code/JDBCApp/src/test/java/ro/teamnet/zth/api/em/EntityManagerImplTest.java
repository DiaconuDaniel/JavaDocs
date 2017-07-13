package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class EntityManagerImplTest extends EntityManagerImpl{
    EntityManagerImpl entityManager = new EntityManagerImpl();
    static Department depatament = new Department();

    @Test
    public void aTestFindById(){
        depatament = entityManager.findById(Department.class, 9L);
        assertTrue(depatament.getId()==9L);
        assertTrue(depatament.getDepartmentName().equals("Administration"));
    }



}
