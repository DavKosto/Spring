package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Role;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.repositories.RolesRepository;
import com.geekbrains.july.market.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Optional<User> findByPhone(String phone) {
        return usersRepository.findOneByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findOneByPhone(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findOneUserByPhone(String userPhone){
        User user = usersRepository.findOneUserByPhone(userPhone);
        return user;
    }

    public List<User> findAllBy(){
        return usersRepository.findAllBy();
    }

    public void changeAccess(Long id, String access){
        User user = usersRepository.findOneUserById(id);
        Collection<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().startsWith("ACCESS")){
                role.setName(access);
                break;
            }
        }
    }

    public Role getAccess(Long id){
        User user = usersRepository.findOneUserById(id);
        Collection<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().startsWith("ACCESS")){
                return role;
            }
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}