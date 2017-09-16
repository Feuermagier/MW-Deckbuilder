/**
 * This class was added at 15.11.2016.
 * 
 */
package application.mages;

import mwdeckbuilder.logic.Card;
import mwdeckbuilder.logic.Type;

/**
 * @author Florian
 *
 */
public class AcademyWizard extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Academy Wizard";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		if(school == School.ARCANE) {
			return level;
			
		} else if(school == School.AIR) {
			return level;
			
		} else {
			return level*2;
		}
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCanUse()
	 */
	@Override
	public String getCanUse() {
		return "Wizard, Arcane, Air, null";
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
		return "";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "31";
	}
	
	public boolean isAcademy() {
		return true;
	}
	
	@Override
	public String getState() {
		return "Academy/Official";
	}	

}
