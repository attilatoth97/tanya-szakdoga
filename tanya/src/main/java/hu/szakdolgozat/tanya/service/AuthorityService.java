package hu.szakdolgozat.tanya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

	@Autowired
	private GroupService groupService;

	protected boolean isMemberForTheGroup(Long groupId, Long userId) {
		return groupService.isMemberTheGroup(groupId, userId);
	}
}
