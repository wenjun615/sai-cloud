package com.wen.sai.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * <p>
 * 秘钥对相关操作 Controller
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/keyPair")
@Api(tags = {"KeyPairController"})
public class KeyPairController {

    private final KeyPair keyPair;

    @ApiOperation("加载公钥")
    @GetMapping("/loadPublicKey")
    public Map<String, Object> loadPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
