package demo.domain.web.controller;

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
import demo.domain.exception.InvalidAccessKeyException;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotFoundException;
import demo.domain.service.UserService;
import demo.domain.web.dto.UserDto;
import demo.domain.web.dto.request.WithdrawalRequest;

@RestController
@RequestMapping("/users")
public class BankController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) throws NotFoundException{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) throws InvalidBalanceException{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	
	@PatchMapping("/{id}/withdraw")
		public ResponseEntity<UserEntity> withdrawById(@PathVariable Integer id, @RequestBody WithdrawalRequest withdrawalRequest) throws NotFoundException, InvalidBalanceException, InvalidAccessKeyException{
		System.out.println("User Acess Key: "+ withdrawalRequest.getAccessKey());
		System.out.println("AcessKey on request:" + withdrawalRequest.getValue());
		return ResponseEntity.ok(userService.withdrawById(id,withdrawalRequest.getAccessKey(),withdrawalRequest.getValue()));
		}
		
	@PatchMapping("/{id}/active/{active}")
	public ResponseEntity<UserEntity> changeActivity(@PathVariable Integer id, @PathVariable Boolean active) throws NotFoundException {
		return ResponseEntity.ok(userService.changeActivityUser(id, active));
	}
	
	@PutMapping
	public ResponseEntity<UserEntity> updateUser(@RequestParam Integer id, UserDto userDto) throws InvalidNameException, NotFoundException{
		return ResponseEntity.ok(userService.updateUser(id, userDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletUser(@PathVariable Integer id) throws NotFoundException{
		return userService.deletUser(id);
		
	}
}
