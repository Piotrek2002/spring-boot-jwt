package com.techgeeknext.service;

import com.techgeeknext.model.GroupDao;
import com.techgeeknext.model.MessageDao;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.model.UserDto;
import com.techgeeknext.repository.GroupRepository;
import com.techgeeknext.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	private final GroupRepository groupRepository;

	public JwtUserDetailsService(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		}

	}

	public UserDao save(UserDto user) {
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

		newUser.setActualData(user.getActualData());


		newUser.setDataOfStart(String.valueOf(LocalDateTime.now()));
		newUser.setDataOfUpdate(String.valueOf(LocalDateTime.now()));
		newUser.setDescription(user.getDescription());
		newUser.setEmail(user.getEmail());
		newUser.setMacAddress(user.getMacAddress());
		newUser.setModes(user.getModes());
		newUser.setPosition(user.getPosition());
		newUser.setSecretData1(user.getSecretData1());
		newUser.setSecretData2(user.getSecretData2());
		newUser.setSecretData3(user.getSecretData3());
		newUser.setSecretData4(user.getSecretData4());
		newUser.setUserIP(user.getUserIP());
		GroupDao groupDao=new GroupDao();
		groupDao.setName(user.getGroupName());
		groupDao.setGroupDescription(user.getGroupDescription());
		groupDao.setAdministrator(newUser);
		groupRepository.save(groupDao);
		newUser.setAdministratedGroup(groupDao);
		List<MessageDao> messageSent=new ArrayList<>();
		List<MessageDao> messageReceived=new ArrayList<>();
		newUser.setMessagesSent(messageSent);
		newUser.setMessagesReceived(messageReceived);
		return userDao.save(newUser);
	}
	public UserDao update(UserDto user){
		UserDao newUser=userDao.findByUsername(user.getUsername());
		newUser.setActualData(user.getActualData());
		newUser.setDataOfStart(String.valueOf(LocalDateTime.parse(user.getDataOfStart())));
		newUser.setDataOfUpdate(String.valueOf(LocalDateTime.now()));
		newUser.setDescription(user.getDescription());
		newUser.setEmail(user.getEmail());
		newUser.setMacAddress(user.getMacAddress());
		newUser.setModes(user.getModes());
		newUser.setPosition(user.getPosition());
		newUser.setSecretData1(user.getSecretData1());
		newUser.setSecretData2(user.getSecretData2());
		newUser.setSecretData3(user.getSecretData3());
		newUser.setSecretData4(user.getSecretData4());
		newUser.setUserIP(user.getUserIP());
		return userDao.save(newUser);
	}
	public UserDao updatePassword(String password, UserDao user){
		user.setPassword(bcryptEncoder.encode(password));
		return userDao.save(user);

	}
	public String generatePassword() {
		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
		String totalChars = RandomStringUtils.randomAlphanumeric(2);
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
				.concat(numbers)
				.concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String password = pwdChars.stream()
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}

}