package application.data;

import java.util.HashMap;
import java.util.Map.Entry;

import application.builder.Action;
import application.builder.AttackType;
import application.builder.Role;
import application.builder.School;
import application.builder.Type;

public class EnumTranslater {

	HashMap<String, School> enumSchools = new HashMap<String, School>();	
	HashMap<String, Role> enumRoles = new HashMap<String, Role>();
	HashMap<String, Type> enumTypes = new HashMap<String, Type>();
	HashMap<String, Action> enumActions = new HashMap<String, Action>();
	HashMap<AttackType, String> enumAttacks = new HashMap<AttackType, String>();
	
	public EnumTranslater() {
		enumSchools.put("Arcane", School.ARCANE);
		enumSchools.put("Nature", School.NATURE);
		enumSchools.put("War", School.WAR);
		enumSchools.put("Mind", School.MIND);
		enumSchools.put("Dark", School.DARK);
		enumSchools.put("Holy", School.HOLY);
		enumSchools.put("Fire", School.FIRE);
		enumSchools.put("Air", School.AIR);
		enumSchools.put("Earth", School.EARTH);
		enumSchools.put("Water", School.WATER);
		
		enumTypes.put("Conjuration", Type.CONJURATION);
		enumTypes.put("Creature", Type.CREATURE);
		enumTypes.put("Enchantment", Type.ENCHANTMENT);
		enumTypes.put("Incantation", Type.INCANTATION);
		enumTypes.put("Equipment", Type.EQUIPMENT);
		enumTypes.put("Attack", Type.ATTACK);
		
		enumAttacks.put(AttackType.MELEE, "Melee");
		enumAttacks.put(AttackType.RANGE, "Ranged");
		enumAttacks.put(AttackType.DAMAGE_BARRIER, "Damage Barrier");
		enumAttacks.put(AttackType.PASSAGE_ATTACK, "Passage Attack");
		
		enumRoles.put("offensiv", Role.OFFENSIVE);
		enumRoles.put("defensiv", Role.DEFENSIVE);
		enumRoles.put("schaden", Role.DAMAGE);
		enumRoles.put("zustand", Role.CONDITION);
		enumRoles.put("heilen", Role.HEAL);
		enumRoles.put("kosten", Role.COST);
		enumRoles.put("leben", Role.LIFE);
		enumRoles.put("mana", Role.MANA);
		enumRoles.put("domination", Role.DOMINATION);
		enumRoles.put("dot", Role.DAMAGE_OVER_TIME);
		enumRoles.put("kontrolle", Role.CONTROL);
		
		enumActions.put("Full", Action.FULL);
		enumActions.put("Quick", Action.QUICK);
	}
	
	public String getActionNameEnglish(Action key) {
		String value = "Error2";
	    if(!enumActions.containsValue(key)) {return "Error1";}
		for(Entry<String, Action> entry : enumActions.entrySet()) {
			if(entry.getValue().equals(key)) { 
			    value = (String)entry.getKey();
				break;
		    }
		}
		return value;		
	} 
	
	public School getSchoolEnum(String key) {
		if(!enumSchools.containsKey(key)) {return null;}
		School value = enumSchools.get(key);
		return value;
	}
	
	public Type getTypeEnum(String key) {
		if(!enumTypes.containsKey(key)) {return null;}
		Type value = enumTypes.get(key);
		return value;
	}
	
	public Role getRoleEnum(String key) {
		if(!enumRoles.containsKey(key)) {return null;}
		Role value = enumRoles.get(key);
		return value;
	}
	
	public Action getActionEnum(String key) {
		if(!enumActions.containsKey(key)) {return null;}
		Action value = enumActions.get(key);
		return value;
	}	

	public String getAttackTypeNameE(AttackType key) {
		if(!enumAttacks.containsKey(key)) return "error";
		String value = enumAttacks.get(key);
		return value;
	}
	
	public AttackType getAttackType(String key) {
		AttackType value = null;
	    if(!enumAttacks.containsValue(key)) {return value;}
		for(Entry<AttackType, String> entry : enumAttacks.entrySet()) {
			if(entry.getValue().equals(key)) { 
			    value = (AttackType)entry.getKey();
				break;
		    }
		}
		return value;
	}


}
