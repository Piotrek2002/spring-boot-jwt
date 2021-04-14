package com.techgeeknext.controller;

import com.techgeeknext.config.JwtTokenUtil;
import com.techgeeknext.model.JwtRequest;
import com.techgeeknext.model.JwtResponse;
import com.techgeeknext.model.UserDto;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.JwtUserDetailsService;
import com.techgeeknext.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final MailService mailService;

    public JwtAuthenticationController(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @AuthenticationPrincipal UserDetails customUser) {
        if (customUser.getUsername().equals(userDto.getUsername())) {
            return ResponseEntity.ok(userDetailsService.update(userDto));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/updatePassword/{password}", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@PathVariable String password, @AuthenticationPrincipal UserDetails customUser) {
        return ResponseEntity.ok(userDetailsService.updatePassword(password, userRepository.findByUsername(customUser.getUsername())));
    }

    @RequestMapping(value = "/sendEmail/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> sendEmail(@PathVariable String userName, @AuthenticationPrincipal UserDetails customUser) {
        String password = userDetailsService.generatePassword();
        userDetailsService.updatePassword(password, userRepository.findByUsername(customUser.getUsername()));
        mailService.sendSimpleEmail(userRepository.findByUsername(userName).getEmail(), "New password", password);
        return ResponseEntity.ok("E-mail sent!");
    }
}
