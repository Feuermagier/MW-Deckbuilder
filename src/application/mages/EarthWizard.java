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
public class EarthWizard extends Mage {

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Wizard (Earth)";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int, mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type, mwdeckbuilder.logic.Card)
	 */
	@Override
	public Integer getCreditCost(int level, School school, Type type, Card card) {
		if(school == School.ARCANE) {
			return level;
			
		} else if(school == School.EARTH) {
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
		return "Core";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "2d170aeb-80ef-489e-90cc-ad0d83a08a65";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "10";
	}

	/* (non-Javadoc)
	 * @see mwdeckbuilder.mages.Mage#getState()
	 */
	@Override
	public String getState() {
		return "Arena/Deprecated";
	}

}
