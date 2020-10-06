package hu.szakdolgozat.tanya.service;

import hu.szakdolgozat.tanya.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class   AuthorityService {

	@Autowired
	protected GroupService groupService;

	protected boolean isMemberForTheGroup(Long groupId, Long userId) {
		return groupService.isMemberTheGroup(groupId, userId);
	}

	protected boolean isMemberForTheGroup(Group group, Long userId) {
		return groupService.isMemberTheGroup(group, userId);
	}

}
