package com.zilker.service;

import java.util.ArrayList;

import com.zilker.bean.HallData;
import com.zilker.delegate.AddHallDelegate;
import com.zilker.ui.Hall;

public class HallService {
	
	AddHallDelegate addHallDelegate = new AddHallDelegate();

	public void hallValues(HallData hallData)
	{
		
		addHallDelegate.addHallProcess(hallData);
	}
	
	public ArrayList<HallData> hallListService() {
		
		return addHallDelegate.listHallProcess();
		
	}
	
	public void hallDisplayService(HallData hallData,int sno) {
		
		Hall hall = new Hall();
		
		hall.resultViewHallList(hallData,sno);
		
	}
	
	public ArrayList<String> displayHallFacilities() {
		
		return addHallDelegate.getHallfacilities();
	}
	
}
