package TODOservice.services;

import TODOservice.dao.TODOServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TODOPostService {
    @Autowired
    TODOServiceDAO todoPostDAO;

    private static TODOPostService ourInstance = new TODOPostService();

    public static TODOPostService getInstance() {
        return ourInstance;
    }

    private TODOPostService() {
    }

}
