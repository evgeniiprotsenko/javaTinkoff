package edu.hw3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Task5 {

    public String[] parseContacts(String[] contacts, String type) {
        if (contacts == null || contacts.length == 0 || (contacts.length == 1 && contacts[0] == null)) {
            return new String[0];
        }

        TreeMap<String, String> map = new TreeMap<>();

        for (String contact : contacts) {
            if (contact.contains(" ")) {
                String[] split = contact.split(" ");
                map.put(split[1], split[0]);
            } else {
                map.put(contact, "");
            }
        }
        if (type.equals("DESC")) {
            HashMap<String, String> helpMap = new HashMap<>(map);
            map = new TreeMap<>(Comparator.reverseOrder());
            map.putAll(helpMap);
        }

        String[] result = new String[map.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result[index++] = entry.getValue().equals("") ? entry.getKey()
                : entry.getValue().concat(" ").concat(entry.getKey());
        }

        return result;
    }
}
