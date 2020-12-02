package util;

import java.util.Comparator;

import model.ReimbursementDTO;

public class SortByStatus implements Comparator<ReimbursementDTO> {

	@Override
	public int compare(ReimbursementDTO o1, ReimbursementDTO o2) {
		// TODO Auto-generated method stub
	
		if(o1.getStatus().equals("Pending")&& o2.getStatus().equals("Pending"))
		{
			return 0;
		}
		else if(o1.getStatus().equals("Pending")&& o2.getStatus().equals("Pending"))
		{
			return -1;
		}
		else {
			return 1;
		}
	}

}
