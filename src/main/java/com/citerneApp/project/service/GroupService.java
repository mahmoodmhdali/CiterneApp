package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.Group;
import java.util.List;

public interface GroupService {

    List<Group> getGroups();
    
    ResponseBodyEntity getGroup(Long id);
    
    ResponseBodyEntity getGroup(String name); 
    
    Group toGroup(Long id);
    
    Group toGroupForAdd(Long id);
    
    Group isUnique(String name);
    
    ResponseBodyEntity addGroup(Group group);
    
    ResponseBodyEntity updateGroup(Group group);
    
    ResponseBodyEntity deleteGroup(Long id);
    
    ResponseBodyEntity deleteSelection(List<Long> id);
}
