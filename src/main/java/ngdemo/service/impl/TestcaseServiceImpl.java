package ngdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import ngdemo.domain.Testcase;
import ngdemo.repositories.contract.TestcaseRepository;
import ngdemo.service.contract.TestcaseService;

import java.util.List;

@Singleton
public class TestcaseServiceImpl implements TestcaseService {

	private final TestcaseRepository testcaseRepository;

    @Inject
    public TestcaseServiceImpl(TestcaseRepository testcaseRepository) {
        this.testcaseRepository = testcaseRepository;
    }

    public List getAllTestcases() {
        return this.testcaseRepository.getAll();
    }

    public Testcase getById(int id) {
        return this.testcaseRepository.getById(id);
    }

    public Testcase createNewTestcase(Testcase testcase) {
        Testcase u = this.testcaseRepository.create(testcase);
        return u;
    }

    public Testcase update(Testcase testcase) {
        return this.testcaseRepository.update(testcase);
    }

    public void remove(String id) {
        this.testcaseRepository.remove(id);
    }

    public int getNumberOfTestcases() {
        return this.testcaseRepository.getNumberOfTestcases();
    }


}
