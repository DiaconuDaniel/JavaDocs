package ro.teamnet.zth.api.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartamentDao;
import ro.teamnet.zth.appl.domain.Location;

import static org.junit.Assert.assertNull;

/**
 * Created by Daniel.Diaconu on 17/07/14.
 */
public class LocationDao extends DepartamentDao{

    Location location = new Location();
    LocationDao locationDao = new LocationDao();

//    @Test
//    public void cTestDeleteLocation() {
//        locationDao.deleteLocation(location);
//        Location locById = locationDao.getClass(location.getId());
//
//        assertNull(locById);
//    }



}
