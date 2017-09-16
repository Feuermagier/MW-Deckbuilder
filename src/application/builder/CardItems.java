package application.builder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;

import application.Paths;

import org.apache.logging.log4j.LogManager;

import firemage.util.io.FileReadingUtils;

public final class CardItems {

	private static CardItems instance = null;

	private static final Logger log = LogManager.getLogger("CardItems");

	private ArrayList<String> subtypes = new ArrayList<String>();
	private ArrayList<String> traits = new ArrayList<String>();
	private ArrayList<String> slots = new ArrayList<String>();
	private ArrayList<String> attackEffects = new ArrayList<String>();
	private ArrayList<String> attackTraits = new ArrayList<String>();
	private ArrayList<String> damageTypes = new ArrayList<String>();

	private CardItems() {
		subtypes = readFile(new File(Paths.RESOURCES, "subtypes.mwrd"));
		traits = readFile(new File(Paths.RESOURCES, "traits.mwrd"));
		slots = readFile(new File(Paths.RESOURCES, "slots.mwrd"));
		attackEffects = readFile(new File(Paths.RESOURCES, "attackEffects.mwrd"));
		attackTraits = readFile(new File(Paths.RESOURCES, "attackTraits.mwrd"));
		damageTypes = readFile(new File(Paths.RESOURCES, "damageTypes.mwrd"));
	}

	private ArrayList<String> readFile(File file) {
		ArrayList<String> res = new ArrayList<>();
		
		try {
			
			log.debug("Reading file: " + file.getAbsolutePath());
			
			res = FileReadingUtils.readCSVFile(file, ";");
			
		} catch (IOException ex) {
			log.warn("Cannot find or read file " + file.getAbsolutePath(), ex);
		}
		
		return res;
	}

	public static CardItems getInstance() {
		if (instance == null)
			instance = new CardItems();
		return instance;
	}

	/**
	 * @return the subtypes
	 */
	public ArrayList<String> getSubtypes() {
		return subtypes;
	}

	/**
	 * @return the traits
	 */
	public ArrayList<String> getTraits() {
		return traits;
	}

	/**
	 * @return the slots
	 */
	public ArrayList<String> getSlots() {
		return slots;
	}

	/**
	 * @return the attackEffects
	 */
	public ArrayList<String> getAttackEffects() {
		return attackEffects;
	}

	/**
	 * @return the attackTraits
	 */
	public ArrayList<String> getAttackTraits() {
		return attackTraits;
	}

	/**
	 * @return the damageTypes
	 */
	public ArrayList<String> getDamageTypes() {
		return damageTypes;
	}
}
