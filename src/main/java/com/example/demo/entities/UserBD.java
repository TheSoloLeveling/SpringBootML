package com.example.demo.entities;

import com.example.demo.Feed.PostLiked;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "userBD",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userName")
        })
public class UserBD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String userName;

    @NotBlank
    @Size(max = 120)
    private String password;

    private String icon;
    private String cover;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public  Optional<String> getCover() {
        return Optional.ofNullable(cover);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Optional<String> getIcon() {
        return Optional.ofNullable(icon);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    private boolean CredentialsNonExpired;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="id", referencedColumnName = "id")
    private Set<ClubFollowed> followedTo;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="id", referencedColumnName = "id")
    private Set<PostLiked> LikedTo;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<PostLiked> getLikedTo() {
        return LikedTo;
    }

    public void setLikedTo(Set<PostLiked> likedTo) {
        LikedTo = likedTo;
    }

    public void setFollowedTo(Set<ClubFollowed> followedTo) {
        this.followedTo = followedTo;
    }

    public Set<ClubFollowed> getFollowedTo() {
        return followedTo;
    }

    public UserBD() {
    }

    public UserBD(String userName, Set<Role> roles, String password) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public UserBD(String userName, Set<Role> roles, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public boolean isCredentialsNonExpired() {
        return CredentialsNonExpired;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }


    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setIsAccountNonExpired(boolean expired) {
        this.isAccountNonExpired = expired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        CredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public String toString() {
        return "UserBD{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", roles=" + roles +
                ", isEnabled=" + isEnabled +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", CredentialsNonExpired=" + CredentialsNonExpired +
                '}';
    }
}
