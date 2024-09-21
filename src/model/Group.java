package model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String id;
    String name;
    List<User> members;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void addMember(User member) {
        members.add(member);
    }
}
