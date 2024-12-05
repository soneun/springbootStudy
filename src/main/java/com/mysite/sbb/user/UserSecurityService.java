package com.mysite.sbb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //시큐리티의 인증을 하기위해서 메서드를 완성한다.
        //이때 유저를 DB 검색하여 1.유저객체와 2.유저권한을 함께 UserDetails 로 리턴
        Optional<SiteUser> _siteUser = userRepo.findByUsername(username);
        if(_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();//유저객체 찾음

        //유저의 권한을 임의로 설정(이름이 admin이면 관리자 아니면 user)
        List<GrantedAuthority> authorities = new ArrayList<>();//시큐리티 권한 리스트(고정) -> 여러개 권한 가능하므로 s붙음
        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRole()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getRole()));
        }//유저 권한 완료
       //유저네임, 패스워드, 권한리스트로 유저 객체를 만들어 리턴한다
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
