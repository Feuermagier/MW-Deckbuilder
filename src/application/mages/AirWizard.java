/**
 * This class was added at 15.11.2016.
 * 
 */
package application.mages;

import java.util.ArrayList;
import java.util.Collections;

import application.builder.Card;
import application.builder.School;

/**
 * @author Florian
 *
 */
public class AirWizard extends Mage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getName()
	 */
	@Override
	public String getName() {
		return "Wizard (Air)";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getCreditCost(int,
	 * mwdeckbuilder.logic.MWEnums.School, mwdeckbuilder.logic.MWEnums.Type,
	 * mwdeckbuilder.logic.Card)
	 */
	@Override
	public int calcSpellbookPointCost(Card card) {
		if (card.getSchoolRelation() == Card.SCHOOL_RELATION_OR) {
			ArrayList<Integer> costs = new ArrayList<>();
			for (School school : card.getSchools()) {
				if (school == School.ARCANE || school == School.AIR) {
					costs.add(card.getLevels().get(card.getSchools().indexOf(school)));
				} else {
					costs.add(card.getLevels().get(card.getSchools().indexOf(school)) * 2);
				}
			}
			Collections.sort(costs);
			return costs.get(0);
		} else {
			int cost = 0;
			for (School school : card.getSchools()) {
				if (school == School.ARCANE || school == School.AIR) {
					cost += card.getLevels().get(card.getSchools().indexOf(school));
				} else {
					cost += card.getLevels().get(card.getSchools().indexOf(school)) * 2;
				}
			}
			return cost;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getCanUse()
	 */
	@Override
	public String getCanUse() {
		return "Wizard, Arcane, Air, null";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getSet()
	 */
	@Override
	public String getSet() {
		return "Core";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getOctgnID()
	 */
	@Override
	public String getOctgnID() {
		return "4208e1b0-b570-42de-9e62-5ce15b97b91e";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getIDNumber()
	 */
	@Override
	public String getIDNumber() {
		return "10";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mwdeckbuilder.mages.Mage#getState()
	 */
	@Override
	public String getState() {
		return "Arena/Official";
	}

	@Override
	public boolean canUse(Card card) {
		return getCanUse().contains(card.getOnly());
	}

	@Override
	public int getMaxSpelbookPoints() {
		return 120;
	}

}
