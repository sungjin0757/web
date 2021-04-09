package firstproject.shop.controller;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@Data
public class MemberDto  {

    @NotBlank(message = "아이디 입력은 필수 입니다.")
    private String name;

    @NotBlank(message = "비밀번호 입력은 필수 입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\W)(?=\\\\S+$).{8,20}"
    ,message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자~20자의 비밀번호 입니다.")
    private String password;

    //회원 주소
    private String zipcode;
    private String city;
    private String street;
}
