package application.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import application.Paths;
import application.builder.School;
import javafx.scene.image.Image;

public final class ImageCreator {

	private ImageCreator() {}

	public static Image createSchoolImage(School school) throws FileNotFoundException {
		File file;
		switch(school) {
		case ARCANE:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "arcane_school.png");
			break;
		case NATURE:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "nature_school.png");
			break;
		case WAR:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "war_school.png");
			break;
		case DARK:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "dark_school.png");
			break;
		case HOLY:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "holy_school.png");
			break;
		case MIND:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "mind_school.png");
			break;
		case FIRE:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "fire_school.png");
			break;
		case EARTH:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "earth_school.png");
			break;
		case AIR:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "air_school.png");
			break;
		case WATER:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "water_school.png");
			break;
		default:
			file = new File(Paths.RESOURCES + "/images/cardTemplates", "unknown.png");
		}
		return new Image(new FileInputStream(file));
	}
	
	public static Image createCardImage() {
		//TODO
		return null;
	}
	
	public static Image createMageImage() {
		//TODO
		return null;
	}
	
	public static Image createMageInfoImage() {
		//TODO
		return null;
	}
}
