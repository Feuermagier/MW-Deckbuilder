/**
 * This class was added at 16.11.2016.
 * 
 */
package application.mages;

import mwdeckbuilder.logic.Card;
import mwdeckbuilder.logic.Type;

/**
 * @author Florian
 *
 */
public class AcademyPriestess extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Academy Priestess";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		if(school == School.HOLY) {
			return level;
		} else if(school == School.DARK) {
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
		return "Priestess, Holy, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Academy Priestess";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "32";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getState()
	 */
	@Override
	public String getState() {
		return "Academy/Official";
	}
	
	public boolean getNovice(School school) {
		if(school == School.DARK)
			return false;
		else
			return true;		
	}
	
	public boolean isAcademy() {
		return true;
	}
	
}
