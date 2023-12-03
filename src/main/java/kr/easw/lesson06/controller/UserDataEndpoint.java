package kr.easw.lesson06.controller;

import kr.easw.lesson06.model.dto.RemoveUserDto;
import kr.easw.lesson06.model.dto.UserDataEntity;
import kr.easw.lesson06.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserDataEndpoint {
    private final UserDataService userDataService;

    @GetMapping("/list")
    public ResponseEntity<List<String>> listUsers() {
        List<String> userIds = userDataService.getAllUsers().stream()
                .map(UserDataEntity::getUserId)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userIds);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUser(@RequestBody RemoveUserDto removeUserDto) {
        return userDataService.removeUser(removeUserDto.getUserId())
                ? ResponseEntity.ok("User removed successfully")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove user");
    }
}
