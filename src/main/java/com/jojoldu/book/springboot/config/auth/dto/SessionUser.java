package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // SessionUser 에는 인증된 사용자 정보만 필요함.
    // 그 외 필요한 정보들은 없으니 name, email, picture 만 필드로 선언함.
    // User 클래스를 그대로 사용하면 직렬화를 구현하지 않았다는 에러가 발생함.
    // User 클래스는 Entity 이므로, 언제 다른 Entity 와 관계가 형성될지 모르니.. 직렬화 시 성능 이슈, 부수 효과가 발생할 확률이 높음
    // 그래서  직렬화 기능을 가진 세션 Dto 를 하나 추가로 만드는 것이 운영 및 유지보수에 많은 도움이 됨
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
