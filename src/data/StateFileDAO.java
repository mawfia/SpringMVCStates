package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class StateFileDAO implements StateDAO {
	private static final String FILE_NAME1="/WEB-INF/states.csv";
	private static final String FILE_NAME2="/WEB-INF/statetax.csv";
	private static final String FILE_NAME3="/WEB-INF/statedob.csv";
	private List<State> states = new ArrayList<>();
	public static int count = 0;
	/*
	 * Use Autowired to have Spring inject an instance
	 * of a WebApplicationContext into this object after
	 * creation. We will use the WebApplicationContext to
	 * retrieve a ServletContext so we can read from a 
	 * file.
	 */
	@Autowired 
	private WebApplicationContext wac;

	/*
	 * The @PostConstruct method is called by Spring after 
	 * object creation and dependency injection
	 */
	@PostConstruct
	public void init() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME1);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			) {
			String line = buf.readLine();
			int counter = 1;
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				int index = Integer.parseInt(tokens[0]);
				String abbrev = tokens[1];
				String name = tokens[2];
				String capital = tokens[3];
				String latitude = tokens[4];
				String longitude = tokens[5];
				int population = Integer.parseInt(tokens[6]);
				states.add(new State(index, abbrev, name, capital, latitude, longitude, population));
			}
			inittax();
			initdob();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void inittax() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME2);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String abbrev = tokens[1];
				String statetax = tokens[3];
				State state=getStateByAbbreviation(abbrev);
				state.setTaxrate(statetax);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void initdob() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME3);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
				) {
			String line;
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String abbrev = tokens[0];
				String name = tokens[1];
				String capital = tokens[2];
				String month = tokens[3];
				String day = tokens[4];
				String year = tokens[5];
				State state=getStateByAbbreviation(abbrev);
				state.setDob(month + " " + day + "," + " " + year);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	
	@Override
	public State getStateByName(String name) {
		State s = null;
		for (State state : states) {
			if (state.getName().equalsIgnoreCase(name)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public State getStateByAbbreviation(String abbrev) {
		State s = null;
		for (State state : states) {
			if (state.getAbbreviation().equalsIgnoreCase(abbrev)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public void addState(State state) {
		state.setIndex(states.size());
		states.add(state);
		count++;
	}
	@Override
	public int getStateIndex(String name) {
		int index = 0;
		if (name.length()>2){
			for (State state : states) {
				if (state.getName().equalsIgnoreCase(name)) {
					index = state.getIndex();
					break;
				}
			}
		}
		else {
			for (State state : states) {
				if (state.getAbbreviation().equalsIgnoreCase(name)) {
					index = state.getIndex();
					break;
				}
			}
		}
		return index;
	}
	@Override
	public String getStateNamebyIndex(int index) {
		String name = null;
		for (State state : states) {
			if (state.getIndex() == index) {
				name = state.getName();
				break;
			}
		}
		return name;
	}

	@Override
	public List<String> getStateNameList() {
		List<String> s = new ArrayList<String>();
		for (State state : states) {
			s.add(state.getName());
		}
		return s;
	}
}
