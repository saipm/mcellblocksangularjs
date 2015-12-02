package ngdemo.service.contract;


import java.util.List;

import ngdemo.domain.Testcase;

public interface TestcaseService {

    List<Testcase> getAllTestcases();

    Testcase getById(int id);

    Testcase createNewTestcase(Testcase testcase);

    Testcase update(Testcase testcase);

    void remove(String id);

    int getNumberOfTestcases();
}
