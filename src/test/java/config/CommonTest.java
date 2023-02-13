package config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommonTest {
	
	@BeforeAll
	public void setUpClass() {
		
	}
	
	
	@AfterAll
	public void tearDownClass() {
		
	}
	
	@BeforeEach
    public void setUp() {
        
    }

    @AfterEach
    public void tearDown() {
        
    }
}
