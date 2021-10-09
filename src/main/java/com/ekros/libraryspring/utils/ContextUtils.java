package com.ekros.libraryspring.utils;

import com.ekros.libraryspring.model.entity.LibraryUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextUtils {
    public static LibraryUserDetails getPrincipal(){
        return ((LibraryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
