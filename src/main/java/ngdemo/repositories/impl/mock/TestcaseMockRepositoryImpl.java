package ngdemo.repositories.impl.mock;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.google.inject.Singleton;
import ngdemo.domain.NullTestcase;
import ngdemo.domain.Testcase;
import ngdemo.repositories.contract.TestcaseRepository;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TestcaseMockRepositoryImpl extends GenericMockRepository<Testcase> implements TestcaseRepository {

    private List<Testcase> testcases = new ArrayList<Testcase>();

    public TestcaseMockRepositoryImpl() {
        this.testcases = this.createTestcases();
    }

    public Testcase getById(String id) {
        for (Testcase u : this.testcases) {
            if (u.getTestcase() == id) {
                return u;
            }
        }
        return new NullTestcase();
    }

    public List<Testcase> getAll() {
        return this.testcases;
    }

    
    public Testcase create(Testcase testcase) {
        //device.setDevice((getCurrentMaxId() + 1));
    	testcase.setTestcase("hello");
        this.testcases.add(testcase);
        return testcase;
    }

    
    public Testcase update(Testcase testcase) {
        Testcase byId = this.getById(testcase.getTestcase());
        //byId.setFirstName(device.getFirstName());
        //byId.setLastName(device.getLastName());
        return byId;
    }

    
    public void remove(int id) {
        Testcase byId = this.getById(id);
        this.testcases.remove(byId);
    }

    
    public int getNumberOfTestcases() {
        return this.testcases.size();
    }

    private List<Testcase> createTestcases() {
        int numberOfTestcases = 10;
        for (int i = 0; i < numberOfTestcases; i++) {
            Testcase testcase = new Testcase();
            testcase.setTestcase("i + 1");
          //  device.setFirstName("Foo" + (i + 1));
          //  device.setLastName("Bar" + (i + 1));
            this.testcases.add(testcase);
        }
        return this.testcases;
    }

    private String getCurrentMaxId() {
        Ordering<Testcase> ordering = new Ordering<Testcase>() {

			@Override
			public int compare(Testcase arg0, Testcase arg1) {
				// TODO Auto-generated method stub
				return 0;
			}
            
            /*public int compare(Device left, Device right) {
                return Ints.compare(left.getId(), right.getId());
            }*/
        };
        return ordering.max(this.testcases).getTestcase();
    }

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}
}
