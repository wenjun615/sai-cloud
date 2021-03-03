package com.wen.sai.common.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>
 * 解决 AdminUserDetails getAuthorities 反序列化失败问题
 * </p>
 *
 * @author wenjun
 * @since 2021/2/25
 */
public class AuthorityDeserializer extends JsonDeserializer<LinkedList<GrantedAuthority>> {

    @Override
    public LinkedList<GrantedAuthority> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        LinkedList<GrantedAuthority> grantedAuthorityLinkedList = new LinkedList<>();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode authority = elements.next().get("authority");
            grantedAuthorityLinkedList.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorityLinkedList;
    }
}
