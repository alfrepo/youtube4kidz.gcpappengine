package digital.alf.youtube4kidz.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final Long USER_ID = Long.valueOf("5629499534213120");

    public Long getCurrentUserId(){
        return USER_ID;
    }
}
