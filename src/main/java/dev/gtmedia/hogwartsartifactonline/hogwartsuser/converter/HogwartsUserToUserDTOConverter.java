package dev.gtmedia.hogwartsartifactonline.hogwartsuser.converter;

import dev.gtmedia.hogwartsartifactonline.hogwartsuser.HogwartsUser;
import dev.gtmedia.hogwartsartifactonline.hogwartsuser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HogwartsUserToUserDTOConverter implements Converter<HogwartsUser, UserDto> {
    @Override
    public UserDto convert(HogwartsUser source) {
        return new UserDto(source.getId(), source.getUsername(), source.isEnabled(), source.getRoles());
    }

    public List<UserDto> convert(List<HogwartsUser> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
