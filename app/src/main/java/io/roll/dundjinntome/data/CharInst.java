package io.roll.dundjinntome.data;

//This singleton is meant to be used as a conduit for whatever class is loading the character information into it, and whatever class is reading that information for display.
//The part of it responsible for transmitting the information to the class reading it is very simple, just a bunch of getters that can grab the information for display.
//The setter part is a bit more complex. Basically, to initialize the character instance and make it ready to be read by ViewCharacterActivity.java or any other class, you need to pass in the following:
//      The HP, max HP, Armor Class, XP, and level of the character as integers
//      An array of 6 integers representing the ability scores. It is important that these are in the order Str, Con, Dex, Int, Wis, Cha
//      And finally, optionally, an arraylist where each entry is a string of a skill that the character is proficient in. I made this optional because I wasn't sure if proficiency is something you were concerned about for the sake of this project.
//The setter methods follow the rules found in the PHB to calculate the ability modifiers for the skills based on the ability scores.
//Please refer to the comments found throughout the code to get further insight into the usage of this class.

//I noticed that the ViewCharacterActivity.java doesn't have anything for character name or class. I wasn't sure if this was something we wanted included in CharInst.java as a result, but I added in some basic methods relevant to that. If we want to expand on that and use the class to calculate AC and HP as opposed to having them passed in as parameters, let me know and I can work on that as well.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharInst {

    //singleton instance, initialized when first used
    //I initialize the singleton instance when getInstance() is first called, because I learned this is a better practice from some of my software engineering courses.
    private static CharInst ourInstance;

    private String name;
    private String charClass;

    //Numerical values from character sheet
    private int hp;
    private int maxHP;
    private int AC;
    private int xp;
    private int lvl;

    //skills hashmap
    //use attribute as string for key such as "Strength", returns integer that gives score
    private Map<Integer, Integer> expTbl = initExpTbl();
    private Map<String, Integer> attributes = initAbilityScores();
    private LinkedHashMap<String, Integer> skills = setSkills();

    //if instance isn't already initialized, do so when getInstance is first called
    public static CharInst getInstance(){
        if(ourInstance == null)
            ourInstance = new CharInst();
        return ourInstance;
    }

    //TODO constructor
    //I wasn't 100% sure what to do in the constructor, because the data stored in the fields of this class are meant to be fluid and change as each character is loaded in. I opted to just initialize all of the basic numerical values to 0. If the class is used properly, these values shouldn't be in place by the time they're important anyways.
    private CharInst(){
    }

    //getters

    public String getName(){
        return name;
    }

    public String getCharClass(){
        return charClass;
    }
    //these numerical ones are more basic, but take a look at the comment block about the ability scores and skills
    public int getHP(){
        return hp;
    }

    public int getMaxHP(){
        return maxHP;
    }

    public int getAC(){
        return AC;
    }

    public int getXP(){
        return xp;
    }

    public int getLVL(){
        return lvl;
    }


    //I wasn't sure what kind of usage you had in mind for retrieving the ability scores and skill modifiers so I made two versions of each.
    //One of the methods for each just returns the hashmap itself for the other class to iterate through to read
    //The other one takes an ability/skill name as a parameter and returns specifically the score/modifier associated with it.
    //Depending on your plans for usage, you can go ahead and delete one of them if you want.
    
    //return specific ability score
    public int getAbilityScore(String abilName){
        return attributes.get(abilName);
    }
    //return all ability scores as hashmap
    public Map<String, Integer> getAbilityScores(){
        return attributes;
    }
    //return specific skill modifier
    public int getSkillModifier(String skillName){
        return skills.get(skillName);
    }
    //return all skill modifiers as hashmap
    public LinkedHashMap<String, Integer> getSkills(){
        return skills;
    }
    public Map<Integer,Integer> getExpTbl() { return expTbl; }

    //setters
    //In order to load a character into CharInst, the class accessing it should use initalizeCharacter. There are two versions of the method in case you care about the proficienies or not.
    //This first version does everything that is required, and the second version calls this version to initialize everything else, and then adds the proficiencies afterwards.
    //These utilize the setters which are all set to public in case you want to modify the values manually at some point after initializing it.
    //If that's not a feature that we want, then we can change the setters to private methods, and leave only the two instances of initializeCharacter() to be public.

    //This is the version of initializeCharacter that doesn't account for proficiencies.
    public void initializeCharacter(String newName, String newClass, int newHP, int newMaxHP, int newAC, int newXP, int newLVL, int[] abilScores){
        setName(newName);
        setClass(newClass);
        setNumericalValues(newHP, newMaxHP, newAC, newXP, newLVL);
        setAbilityScores(abilScores);
        skills = setSkills();
    }
    //This version of initializeCharacter has the extra parameter of proficiencies, which is used to add proficiencies to the skill modifier.
    //It first just calls the previous version of initializeCharacter, passing the parameters that are relevant to that, and then uses setProficiencies to add those
    public void initializeCharacter(String newName, String newClass, int newHP, int newMaxHP, int newAC, int newXP, int newLVL, int[] abilScores, ArrayList<String> proficiencies){
        initializeCharacter(newName, newClass, newHP, newMaxHP, newAC, newXP, newLVL, abilScores);
        addProficiencies(proficiencies);
    }

    //set name and class
    public void setName(String newVal){
        name = newVal;
    }
    public void setClass(String newVal){
        charClass = newVal;
    }

    //set numerical values in one method (make initialization of values easier)
    public void setNumericalValues(int newHP, int newMaxHP, int newAC, int newXP, int newLVL){
        setHP(newHP);
        setMaxHP(newMaxHP);
        setAC(newAC);
        setXP(newXP);
        setLVL(newLVL);
    }

    //set individual numerical values (useful in case of adjusting values after initialization)
    //Again, if we don't need the ability to adjust these stats after initializing the character, we can set all of these to private, or even delete the methods and just implement their functionality into the other methods that are calling them (seeing as they're all only one line each)
    public void setHP(int newVal){
        hp = newVal;
    }

    public void setMaxHP(int newVal){
        maxHP = newVal;
    }

    public void setAC(int newVal){
        AC = newVal;
    }

    public void setXP(int newVal){
        xp = newVal;
    }

    public void setLVL(int newVal){
        lvl = newVal;
    }

    //set abilityScores hashmap (must be done when character is loaded into CharInst)
    public void setAbilityScores(int[] attributeScores){
        attributes.put("Strength", attributeScores[0]);
        attributes.put("Constitution", attributeScores[1]);
        attributes.put("Dexterity", attributeScores[2]);
        attributes.put("Intelligence", attributeScores[3]);
        attributes.put("Wisdom", attributeScores[4]);
        attributes.put("Charisma", attributeScores[5]);
    }

    //Initializes the attributes all to zero.
    //These initial values should be overwritten when the first character is loaded into CharInst
    //This is private because we don't want this to be called again at any time other than the initialization of the singleton. After that, each time a character is loaded in, the values will update.
    //It can be argued that these initalized values are pointless. I just figured that it would be better to avoid any potential NullPointerExceptions, and as such, opted to initialize everything with dummy values. When bug testing, we should test for those dummy values, and if we find that they're still here, then there's some problem with initializeCharacter() or the other setter methods.
    private HashMap<String, Integer> initAbilityScores(){
        HashMap<String, Integer> temp = new HashMap();
        temp.put("Strength", 0);
        temp.put("Constitution", 0);
        temp.put("Dexterity", 0);
        temp.put("Intelligence", 0);
        temp.put("Wisdom", 0);
        temp.put("Charisma", 0);
        return temp;
    }

    private HashMap<Integer,Integer> initExpTbl(){
        HashMap<Integer,Integer> tmp = new HashMap<Integer,Integer>();
        tmp.put(1,300);
        tmp.put(2,900);
        tmp.put(3,2700);
        tmp.put(4,6500);
        tmp.put(5,14000);
        tmp.put(6,23000);
        tmp.put(7,34000);
        tmp.put(8,48000);
        tmp.put(9,64000);
        tmp.put(10,85000);
        tmp.put(11,100000);
        tmp.put(12,120000);
        tmp.put(13,140000);
        tmp.put(14,165000);
        tmp.put(15,195000);
        tmp.put(16,225000);
        tmp.put(17,265000);
        tmp.put(18,305000);
        tmp.put(19,355000);
        tmp.put(20,500000);
        return tmp;
    }

    //setSkills uses the ability scores to calculate the modifier for each skill. I'm basing this on the D&D 5e character sheet. If we want to include functionality for the other versions, then just let me know and I can add those in as well.
    public LinkedHashMap<String, Integer> setSkills(){
        LinkedHashMap<String, Integer> temp = new LinkedHashMap();
        temp.put("Acrobatics", calculateModifier(attributes.get("Dexterity")));
        temp.put("Animal Handling", calculateModifier(attributes.get("Wisdom")));
        temp.put("Arcana", calculateModifier(attributes.get("Intelligence")));
        temp.put("Athletics", calculateModifier(attributes.get("Strength")));
        temp.put("Deception", calculateModifier(attributes.get("Charisma")));
        temp.put("History", calculateModifier(attributes.get("Intelligence")));
        temp.put("Insight", calculateModifier(attributes.get("Wisdom")));
        temp.put("Intimidation", calculateModifier(attributes.get("Charisma")));
        temp.put("Investigation", calculateModifier(attributes.get("Intelligence")));
        temp.put("Medicine", calculateModifier(attributes.get("Wisdom")));
        temp.put("Nature", calculateModifier(attributes.get("Intelligence")));
        temp.put("Perception", calculateModifier(attributes.get("Wisdom")));
        temp.put("Performance", calculateModifier(attributes.get("Charisma")));
        temp.put("Persuasion", calculateModifier(attributes.get("Charisma")));
        temp.put("Religion", calculateModifier(attributes.get("Intelligence")));
        temp.put("Sleight of Hand", calculateModifier(attributes.get("Dexterity")));
        temp.put("Stealth", calculateModifier(attributes.get("Dexterity")));
        temp.put("Survival", calculateModifier(attributes.get("Wisdom")));
        return temp;
    }

    //This method is utilized by setSkills to calculate the skill modifiers based on ability score. It takes in an integer representing an ability score and returns what modifier such a skill would have.
    //According to the PHB, the modifier can be calculated sans table by subtracting 10 from the ability score and dividing it by 2 (ignoring the remainder). Because integer division in Java ignores the remainder, this functionality should be represented by the statement in the method.
    public int calculateModifier(int abilScore){
        return (abilScore - 10) / 2;
    }

    //This method calculates the proficiency bonus for each skill. Taking an arraylist of the skills the character is proficient in as a parameter, it iterates through the list and updates the modifier to add the appropriate bonus based on the level.
    //When I was reviewing the tables in the PHB regarding proficiency bonus, it appeared that it's initially a +2 modifier, and increases by 1 at each N + 1 level, where N is a multiple of four.
    //In order to calculate the bonus, I take the level, subtract 1 and divide by 4. This would be 0 for levels 1-4, 1 for levels 5-8, 2 for levels 9-12, etc. I then just add 2.
    public void addProficiencies(ArrayList<String> proficiencies){
        for(String proficiency : proficiencies){
            skills.put(proficiency, skills.get(proficiency) + ((lvl - 1) / 4) + 2);
        }
    }
}
