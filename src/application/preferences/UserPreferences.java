
/*
 * Singleton
 */

package application.preferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Type;
import application.data.EnumTranslater;
import firemage.util.io.FileReadingUtils;
import firemage.util.io.StreamUtils;

public final class UserPreferences {

	private static UserPreferences instance = null;

	private static Logger log = LogManager.getLogger(UserPreferences.class);

	private static final String PREFERENCE_PATH = "./config/preferences";

	private static final String DEFAULT_COUNTRY = "en";
	private static final String DEFAULT_LANGUAGE = "en";

	private static final EnumTranslater enumTranslater = new EnumTranslater();

	private ArrayList<Type> cardOrder = new ArrayList<Type>();
	private HashMap<String, Integer> setCount;
	private boolean checkSets = false;
	private boolean checkPromos = false;
	private boolean usePromos = false;
	private boolean onlyEligible = false;
	private String country;
	private String preferredLanguage;

	private UserPreferences() {

		this.setCount = readSetCount();
		this.country = readCountry();
		this.preferredLanguage = readPreferredLanguage();

		// default values
		cardOrder.add(Type.EQUIPMENT);
		cardOrder.add(Type.CREATURE);
		cardOrder.add(Type.CONJURATION);
		cardOrder.add(Type.ENCHANTMENT);
		cardOrder.add(Type.INCANTATION);
		cardOrder.add(Type.ATTACK);

		this.cardOrder = readCardOrder();

		readFlags();
	}

	public static UserPreferences getInstance() {
		if (instance == null)
			instance = new UserPreferences();

		return instance;
	}

	/**
	 * @return the cardOrder
	 */
	public ArrayList<Type> getCardOrder() {
		return new ArrayList<Type>(cardOrder);
	}

	/**
	 * @return the setCount
	 */
	public HashMap<String, Integer> getSetCount() {
		return setCount;
	}

	/**
	 * @return the onlyEligible
	 */
	public boolean isCheckSets() {
		return checkSets;
	}

	private HashMap<String, Integer> readSetCount() {

		HashMap<String, Integer> result = new HashMap<>();

		try {

			HashMap<String, String> readValues = new HashMap<String, String>(
					FileReadingUtils.readLineAndEqualSeparatedFileIntoMap(new File(PREFERENCE_PATH, "setCounts.mwcf")));

			for (Entry<String, String> entry : readValues.entrySet()) {
				result.put(entry.getKey(), Integer.parseInt(entry.getValue()));
			}

		} catch (NumberFormatException | IOException ex) {
			log.warn("File" + PREFERENCE_PATH + "/setCounts.mwcf was not found or is not valid: " + ex.getMessage());
		}

		return result;
	}

	private String readCountry() {

		String result = DEFAULT_COUNTRY;
		try {

			result = FileReadingUtils.readLineSeparatedFile(new File(PREFERENCE_PATH, "country.mwcf")).get(0);

		} catch (NumberFormatException | IOException ex) {
			log.warn("File" + PREFERENCE_PATH + "/country.mwcf was not found or is not valid: " + ex.getMessage());
		}

		return result;

	}

	private String readPreferredLanguage() {

		String result = DEFAULT_LANGUAGE;
		try {

			result = FileReadingUtils.readLineSeparatedFile(new File(PREFERENCE_PATH, "language.mwcf")).get(0);

		} catch (NumberFormatException | IOException ex) {
			log.warn("File" + PREFERENCE_PATH + "/language.mwcf was not found or is not valid: " + ex.getMessage());
		}

		return result;

	}

	private ArrayList<Type> readCardOrder() {
		ArrayList<Type> result = new ArrayList<Type>();

		try {

			ArrayList<String> readValues = new ArrayList<String>(
					FileReadingUtils.readLineSeparatedFile(new File(PREFERENCE_PATH, "cardOrder.mwcf")));

			for (String s : readValues) {
				result.add(enumTranslater.getTypeEnum(s));
				System.out.println(s);
			}

		} catch (NumberFormatException | IOException ex) {
			log.warn("File" + PREFERENCE_PATH + "/cardOrder.mwcf was not found or is not valid: " + ex.getMessage());
		}

		return result;
	}

