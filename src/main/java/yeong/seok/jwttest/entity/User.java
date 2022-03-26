package yeong.seok.jwttest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity // DB Table 과 1:1 매핑되는 객체
@Table(name="user") // 명시적으로 지정 만약 안하면 클래스 명이 table 명
@Getter // Getter,Setter,Builder,AllArgsConstructor,NoArgsConstructor
@Setter // 5개 어노테이션은 Lombok 어노테이션으로 get,set,Builder,Constructor 관련 코드를 자동으로 생성
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore // 서버에서 Json 응답을 생성할때 해당 필드는 ignore 하겠다는 의미
    @Id // primary Key
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="username",length = 50,unique = true)
    private String username;

    @JsonIgnore
    @Column(name="password",length = 100)
    private String password;

    @Column(name="nickname", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name="activated")
    private boolean activated;
    


    // ManyToMany,JoinTable 부분은 User,Authority 테이블이 다대다 관계를
    // 1:다, 다:1 관계의 조인 테이블로 정의
    @ManyToMany
    @JoinTable(
     name="user_authority",
     joinColumns = {@JoinColumn(name="user_id",referencedColumnName ="user_id" )},
     inverseJoinColumns = {@JoinColumn(name="authority_name",referencedColumnName = "authority_name")})
    private Set<Authority> autorities;
    
    
    
    
}
