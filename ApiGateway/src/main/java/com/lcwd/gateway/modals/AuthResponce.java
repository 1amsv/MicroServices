package com.lcwd.gateway.modals;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponce {
	
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String> authorites;

}
