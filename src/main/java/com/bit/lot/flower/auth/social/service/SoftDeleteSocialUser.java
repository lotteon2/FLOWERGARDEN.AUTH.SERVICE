package com.bit.lot.flower.auth.social.service;

import com.bit.lot.flower.auth.social.exception.SocialAuthException;
import com.bit.lot.flower.auth.social.entity.SocialAuth;
import com.bit.lot.flower.auth.social.repository.SocialAuthJpaRepository;
import com.bit.lot.flower.auth.social.valueobject.AuthId;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SoftDeleteSocialUser<ID extends AuthId> implements
    SocialUserWithdrawalStrategy<ID> {

  private final SocialAuthJpaRepository repository;

  @Transactional
  @Override
  public void delete(ID userId) {
    SocialAuth socialAuth = repository.findById(userId.getValue()).orElseThrow(() -> {
      throw new SocialAuthException("존재하지 않는 소셜 계정입니다.");
    });
    if(socialAuth.isRecentlyOut()) throw new SocialAuthException("이미 회원 탈퇴가 진행된 계정입니다.");
    repository.save(socialAuthUpdateStatus(socialAuth));
  }

  private SocialAuth socialAuthUpdateStatus(SocialAuth socialAuth){
    return SocialAuth.builder().lastLogoutTime(socialAuth.getLastLogoutTime()).isRecentlyOut(true).oauthId(socialAuth.getOauthId()).build();
  }
}
