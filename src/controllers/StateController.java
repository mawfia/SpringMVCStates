package controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.ImageOftheDay;
import data.State;
import data.StateDAO;

@Controller
@SessionAttributes("allStates")
public class StateController {
	@Autowired
	private StateDAO stateDao;

	@ModelAttribute("allStates")
	public List<String> initStates(){
		List<String> list = stateDao.getStateNameList();
		return list;
	}
	
	@RequestMapping(path="GetStateData.do", 
			params="name",
			method=RequestMethod.GET)
	public ModelAndView getByName(@RequestParam("name") String n) {
//		public ModelAndView getByName(@RequestParam("name") String n, @ModelAttribute("allStates") List<String> thelist) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("state", stateDao.getStateByName(n));
		int register = stateDao.getStateIndex(n);
		register++;
		if (register > 50) register = 1;
			mv.addObject("nextState", stateDao.getStateNamebyIndex(register));
		register = stateDao.getStateIndex(n);
		register--;
		if (register < 1) register = 50;	
			mv.addObject("previousState", stateDao.getStateNamebyIndex(register));
		try {
			mv.addObject("bingImage", new ImageOftheDay().bingImage());
		} catch (IOException e) {
			mv.addObject("bingImage", "default.jpg");
		}
		return mv;
	}
	
	@RequestMapping(path="GetStateData.do", 
			params="abbr",
			method=RequestMethod.GET)
	public ModelAndView getByAbbrev(@RequestParam("abbr") String a) {
		ModelAndView mv = getByName(stateDao.getStateByAbbreviation(a).getName());
		mv.setViewName("result.jsp");
		return mv;
	}

	@RequestMapping(path="NewState.do",
			method=RequestMethod.POST)
	public ModelAndView newState(State state) {
		stateDao.addState(state);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		int register = state.getIndex();
		register++;
		if (register > 50) register = 1;
			mv.addObject("nextState", stateDao.getStateNamebyIndex(register));
		register = state.getIndex();
		register--;
		if (register < 1) register = 50;	
			mv.addObject("previousState", stateDao.getStateNamebyIndex(register));
		try {
			mv.addObject("bingImage", new ImageOftheDay().bingImage());
		} catch (IOException e) {
			mv.addObject("bingImage", "default.jpg");
		}
		return mv;
	}
	
	@RequestMapping(path="GetNextStateData.do", 
			params="previous",
			method=RequestMethod.GET)
	public ModelAndView setPreviousButton(@RequestParam("previous") String p) {
		ModelAndView mv = getByName(p);
		return mv;
	}
	
	@RequestMapping(path="GetNextStateData.do", 
			params="next",
			method=RequestMethod.GET)
	public ModelAndView setNextButton(@RequestParam("next") String n) {
		ModelAndView mv = getByName(n);
		return mv;
	}
}
