package demo.domain.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.service.UserService;

@RestController
@RequestMapping("/user")
public class BankController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) throws NotFoundException{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	
	@PatchMapping("/{id}/active/{active}")
	public ResponseEntity<UserEntity> changeActivity(@RequestParam Integer id, Boolean active) throws NotFoundException {
		return ResponseEntity.ok(userService.changeActivityUser(id, active));
	}
	
	@PutMapping
	public ResponseEntity<UserEntity> updateUser(@RequestParam Integer id, UserDto userDto) throws InvalidNameException{
		return ResponseEntity.ok(userService.updateUser(id, userDto));
	}
	
	@DeleteMapping
	public ResponseEntity<UserEntity> deletById(@RequestParam Integer id){
		return ResponseEntity.ok(userService.changeActivityUser(id));
	}
}
