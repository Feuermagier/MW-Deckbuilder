/**
 * This class was added at 13.11.2016.
 * 
 */
package application.mages;

import mwdeckbuilder.logic.Card;
import mwdeckbuilder.logic.MWEnums.School;
import mwdeckbuilder.logic.Type;

/**
 * @author Florian
 *
 */
public class ArraxianCroneWarlock extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Warlock";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		
		if(school == School.DARK) {
			return level;
			
		} else if(school == School.FIRE) {
		    return level;	
		
			
		} else if(school == School.HOLY) {
			return level*3;
			
		} else {
			return level*2;		
	    }
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCanUse()
	 */
	@Override
	public String getCanUse() {
		return "Warlock, Dark, Fire, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Core";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "2253ac7a-0146-4022-bd2e-4f5403ad8e1d";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "13";
	}
	
	@Override
	public String getState() {
		return "Arena/Official";
	}
}
