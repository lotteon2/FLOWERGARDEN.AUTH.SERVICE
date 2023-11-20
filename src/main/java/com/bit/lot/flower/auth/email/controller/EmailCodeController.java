package com.bit.lot.flower.auth.email.controller;

import com.bit.lot.flower.auth.email.dto.VerifyCodeDto;
import com.bit.lot.flower.auth.email.service.EmailCodeService;
import com.sun.istack.NotNull;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@RequiredArgsConstructor
public class EmailCodeController {

  private final EmailCodeService emailCodeService;

  @PostMapping("/emails/{email}")
  public ResponseEntity<String> create(@NotNull @PathVariable String email) {
    emailCodeService.create(email);
    return ResponseEntity.ok("이메일이 성공적으로 보내졌습니다.");
  }

  @PutMapping("/emails/{email}")
  public ResponseEntity<String> verify(@PathVariable String email, @Valid  @RequestBody VerifyCodeDto dto) {
    emailCodeService.verify(email,dto.getCode());
    return ResponseEntity.ok("이메일이 성공적으로 보내졌습니다.");

  }
}
