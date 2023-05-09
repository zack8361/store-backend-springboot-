package UsedStore.service;

import UsedStore.mapper.TestMapper;
import UsedStore.model.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestMapper testMapper;

    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    public List<Test> findAll() {
        return testMapper.findAll();
    }
}