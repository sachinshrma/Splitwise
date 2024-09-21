package service;

import model.Group;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GroupService {
    Map<String, Group> groups;

    public GroupService() {
        this.groups = new HashMap<>();
    }

    public Group createGroup(String name) {
        Group group = new Group(UUID.randomUUID().toString(), name);
        groups.put(group.getId(), group);
        return group;
    }

    public void addMember(String groupId, User user) {
        groups.get(groupId).addMember(user);
    }
}
