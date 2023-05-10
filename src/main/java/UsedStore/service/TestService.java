package UsedStore.service;

import UsedStore.mapper.TestMapper;
import UsedStore.model.Test;
import org.springframework.stereotype.Service;

import java.util.List;

//TestMapper 인터페이스 받아 데이터 조회하는 클래스

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