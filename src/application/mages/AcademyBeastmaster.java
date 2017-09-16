/**
 * This class was added at 15.11.2016.
 * 
 */
package application.mages;

import application.builder.Card;
import application.builder.School;
import application.builder.Type;

/**
 * @author Florian
 *
 */
public class AcademyBeastmaster extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Academy Beastmaster";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		
		if(school == School.NATURE) {
			return level;
		} else if(school == School.FIRE) {
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
		return "Beastmaster, NATURE, null";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Academy Core";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "30";
	}
	
	public boolean isAcademy() {
		return true;
	}

	@Override
	public String getState() {
		return "Academy/Official";
	}	

}
