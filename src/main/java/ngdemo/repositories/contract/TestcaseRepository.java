package ngdemo.repositories.contract;

import ngdemo.domain.Testcase;

public interface TestcaseRepository extends Repository<Testcase> {
    Testcase create(Testcase testcase);

    Testcase update(Testcase testcase);

    void remove(String id);

    int getNumberOfTestcases();
}
