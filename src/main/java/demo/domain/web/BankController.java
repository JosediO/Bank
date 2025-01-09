package demo.domain.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.domain.entity.UserEntity;
import demo.domain.service.UserService;

@RestController
@RequestMapping("/user")
public class BankController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@DeleteMapping
	public ResponseEntity<UserEntity> deletById(@RequestParam Integer id){
		return ResponseEntity.ok(userService.changeActivityUser(id));
	}
}
