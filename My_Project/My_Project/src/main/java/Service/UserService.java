package Service;

import model.User_Model;
import org.springframework.stereotype.Service;
import repository.UsersRepository;

@Service
public class UserService {

    private final UsersRepository usersrepository;
    public UserService(UsersRepository usersrepository){

        this.usersrepository=usersrepository;
    }
    public User_Model registerUser(String login,String password,String email){
        if(login==null||password==null){
            return null;
        }else{
            if(usersrepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
            }
            User_Model usermodel=new User_Model();
            usermodel.setLogin(login);
            usermodel.setPassword(password);
            usermodel.setEmail(email);
            return usersrepository.save(usermodel);
        }

    }

    public User_Model authenticate(String login, String password) {
       return usersrepository.findByLoginAndPassword(login,password).orElse(null);
    }
//    public User_Model authenticate(String login,String password){
//        return usersrepository.findByLoginAndPassword(login,password).orElse( other: null);
//    }
}
