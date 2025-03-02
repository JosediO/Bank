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
import org.springframework.web.bind.annotation.RestController;

import demo.domain.entity.UserEntity;
import demo.domain.exception.InvalidAccessKeyException;
import demo.domain.exception.InvalidBalanceException;
import demo.domain.exception.InvalidNameException;
import demo.domain.exception.NotActiveException;
import demo.domain.exception.NotFoundException;
import demo.domain.exception.NullException;
import demo.domain.service.UserService;
import demo.domain.web.dto.request.DepositRequest;
import demo.domain.web.dto.request.TransferRequest;
import demo.domain.web.dto.request.UpdateRequest;
import demo.domain.web.dto.request.WithdrawalRequest;
import demo.domain.web.dto.response.UserDto;

@RestController
@RequestMapping("/users")
public class BankController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) throws NotFoundException, NullException{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) throws InvalidBalanceException, InvalidNameException, NotActiveException{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userEntity));
	}
	
	@PostMapping("/{id}/withdraw")
		public ResponseEntity<UserEntity> withdrawById(@PathVariable Integer id, @RequestBody WithdrawalRequest withdrawalRequest) throws NotFoundException, InvalidBalanceException, InvalidAccessKeyException, NullException{
		return ResponseEntity.ok(userService.withdrawById(id,withdrawalRequest.getAccessKey(),withdrawalRequest.getValue()));
		}
	
	@PutMapping("/{id}/deposit")
		public ResponseEntity<UserEntity> depositById(@PathVariable Integer id, @RequestBody  DepositRequest depositRequest) throws InvalidBalanceException, NotFoundException, InvalidAccessKeyException, NullException{
		return ResponseEntity.ok(userService.depositById(id,depositRequest.getValue(), depositRequest.getAccessKey()));
	}
		
	@PatchMapping("/{id}/active/{active}")
	public ResponseEntity<UserEntity> changeActivity(@PathVariable Integer id, Boolean active) throws NotFoundException, NotActiveException, NullException {
		return ResponseEntity.ok(userService.changeActivityUser(id, active));
	}
	
	@PutMapping("/{id}/transfer")
	public ResponseEntity<UserEntity> transferToId(@PathVariable Integer id, @RequestBody TransferRequest transferRequest) throws NotFoundException, InvalidAccessKeyException, InvalidBalanceException, NullException{
		return ResponseEntity.ok(userService.transferToId(id,transferRequest.getAccessKey(),transferRequest.getReceptorId(),transferRequest.getValue()));
	}
	
	@PostMapping("/{id}/update")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UpdateRequest updateRequest) throws InvalidNameException, NotFoundException, NotActiveException, InvalidAccessKeyException, NullException{
		return ResponseEntity.ok(userService.updateUser(id, updateRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserEntity> deletUser(@PathVariable Integer id) throws NotFoundException, NullException{
		return ResponseEntity.ok(userService.deletUser(id)); 
		
	}
}
