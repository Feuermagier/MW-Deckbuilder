package application.translation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.builder.Action;
import application.builder.AttackType;
import application.builder.School;
import application.builder.Type;
import application.mages.Mage;
import firemage.util.io.FileReadingUtils;

public class MWLocale {

	private static final Logger log = LogManager.getLogger(MWLocale.class);

	private Locale locale = null;

	// Card Components
	private HashMap<String, String> cardNames = new HashMap<String, String>();
	private ResourceBundle roles;
	private ResourceBundle subtypes;
	private ResourceBundle traits;
	private ResourceBundle schools;
	private ResourceBundle types;
	private ResourceBundle targets;
	private ResourceBundle mages;
	private ResourceBundle modeInfo;
	private ResourceBundle sets;
	private ResourceBundle actions;
	private ResourceBundle slots;
	private ResourceBundle attackTypes;
	private ResourceBundle attackEffects;
	private ResourceBundle attackTraits;
	private ResourceBundle damageTypes;
	private ResourceBundle sectionNames;

	// GUI Components
	private ResourceBundle uiBundle;
	private ResourceBundle messages;

	public MWLocale(String language) {
		locale = new Locale(language);
		System.out.println(locale.getLanguage());
		init();
	}

	private void init() {
		readNameFiles();
		roles = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwroles.mwlf");
		subtypes = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwsubtypes.mwlf");
		traits = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwtraits.mwlf");
		schools = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwschools.mwlf");
		types = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwtypes.mwlf");
		targets = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwtargets.mwlf");
		mages = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwmages.mwlf");
		sets = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwsets.mwlf");
		actions = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwactions.mwlf");
		slots = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwslots.mwlf");
		attackTypes = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwattacktypes.mwlf");
		attackEffects = createResourceBundle(
				 TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwattackeffects.mwlf");
		attackTraits = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwattacktraits.mwlf");
		damageTypes = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwdamagetypes.mwlf");
		sectionNames = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwsections.mwlf");

		uiBundle = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/ui.properties");
		messages = createResourceBundle( TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwmessages.mwlf");
		
		modeInfo = createResourceBundle(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/mwmodiinfo.mwlf");
	}

	private ResourceBundle createResourceBundle(String filename) {
		try {
			InputStream in = new FileInputStream(filename);
			return new PropertyResourceBundle(in);
		} catch (Exception ex) {
			log.error("Cannot find ore read property file " + filename, ex);
		}
		return null;
	}

	private void readNameFiles() {
		ArrayList<String> nameFiles = new ArrayList<String>();
		try {
			nameFiles = FileReadingUtils.readCSVFile(
					new File(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage(), "cardNameFiles.mwlf"), ";");

		} catch (IOException ex) {
			log.warn("Cannot find ore read file " + TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage() + "/"
					+ "cardNameFiles.mwlf");
		}
		for (String file : nameFiles) {
			log.debug("Reading " + file);
			cardNames.putAll(readFile(new File(TranslaterNew.LOCAL_PATH + "/" + locale.getLanguage(), file)));
		}
	}

	private HashMap<String, String> readFile(File file) {
		HashMap<String, String> map = new HashMap<>();

		try {
			map.putAll(FileReadingUtils.readCSVFileIntoMap(file, ";"));
		} catch (IOException ex) {
			log.warn("Cannot find ore read file " + file.getAbsolutePath(), ex);
		}

		return map;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public String getDisplayLanguage(Locale loc) {
		return locale.getDisplayLanguage(loc);
	}

	public ResourceBundle getUILabels() {
		return uiBundle;
	}

	public String getMessage(String key, Object... arguments) {

		return MessageFormat.format(messages.getString(key), arguments);
	}

	public String translateSubtype(String key) {
		return subtypes.getString(key);
	}

	public String translateType(Type key) {
		return types.getString(key.toString());
	}

	public String translateCardName(String englishName) {
		if (this.locale.getLanguage().equals("en"))
			return englishName;
		String name = cardNames.get(englishName.trim());
		if (name == null) {
			return englishName + " ?Translation?";
		}
		return name;
	}

	public String translateAttackType(AttackType key) {
		return attackTypes.getString(key.toString());
	}

	public String translateDamageType(String key) {
		return damageTypes.getString(key);
	}

	public String translateAttackTrait(String key) {
		return attackTraits.getString(key);
	}

	public String translateMage(Mage mage) {
		return mages.getString(mage.getName());
	}

	public String translateTrait(String key) {
		return traits.getString(key);
	}

	public String translateRole(String key) {
		return roles.getString(key);
	}

	public String translateTarget(String key) {
		return targets.getString(key);
	}

	public String getModeInfo(String modeName) {
		return modeInfo.getString(modeName);
	}

	public String translateSet(String englishSetName) {
		return sets.getString(englishSetName);
	}

	public String translateSchool(School school) {
		return schools.getString(school.toString());
	}

	public String translateAction(Action action) {
		return actions.getString(action.toString());
	}

	public String translateAttackEffect(String key) {
		return attackEffects.getString(key);
	}

	public String translateSlot(String key) {
		return slots.getString(key);
	}

	public String translateSectionName(String key) {
		return sectionNames.getString(key);
	}

}
