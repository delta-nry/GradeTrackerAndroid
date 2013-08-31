package com.gradetracker.gradetracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nathan on 2013-08-26.
 */
public class ExpandableListGroup {
    public String name;
    public final List<String> children = new ArrayList<String>();

    public ExpandableListGroup(String string) {
        name = string;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }
}