	private void readFlags() {
		HashMap<String, String> readValues = new HashMap<>();

		try {

			readValues = new HashMap<String, String>(FileReadingUtils
					.readLineAndEqualSeparatedFileIntoMap(new File(PREFERENCE_PATH, "preferences.mwcf")));

		} catch (IOException ex) {
			log.warn("File" + PREFERENCE_PATH + "/preferences.mwcf was not found or is not valid: " + ex.getMessage());
		}

		checkSets = setFlagByString(readValues.get("checkSets").trim());
		usePromos = setFlagByString(readValues.get("usePromos").trim());
		checkPromos = setFlagByString(readValues.get("checkPromos").trim());
		onlyEligible = setFlagByString(readValues.get("onlyEligible").trim());
	}

	private boolean setFlagByString(String value) {
		if (value == null)
			throw new IllegalArgumentException("value is null");

		if (value.equals("true"))
			return true;
		else
			return false;

	}
	
	private void updateFlags() {
		File file = new File(PREFERENCE_PATH, "preferences.mwcf");
		updateValueInFile(file, "checkSets = " + ((Boolean)checkSets).toString());
		addValueToFile(file, "checkPromos = " + ((Boolean)checkPromos).toString());
		addValueToFile(file, "usePromos = " + ((Boolean)usePromos).toString());
		addValueToFile(file, "onlyEligible = " + ((Boolean) onlyEligible).toString());
	}

	private void addValueToFile(File file, String newValue) {
		FileOutputStream out = null;

		try {

			out = new FileOutputStream(file, true);

		} catch (IOException ex) {
			log.warn("Error when writing to file " + file.getAbsolutePath(), ex);
		} finally {
			StreamUtils.safeClose(out);
		}
	}
	
	private void updateValueInFile(File file, String newValue) {
		FileOutputStream out = null;

		try {

			out = new FileOutputStream(file, false);

		} catch (IOException ex) {
			log.warn("Error when writing to file " + file.getAbsolutePath(), ex);
		} finally {
			StreamUtils.safeClose(out);
		}
	}

	/**
	 * @return the checkPromos
	 */
	public boolean isCheckPromos() {
		return checkPromos;
	}

	/**
	 * @param checkPromos
	 *            the checkPromos to set
	 */
	public void setCheckPromos(boolean checkPromos) {
		this.checkPromos = checkPromos;
		updateFlags();
	}

	/**
	 * @return the usePromos
	 */
	public boolean isUsePromos() {
		return usePromos;
	}

	/**
	 * @param usePromos
	 *            the usePromos to set
	 */
	public void setUsePromos(boolean usePromos) {
		this.usePromos = usePromos;
		updateFlags();
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
		updateValueInFile(new File(PREFERENCE_PATH), country);
	}

	/**
	 * @param checkSets
	 *            the checkSets to set
	 */
	public void setCheckSets(boolean checkSets) {
		this.checkSets = checkSets;
		updateFlags();
	}

	/**
	 * @return the preferredLanguage
	 */
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	/**
	 * @param preferredLanguage
	 *            the preferredLanguage to set
	 */
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
		updateValueInFile(new File(PREFERENCE_PATH), preferredLanguage);
	}

	public static void main(final String[] args) {
		System.setProperty("log4j.configurationFile", "config/log4j2.xml");
		UserPreferences.log = LogManager.getLogger("UserPreferences");
		UserPreferences pref = UserPreferences.getInstance();
		System.out.println("checkSets: " + pref.isCheckSets());
		System.out.println("country: " + pref.getCountry());
		System.out.println("setCount: " + pref.getSetCount());
	}

	/**
	 * @return the onlyEligible
	 */
	public boolean isOnlyEligible() {
		return onlyEligible;
	}

	/**
	 * @param onlyEligible the onlyEligible to set
	 */
	public void setOnlyEligible(boolean onlyEligible) {
		this.onlyEligible = onlyEligible;
	}

}
