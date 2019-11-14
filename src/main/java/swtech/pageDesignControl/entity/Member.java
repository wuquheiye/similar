package swtech.pageDesignControl.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Member implements Serializable {
    private Integer code;
    private String name;
    private Integer age;
    private Date birth;

    public Member(Integer code, String name, Integer age, Date birth) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }
}
