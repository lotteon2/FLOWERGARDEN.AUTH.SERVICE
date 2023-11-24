package com.bit.lot.flower.auth.store.dto;

import com.bit.lot.flower.auth.store.valueobject.StoreId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreManagerLoginResponseWithStoreIdAndName extends
    StoreManagerLoginResponse {
  private StoreId storeId;
}
