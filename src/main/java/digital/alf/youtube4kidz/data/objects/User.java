package digital.alf.youtube4kidz.data.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    public static final String GCPENTITY_KIND = "user";
    public static final String GCPENTITY_LOGIN = "Login";
    public static final String GCPENTITY_PASSWORD = "Password";

    private Long id;
    private String login;
    private String password;
}
