package alkemyChallenge.alkemyBlog.service.serviceImpl;

import alkemyChallenge.alkemyBlog.model.Category;
import alkemyChallenge.alkemyBlog.repository.CategoryRepository;
import alkemyChallenge.alkemyBlog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
