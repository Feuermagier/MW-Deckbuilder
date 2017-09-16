package application.translation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import application.preferences.UserPreferences;
import firemage.util.io.FileReadingUtils;

public class TranslaterNew {

	private static final Logger log = LogManager.getLogger(TranslaterNew.class);

	public static final String LOCAL_PATH = "./resources/local";

	private static TranslaterNew instance = null;

	private static HashMap<String, String> translateMages = new HashMap<String, String>();
	private final ArrayList<MWLocale> availableLocales = new ArrayList<>();
	private MWLocale currentLocale = null;

	private TranslaterNew() {
		currentLocale = new MWLocale(UserPreferences.getInstance().getPreferredLanguage());
		init();
	}
	
	public static TranslaterNew getInstance() {
		if(instance == null) {
			instance = new TranslaterNew();
		}
		
		return instance;
	}

	private void init() {

		translateMages = readFile(new File(LOCAL_PATH, "translateMages.mwrd"));
		
		ArrayList<String> res = new ArrayList<>();
		try {

			res = FileReadingUtils.readCSVFile(new File(LOCAL_PATH, "languages.mwrd"), ";");

		} catch (IOException ex) {
			log.warn("Error when reading file / file not found " + LOCAL_PATH + "/languages.mwrd", ex);
		}
		
		for(String localName : res) {
			availableLocales.add(new MWLocale(localName));
		}
		
	}
	
	private HashMap<String, String> readFile(File file) {
		HashMap<String, String> map = new HashMap<>();
		
		try {
			map = new HashMap<>(FileReadingUtils.readCSVFileIntoMap(file, ";"));
		} catch(IOException ex) {
			log.warn("Cannot find ore read file " + file.getAbsolutePath());
		}
		
		return map;
	}
	
	public String getMageClassName(String englishMageName) {
		return translateMages.get(englishMageName);
	}
	
	public MWLocale getCurrentLocale() {
		return currentLocale;
	}

}
