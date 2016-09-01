package data;

import java.util.List;

public interface StateDAO {
	public State getStateByName(String name);
	public State getStateByAbbreviation(String abbreviation);
	public void addState(State state);
	public int getStateIndex(String name);
	public String getStateNamebyIndex(int index);
	public List<String> getStateNameList();
}
