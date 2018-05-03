package io.roll.dundjinntome.data;

import java.util.ArrayList;
import java.util.HashMap;

import io.roll.dundjinntome.data.CharInst;
import io.roll.dundjinntome.viewCharacter.dummy.DummyContent;

public class SkillObj {
    //Linked Hashmap of Skills
    public static ArrayList<Skill> skillList = new ArrayList();

    public static CharInst charInst = CharInst.getInstance();
    
    static {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Acrobatics");
        temp.add("Animal Handling");
        temp.add("Arcana");
        temp.add("Athletics");
        temp.add("Deception");
        temp.add("History");
        temp.add("Insight");
        temp.add("Intimidation");
        temp.add("Investigation");
        temp.add("Medicine");
        temp.add("Nature");
        temp.add("Perception");
        temp.add("Performance");
        temp.add("Persuasion");
        temp.add("Religion");
        temp.add("Sleight of Hand");
        temp.add("Stealth");
        temp.add("Survival");
        int count = 0;
        for(String skillName:temp){
            addSkill(createSkillObj(count,skillName,10));
        }
    }
    
    private static void addSkill(SkillObj.Skill skill) {
        skillList.add(skill);
    }

    private static SkillObj.Skill createSkillObj(int position, String name, int mod) {
        return new SkillObj.Skill(position, name, charInst.calculateModifier(mod));
    }

    public static class Skill {
        public int id;
        public String name;
        public int modifier;

        public Skill(int id, String name, int modifier){
            this.id = id;
            this.name = name;
            this.modifier = modifier;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}
