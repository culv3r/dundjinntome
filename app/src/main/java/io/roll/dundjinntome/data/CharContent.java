package io.roll.dundjinntome.data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.roll.dundjinntome.start.CharacterFragment;
import io.roll.dundjinntome.viewCharacter.EquipmentFragment;

public class CharContent {
    /**
     * An array of Character(s)
     */
    public static final List<CharObj> CHARS = new ArrayList<CharObj>();

    /**
     * A map of Character items, by ID.
     */
    public static final Map<String, CharObj> CHAR_MAP = new HashMap<String, CharObj>();

    static {
        // Add some sample chars.

            addItem(createCharObj(1, "Bob", "Barbarian"));
            addItem(createCharObj(2, "Reece", "Rogue"));

    }

    private static void addItem(CharObj item) {
        CHARS.add(item);
        CHAR_MAP.put(item.id, item);
    }

    private static CharObj createCharObj(int position, String name, String charClass) {
        return new CharObj(String.valueOf(position), name, charClass);
    }

    /**
     * A CharObj representing a 'saved' character
     */
    public static class CharObj {
        public final String id;
        public final String name;
        public final String charClass;

        public CharObj(String id, String name, String charClass) {
            this.id = id;
            this.name = name;
            this.charClass = charClass;
        }

    }

}
