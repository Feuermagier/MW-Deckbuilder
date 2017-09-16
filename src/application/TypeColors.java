package application;

import javafx.scene.paint.Color;

@Deprecated
public final class TypeColors {

	public static final Color CREATURE = convertColor(222, 184, 135, 1);
	public static final Color EQUIPMENT = convertColor(211, 211, 211, 1);
	public static final Color CONJURATION = convertColor(240, 255, 240, 1);
	public static final Color INCANTATION = convertColor(230, 230, 250, 1);
	public static final Color ENCHANTMENT = convertColor(255, 228, 196, 1);
	public static final Color ATTACK = convertColor(255, 228, 255, 1);
	public static final Color UNKNOWN = convertColor(0, 0, 0, 1);
	
	private TypeColors() {}
	
	private static Color convertColor(int r, int g, int b, int opacity) {
		return new Color(r/255, g/255, b/255, opacity/255);
	}

}
