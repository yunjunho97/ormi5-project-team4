  package com.example.ormi5projectteam4.controller.rest_controller;

  import com.example.ormi5projectteam4.domain.constant.StatusCode;
  import com.example.ormi5projectteam4.domain.dto.MyPageDTO;
  import com.example.ormi5projectteam4.domain.dto.PostDTO;
  import com.example.ormi5projectteam4.domain.dto.UserDto;
  import com.example.ormi5projectteam4.service.AuthenticationService;
  import com.example.ormi5projectteam4.service.MyPageService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.*;

  import java.util.List;
  import java.util.Optional;

  @RestController
//  @RequestMapping("/mypage")
  public class MyPageController {
    private final MyPageService myPageService;
    private final AuthenticationService authenticationService;

    @Autowired
    public MyPageController(MyPageService myPageService, AuthenticationService authenticationService) {
      this.myPageService = myPageService;
      this.authenticationService = authenticationService;
    }

    @GetMapping("/this/info")
    public ResponseEntity<UserDto> getUserInfo(Model model) {
      UserDto userDto =
              authenticationService
                      .getUserDto()
                      .orElseThrow(() -> new RuntimeException("No authenticated user found"));
//      Optional<MyPageDTO> userInfo =
//      MyPageDTO userInfo = myPageService.getUserInfo(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
//      userInfo.ifPresent(info -> model.addAttribute("userInfo", info));
//      return "mypage";
      return new ResponseEntity<>(userDto, HttpStatus.valueOf(StatusCode.SUCCESS));
    }

    @PutMapping("/this")
    public ResponseEntity<Void> updateUserInfo(@RequestBody MyPageDTO myPageDTO) {
      try {
        UserDto userDto =
                authenticationService
                        .getUserDto()
                        .orElseThrow(() -> new RuntimeException("No authenticated user found"));
        Long userId = userDto.getId();
        myPageService.updateUserInfo(userId, myPageDTO);
        return ResponseEntity.noContent().build(); // 성공적으로 업데이트됨
      } catch (Exception e) {
        return ResponseEntity.notFound().build(); // 실패 또는 리소스 없음
      }
    }

//    @DeleteMapping("/this")
//    public ResponseEntity<Void> deleteUser() {
//      try {
//        UserDto userDto =
//                authenticationService
//                        .getUserDto()
//                        .orElseThrow(() -> new RuntimeException("No authenticated user found"));
//        Long userId = userDto.getId();
//        myPageService.deleteUser(userId);
//        return ResponseEntity.noContent().build(); // 성공적으로 삭제됨
//      } catch (Exception e) {
//        return ResponseEntity.notFound().build(); // 실패 또는 리소스 없음
//      }
//    }

    @GetMapping("/this/post")
    public ResponseEntity<List<PostDTO>> getPostsByUser() {
      UserDto userDto = authenticationService.getUserDto()
              .orElseThrow(() -> new RuntimeException("No authenticated user found"));
      Long userId = userDto.getId();
      List<PostDTO> posts = myPageService.getPostsByUser(userId);
      return ResponseEntity.ok(posts);
    }

//    @GetMapping("/read-post/{postId}")
//    public ResponseEntity<PostDTO> getPostDetail(@PathVariable Long postId) {
//      UserDto userDto = authenticationService.getUserDto()
//              .orElseThrow(() -> new RuntimeException("No authenticated user found"));
//      Long userId = userDto.getId();
//
//      return myPageService.getMyPostDetail(postId, userId)
//              .map(ResponseEntity::ok)
//              .orElseGet(() -> ResponseEntity.notFound().build());
//    }
  }


