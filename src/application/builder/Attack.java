package application.builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Definiert einen Angriff einer Karte.
 * 
 * @author firemage
 *
 */
public class Attack {
	private Action action;
	private String typ;
	private String damageType = "";
	private boolean zoneAttack = false;
	private int dice;
	private ObservableList<D12Effect> d12Effects = FXCollections.observableArrayList();
	private ObservableList<String> traits = FXCollections.observableArrayList();

	public Attack(Action action, String typ, int dice) {
		this.action = action;
		this.typ = typ;
		this.dice = dice;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	public boolean isZoneAttack() {
		return zoneAttack;
	}

	public void setZoneAttack(boolean zoneAttack) {
		this.zoneAttack = zoneAttack;
	}

	public int getDice() {
		return dice;
	}

	public void setDice(int dice) {
		this.dice = dice;
	}

	public ObservableList<D12Effect> getD12Effects() {
		return d12Effects;
	}

	public void setD12Effects(ObservableList<D12Effect> d12Effects) {
		this.d12Effects = d12Effects;
	}

	public ObservableList<String> getTraits() {
		return traits;
	}

	public void setTraits(ObservableList<String> traits) {
		this.traits = traits;
	}

	public class D12Effect {
		private int min;
		private int max;
		private String effect;

		public D12Effect(int min, int max, String effect) {
			this.min = min;
			this.max = max;
			this.effect = effect;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}

		public String getEffect() {
			return effect;
		}

		public void setEffect(String effect) {
			this.effect = effect;
		}
	}
}
