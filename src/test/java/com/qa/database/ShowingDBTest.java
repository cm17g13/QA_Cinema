package com.qa.database;

import com.qa.database.ShowingDBRepo;
import com.qa.persistence.Showing;
import com.qa.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ShowingDBTest {

	
	@InjectMocks
	private ShowingDBRepo showingManager;

    private JSONUtil jsonConverter;
	
	@Mock
    private EntityManager manager;

	@Mock
    private TypedQuery<Showing> query;
	
	private Showing showing1 = new Showing("tt1396484", "It", "2017","4:30", "1", 100, 10, "https://ia.media-imdb.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SY1000_CR0,0,666,1000_AL_.jpg");

	String showingString1 = "{\"OMDbCode\":\"tt1396484\",\"title\":\"It\",\"year\":\"2017\",\"time\":\"4:30\",\"screen\":\"1\",\"standardSeats\":100,\"disabledSeats\":10,\"imageURL\":\"https://ia.media-imdb.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SY1000_CR0,0,666,1000_AL_.jpg\"}";
	
	@Before
	public void before() {
		showingManager.setEntityManager(manager);
		this.jsonConverter = new JSONUtil();
		showingManager.setJsonConverter(jsonConverter);
		showingManager.createShowing(showingString1);
	}

	@Test
	public void getAllShowingsTest() {
        Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(Showing.class))).thenReturn(query);
        ArrayList<Showing> showings = new ArrayList<Showing>();
        showings.add(showing1);
        Mockito.when(query.getResultList()).thenReturn(showings);
        String showingArray = "[" + showingString1 + "]";
        Assert.assertEquals(showingArray, showingManager.getAllShowings());
	}
	

	@Test
	public void findAnShowingTest() {
        Mockito.when(manager.find(Mockito.eq(Showing.class), Mockito.anyLong())).thenReturn(showing1);
        Assert.assertEquals(showingString1, showingManager.findAShowing(1L));
	}

	@Test
	public void createShowingTest() {
		Mockito.when(manager.find(Mockito.eq(Showing.class), Mockito.anyLong())).thenReturn(null);
        Assert.assertEquals("{\"message\": \"showing sucessfully added\"}", showingManager.createShowing(showingString1));
    }

	@Test
	public void updateShowingTest() {
		Mockito.when(manager.find(Mockito.eq(Showing.class), Mockito.anyLong())).thenReturn(showing1);
        Assert.assertEquals("{\"message\": \"the showing has been updated\"}", showingManager.updateShowing(showing1, "{\"message\": \"the showing has been updated\"}", "{\"message\": \"showing doesn't exist, could not updated\"}"));

	}

	@Test
	public void deleteShowingTest() {
		Mockito.when(manager.find(Mockito.eq(Showing.class), Mockito.anyLong())).thenReturn(showing1);
        Mockito.when(query.getSingleResult()).thenReturn(showing1);
        Assert.assertEquals("{\"message\": \"the showing has been deleted\"}", showingManager.deleteShowing(1L));

    }
	
}
